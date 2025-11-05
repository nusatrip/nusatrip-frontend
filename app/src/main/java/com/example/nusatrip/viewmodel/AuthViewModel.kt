package com.example.nusatrip_papb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class AuthState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val user: FirebaseUser? = null
)

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        val currentUser = auth.currentUser
        _authState.value = _authState.value.copy(
            user = currentUser,
            isSuccess = currentUser != null
        )
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _authState.value = _authState.value.copy(isLoading = true, error = null)

                val result = auth.signInWithEmailAndPassword(email, password).await()

                _authState.value = _authState.value.copy(
                    isLoading = false,
                    isSuccess = true,
                    user = result.user,
                    error = null
                )
            } catch (e: Exception) {
                _authState.value = _authState.value.copy(
                    isLoading = false,
                    isSuccess = false,
                    error = e.message ?: "Login failed"
                )
            }
        }
    }

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                _authState.value = _authState.value.copy(isLoading = true, error = null)

                // Create user account
                val result = auth.createUserWithEmailAndPassword(email, password).await()

                // Update profile with name
                val user = result.user
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()
                user?.updateProfile(profileUpdates)?.await()

                // Save additional user data to Firestore
                user?.let {
                    val userData = hashMapOf(
                        "name" to name,
                        "email" to email,
                        "createdAt" to System.currentTimeMillis()
                    )
                    firestore.collection("users").document(it.uid).set(userData).await()
                }

                _authState.value = _authState.value.copy(
                    isLoading = false,
                    isSuccess = true,
                    user = user,
                    error = null
                )
            } catch (e: Exception) {
                _authState.value = _authState.value.copy(
                    isLoading = false,
                    isSuccess = false,
                    error = e.message ?: "Registration failed"
                )
            }
        }
    }

    fun logout() {
        auth.signOut()
        _authState.value = AuthState()
    }

    fun resetError() {
        _authState.value = _authState.value.copy(error = null)
    }
}