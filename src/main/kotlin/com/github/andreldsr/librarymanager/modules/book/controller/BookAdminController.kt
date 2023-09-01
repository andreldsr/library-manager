package com.github.andreldsr.librarymanager.modules.book.controller

import com.github.andreldsr.librarymanager.modules.book.request.CreateBookRequest
import com.github.andreldsr.librarymanager.modules.book.service.BookService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin/book")
class BookAdminController(private val bookService: BookService) {
    @GetMapping
    fun findAll(@PageableDefault(size = 20, page = 0) pageable: Pageable) = bookService.findAll(pageable)

    @PostMapping
    fun create(@RequestBody request: List<CreateBookRequest>) = bookService.create(request)
}
