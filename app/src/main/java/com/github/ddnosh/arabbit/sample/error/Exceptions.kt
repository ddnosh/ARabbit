package com.github.ddnosh.arabbit.sample.error

sealed class Errors : Exception() {

    object ConnectFailedException : Errors()

    object NetException : Errors()

    data class AuthorizationError(val timeStamp: Long) : Errors()
}