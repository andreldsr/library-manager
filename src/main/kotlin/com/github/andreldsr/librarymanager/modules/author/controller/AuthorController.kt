package com.github.andreldsr.librarymanager.modules.author.controller

import com.github.andreldsr.librarymanager.modules.author.service.AuthorService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/author")
class AuthorController(private val authorService: AuthorService) {
    @GetMapping
    fun findAll(@PageableDefault(size = 10, page = 0) pageable: Pageable) = authorService.findAll(pageable)
}
