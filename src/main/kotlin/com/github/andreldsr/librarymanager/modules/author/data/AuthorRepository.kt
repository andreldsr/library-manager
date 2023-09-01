package com.github.andreldsr.librarymanager.modules.author.data

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository : JpaRepository<Author, Long> {
    @EntityGraph(attributePaths = ["books", "books.publisher"])
    fun findByName(authorName: String): Author?
}
