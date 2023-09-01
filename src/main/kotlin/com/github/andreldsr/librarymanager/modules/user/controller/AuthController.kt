package com.github.andreldsr.librarymanager.modules.user.controller

import com.github.andreldsr.librarymanager.config.JWTUtils
import com.github.andreldsr.librarymanager.modules.user.request.AuthRequest
import com.github.andreldsr.librarymanager.modules.user.request.RegisterRequest
import com.github.andreldsr.librarymanager.modules.user.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userDetailsService: UserDetailsService,
    private val jwtUtils: JWTUtils,
    private val authenticationManager: AuthenticationManager,
    private val userService: UserService
) {

    @PostMapping
    fun authenticate(@RequestBody request: AuthRequest): ResponseEntity<String> {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(request.username, request.password))
        val user = userDetailsService.loadUserByUsername(request.username)
        return generateResponse(user)
    }

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<String> {
        val user = userService.registerNewUser(request)
        return generateResponse(user)
    }

    private fun generateResponse(user: UserDetails): ResponseEntity<String> {
        val token = jwtUtils.generateToken(user)
        val headers = HttpHeaders()
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer $token")
        return ResponseEntity
            .ok()
            .headers(headers)
            .body("Token generated successfully")
    }
}
