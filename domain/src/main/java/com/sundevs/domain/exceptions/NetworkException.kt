package com.sundevs.domain.exceptions

sealed class NetworkException(message: String?) : Throwable(message) {

    class Forbidden(message: String?) : NetworkException(message)
    open class BadRequest(message: String?) : NetworkException(message)
    class NotFound(message: String?) : BadRequest(message)
    class ServerError(message: String?) : NetworkException(message)
    class Unauthorized(message: String?) : NetworkException(message)
    class UnknowError(message: String?) : NetworkException(message)
    class UnknowHost(message: String?) : NetworkException(message)
}
