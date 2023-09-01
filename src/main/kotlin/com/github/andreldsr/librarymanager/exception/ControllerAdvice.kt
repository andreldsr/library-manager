package com.github.andreldsr.librarymanager.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler
    fun handleUserNotFound(exception: NotFoundException): ProblemDetail {
        val pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND)
        pd.detail = exception.message
        return pd
    }

    @ExceptionHandler
    fun handleUserAlreadyExists(exception: AlreadyExistsException): ProblemDetail {
        val pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST)
        pd.detail = exception.message
        return pd
    }

    @ExceptionHandler
    fun handleIllegalArgumentException(exception: IllegalArgumentException): ProblemDetail {
        val pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST)
        pd.detail = exception.message
        return pd
    }
}
