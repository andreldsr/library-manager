package com.github.andreldsr.librarymanager.modules.book.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.github.andreldsr.librarymanager.modules.author.data.Author
import com.github.andreldsr.librarymanager.modules.lending.data.Lending
import com.github.andreldsr.librarymanager.modules.publisher.data.Publisher
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import java.time.LocalDate

@Entity
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(unique = true, name = "register_number")
    val registerNumber: Int,
    @Column(name = "registration_date")
    val registrationDate: LocalDate,
    @ManyToMany
    @JoinTable(
        name = "books_authors",
        joinColumns = [JoinColumn(name = "book_id")],
        inverseJoinColumns = [JoinColumn(name = "author_id")]
    )
    @JsonIgnoreProperties("books")
    val authors: List<Author>,
    val title: String,
    val volume: String,
    val copy: String,
    val location: String,
    @ManyToOne
    val publisher: Publisher,
    @Column(name = "publication_year")
    val publicationYear: Int,
    @Column(name = "acquisition_form")
    val acquisitionForm: String,
    val index: String,
    val cdd: String,
    val observation: String,
    @ManyToOne
    @JsonIgnoreProperties("book")
    val lending: Lending? = null
)
