package com.github.andreldsr.librarymanager.modules.author.controller

import com.github.andreldsr.librarymanager.modules.author.request.CreateAuthorRequest
import com.github.andreldsr.librarymanager.modules.author.service.AuthorService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin/author")
class AuthorAdminController(private val authorService: AuthorService) {
    @PostMapping
    fun create(@RequestBody request: CreateAuthorRequest) = authorService.create(request)
}
