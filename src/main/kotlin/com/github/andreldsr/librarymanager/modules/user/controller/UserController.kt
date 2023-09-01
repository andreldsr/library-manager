package com.github.andreldsr.librarymanager.modules.user.controller

import com.github.andreldsr.librarymanager.modules.user.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin/user")
class UserController(private val userService: UserService) {
    @GetMapping("/{login}")
    fun findById(@PathVariable login: String) = userService.loadUserByUsername(login)
}
