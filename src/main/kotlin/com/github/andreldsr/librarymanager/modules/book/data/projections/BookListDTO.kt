package com.github.andreldsr.librarymanager.modules.book.data.projections

import java.time.LocalDate

interface BookListDTO {
    fun getId(): Long
    fun getTitle(): String
    fun getPublisherName(): String
    fun getAuthorsNames(): String
    fun getLendingId(): Long?
    fun getLendingReturnDate(): LocalDate?
}
