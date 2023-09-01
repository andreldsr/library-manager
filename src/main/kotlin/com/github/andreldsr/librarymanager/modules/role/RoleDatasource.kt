package com.github.andreldsr.librarymanager.modules.role

import org.springframework.stereotype.Repository

@Repository
class RoleDatasource(private val roleRepository: RoleRepository) {
    fun findByName(name: String) = roleRepository.findByName(name)
}
