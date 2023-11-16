package com.github.andreldsr.librarymanager.modules.book.controller

import com.github.andreldsr.librarymanager.modules.book.service.BookService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/book")
class BookController(private val bookService: BookService) {
    @GetMapping
    fun findAll(
        @RequestParam query: String,
        @PageableDefault(size = 20, page = 0) pageable: Pageable
    ) = bookService.findAll(query, pageable)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = bookService.findById(id)

    @GetMapping("/stats")
    fun getStats() = bookService.getStats()
}
