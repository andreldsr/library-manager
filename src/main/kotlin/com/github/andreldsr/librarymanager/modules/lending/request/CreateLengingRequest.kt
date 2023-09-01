package com.github.andreldsr.librarymanager.modules.lending.request

import java.time.LocalDate

data class CreateLengingRequest(
    val userId: Long,
    val bookId: Long,
    val returnDate: LocalDate
)
