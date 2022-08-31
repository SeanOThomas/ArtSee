package com.sthomas.artsee.domain.repository

/**
 * An abstraction that helps with error handling. Our repositories should return valid
 * data or an error using [Resource.Success] and [Resource.Error], respectively.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}