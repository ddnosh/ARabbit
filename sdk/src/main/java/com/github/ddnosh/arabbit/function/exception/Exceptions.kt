package com.github.ddnosh.arabbit.function.exception

sealed class Errors : Exception() {

    object ConnectFailedException : Errors()

    object NetException : Errors()

    data class AuthorizationError(val timeStamp: Long) : Errors()
}