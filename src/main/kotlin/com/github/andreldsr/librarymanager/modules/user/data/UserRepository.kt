package com.github.andreldsr.librarymanager.modules.user.data

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Long> {
    fun existsByLogin(login: String): Boolean

    @EntityGraph(attributePaths = ["roles"])
    fun findByLogin(login: String): User

    @Query(value = "SELECT u FROM User u where u.name ILIKE %:name%")
    fun findByName(name: String, pageable: Pageable): Page<UserListDto>
}
