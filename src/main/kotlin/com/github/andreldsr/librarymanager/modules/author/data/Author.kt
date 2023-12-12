package com.github.andreldsr.librarymanager.modules.author.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.github.andreldsr.librarymanager.modules.book.data.Book
import jakarta.persistence.Cacheable
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany

@Entity
@Cacheable
data class Author(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    @ManyToMany(mappedBy = "authors")
    @JsonIgnoreProperties("authors")
    val books: List<Book> = emptyList()
)
