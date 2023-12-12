package com.github.andreldsr.librarymanager.modules.book.request

data class CreateBookRequest(
    val registerNumber: String,
    val registrationDate: String,
    val authors: String,
    val title: String,
    val volume: String,
    val copy: String,
    val location: String,
    val publisher: String,
    val publicationYear: String,
    val acquisitionForm: String,
    val index: String,
    val cdd: String,
    val observation: String
)
