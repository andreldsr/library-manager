package com.github.andreldsr.librarymanager.modules.lending.controller

import com.github.andreldsr.librarymanager.modules.lending.request.CreateLengingRequest
import com.github.andreldsr.librarymanager.modules.lending.service.LendingService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin/lending")
class LendingAdminController(private val lendingService: LendingService) {
    @GetMapping
    fun findAll(@PageableDefault pageable: Pageable) = lendingService.findAll(pageable)

    @GetMapping("/open")
    fun findAllOpen(@PageableDefault pageable: Pageable) = lendingService.findAllOpen(pageable)

    @GetMapping("/overdue")
    fun findAllOverdue(@PageableDefault pageable: Pageable) = lendingService.findAllOverdue(pageable)

    @GetMapping("/today")
    fun findAllForToday(@PageableDefault pageable: Pageable) = lendingService.findAllForToday(pageable)

    @GetMapping("/user/{userId}")
    fun findAllByUserId(@PathVariable userId: Long, @PageableDefault pageable: Pageable) =
        lendingService.findAllByUserId(userId, pageable)

    @GetMapping("/book/{bookId}")
    fun findAllByBookId(@PathVariable bookId: Long, @PageableDefault pageable: Pageable) =
        lendingService.findAllByBookId(bookId, pageable)

    @PostMapping
    fun create(@RequestBody request: CreateLengingRequest) = lendingService.create(request)

    @PatchMapping("/{lendingId}/return")
    fun returnBook(@PathVariable lendingId: Long) = lendingService.returnBook(lendingId)
}
