package com.github.andreldsr.librarymanager.modules.lending.data.projection

import java.time.LocalDate

interface LendingDetailDTO {
    fun getId(): Long
    fun getBookId(): Long
    fun getBookTitle(): String
    fun getBookCopy(): String
    fun getBookLocation(): String
    fun getBookObservation() : String
    fun getUserName(): String
    fun getReturnDate(): LocalDate
    fun getReturnedAt(): LocalDate?
}
