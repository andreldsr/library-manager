package com.github.andreldsr.librarymanager.modules.author.data

import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class AuthorDatasource(private val authorRepository: AuthorRepository) {
    fun findAll(pageable: Pageable) = authorRepository.findAll(pageable)
    fun create(author: Author) = authorRepository.save(author)
    fun findByName(authorName: String) = authorRepository.findByName(authorName)
}
