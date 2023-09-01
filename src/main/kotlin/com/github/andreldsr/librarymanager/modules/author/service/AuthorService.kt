package com.github.andreldsr.librarymanager.modules.author.service

import com.github.andreldsr.librarymanager.modules.author.data.AuthorDatasource
import com.github.andreldsr.librarymanager.modules.author.request.CreateAuthorRequest
import com.github.andreldsr.librarymanager.modules.author.request.toEntity
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class AuthorService(private val authorDatasource: AuthorDatasource) {
    fun findAll(pageable: Pageable) = authorDatasource.findAll(pageable)
    fun create(request: CreateAuthorRequest) = authorDatasource.create(request.toEntity())
}
