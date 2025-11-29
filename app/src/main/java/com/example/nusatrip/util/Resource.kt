package com.example.nusatrip.util

/**
 * Generic class untuk handling status data (Success, Error, Loading)
 */
sealed class Resource<out T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error(val message: String, val throwable: Throwable? = null) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}