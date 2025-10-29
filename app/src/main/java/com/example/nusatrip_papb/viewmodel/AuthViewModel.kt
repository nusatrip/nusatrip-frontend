package com.example.nusatrip_papb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * UI State for Authentication screen
 */
data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val token: String? = null,
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true
)

/**
 * ViewModel for handling authentication logic
 * Currently using static/dummy data for UI testing
 */
class AuthViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    // Dummy credentials for testing
    private val DUMMY_EMAIL = "test@gmail.com"
    private val DUMMY_PASSWORD = "password123"

    /**
     * Update email field
     */
    fun onEmailChange(newEmail: String) {
        _uiState.update {
            it.copy(
                email = newEmail,
                errorMessage = null,
                isEmailValid = true
            )
        }
    }

    /**
     * Update password field
     */
    fun onPasswordChange(newPassword: String) {
        _uiState.update {
            it.copy(
                password = newPassword,
                errorMessage = null,
                isPasswordValid = true
            )
        }
    }

    /**
     * Perform login with static validation
     * Uses dummy credentials for testing
     */
    fun loginUser(onSuccess: (String) -> Unit) {
        // Validate inputs first
        if (!validateInputs()) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            // Simulate network delay
            delay(1500)

            // Static validation against dummy credentials
            if (_uiState.value.email == DUMMY_EMAIL &&
                _uiState.value.password == DUMMY_PASSWORD) {

                // Success - generate dummy token
                val dummyToken = "dummy_token_${System.currentTimeMillis()}"

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        token = dummyToken,
                        errorMessage = null
                    )
                }

                onSuccess(dummyToken)

            } else {
                // Failed - show error
                handleError("Email atau password salah")
            }
        }
    }

    /**
     * Validate user inputs
     */
    private fun validateInputs(): Boolean {
        val emailValid = isValidEmail(_uiState.value.email)
        val passwordValid = _uiState.value.password.length >= 6

        _uiState.update {
            it.copy(
                isEmailValid = emailValid,
                isPasswordValid = passwordValid,
                errorMessage = when {
                    !emailValid -> "Format email tidak valid"
                    !passwordValid -> "Password minimal 6 karakter"
                    else -> null
                }
            )
        }

        return emailValid && passwordValid
    }

    /**
     * Validate email format
     */
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    /**
     * Handle error state
     */
    private fun handleError(message: String) {
        _uiState.update {
            it.copy(
                isLoading = false,
                errorMessage = message
            )
        }
    }

    /**
     * Clear error message
     */
    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    /**
     * Clear authentication token
     */
    fun clearToken() {
        _uiState.update { it.copy(token = null) }
    }

    /**
     * Reset form to initial state
     */
    fun resetForm() {
        _uiState.update { AuthUiState() }
    }
}