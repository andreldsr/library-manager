package com.github.andreldsr.librarymanager.modules.user.data

import org.springframework.stereotype.Repository

@Repository
class UserDatasource(private val userRepository: UserRepository) {
    fun findByLogin(login: String) = userRepository.findByLogin(login)
    fun existsByLogin(login: String) = userRepository.existsByLogin(login)
    fun create(user: User) = userRepository.save(user)
    fun findById(userId: Long) = userRepository.findById(userId)
}
