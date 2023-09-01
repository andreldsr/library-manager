package com.github.andreldsr.librarymanager.modules.lending.data.projection

import java.time.LocalDate

interface LendingListDTO {
    fun getId(): Long
    fun getBookId(): Long
    fun getBookTitle(): String
    fun getUserName(): String
    fun getReturnDate(): LocalDate
    fun getReturnedAt(): LocalDate?
}
