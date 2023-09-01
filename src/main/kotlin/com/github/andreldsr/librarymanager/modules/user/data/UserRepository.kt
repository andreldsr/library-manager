package com.github.andreldsr.librarymanager.modules.user.data

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun existsByLogin(login: String): Boolean

    @EntityGraph(attributePaths = ["roles"])
    fun findByLogin(login: String): User
}
