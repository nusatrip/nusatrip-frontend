package com.example.nusatrip.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class AuthState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val user: FirebaseUser? = null,
    val isRegistrationComplete: Boolean = false
)

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    /**
     * Initialization block.
     * Automatically checks for an existing user session when the ViewModel is created.
     * This ensures the Profile screen receives user data even after an app restart.
     */
    init {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            _authState.value = AuthState(
                user = currentUser,
                isSuccess = true,
                isLoading = false,
                isRegistrationComplete = true
            )
        }
    }

    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                Log.d("AuthViewModel", "Starting login for: $email")
                _authState.value = AuthState(isLoading = true, error = null)

                val result = auth.signInWithEmailAndPassword(email, password).await()

                _authState.value = AuthState(
                    isLoading = false,
                    isSuccess = true,
                    user = result.user,
                    error = null,
                    isRegistrationComplete = false
                )

                Log.d("AuthViewModel", "Login successful: ${result.user?.email}")
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Login failed", e)
                val errorMessage = when (e) {
                    is FirebaseAuthInvalidUserException -> "Account not found. Please check your email or register."
                    is FirebaseAuthInvalidCredentialsException -> "Incorrect password. Please try again."
                    else -> "Login failed: ${e.message}"
                }
                _authState.value = AuthState(
                    isLoading = false,
                    isSuccess = false,
                    error = errorMessage
                )
            }
        }
    }

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                Log.d("AuthViewModel", "Starting registration for: $email")
                _authState.value = AuthState(isLoading = true, error = null, isRegistrationComplete = false)

                val result = auth.createUserWithEmailAndPassword(email, password).await()
                Log.d("AuthViewModel", "User created: ${result.user?.uid}")

                val user = result.user
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()
                user?.updateProfile(profileUpdates)?.await()
                Log.d("AuthViewModel", "Profile updated")

                user?.let {
                    val userData = hashMapOf(
                        "name" to name,
                        "email" to email,
                        "createdAt" to System.currentTimeMillis()
                    )
                    firestore.collection("users").document(it.uid).set(userData).await()
                    Log.d("AuthViewModel", "Firestore data saved")
                }

                auth.signOut()
                Log.d("AuthViewModel", "User signed out after registration")

                _authState.value = AuthState(
                    isLoading = false,
                    isSuccess = false,
                    user = null,
                    error = null,
                    isRegistrationComplete = true
                )

                Log.d("AuthViewModel", "Registration complete state: ${_authState.value}")
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Registration failed", e)
                _authState.value = AuthState(
                    isLoading = false,
                    isSuccess = false,
                    error = "Registration failed: ${e.message}",
                    isRegistrationComplete = false
                )
            }
        }
    }

    fun logout() {
        auth.signOut()
        _authState.value = AuthState()
        Log.d("AuthViewModel", "User logged out")
    }

    fun resetError() {
        _authState.value = _authState.value.copy(error = null)
    }

    fun resetAuthState() {
        _authState.value = AuthState()
        Log.d("AuthViewModel", "Auth state reset")
    }
}