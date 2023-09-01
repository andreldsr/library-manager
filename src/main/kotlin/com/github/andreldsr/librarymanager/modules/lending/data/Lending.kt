package com.github.andreldsr.librarymanager.modules.lending.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.github.andreldsr.librarymanager.modules.book.data.Book
import com.github.andreldsr.librarymanager.modules.user.data.User
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDate

@Entity
data class Lending(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    val user: User,
    @ManyToOne
    @JsonIgnoreProperties("lending")
    val book: Book,
    val returnDate: LocalDate,
    val returnedAt: LocalDate? = null,
    val createdAt: LocalDate = LocalDate.now()
)
