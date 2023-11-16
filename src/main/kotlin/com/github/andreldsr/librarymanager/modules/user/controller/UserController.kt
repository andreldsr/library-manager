package com.github.andreldsr.librarymanager.modules.user.controller

import com.github.andreldsr.librarymanager.modules.user.service.UserService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin/user")
class UserController(private val userService: UserService) {
    @GetMapping("/{login}")
    fun findById(@PathVariable login: String) = userService.loadUserByUsername(login)

    @GetMapping("/name/{name}")
    fun findByName(
        @PathVariable name: String,
        @PageableDefault(size = 20, page = 0) pageable: Pageable
    ) = userService.findByName(name, pageable)
}
