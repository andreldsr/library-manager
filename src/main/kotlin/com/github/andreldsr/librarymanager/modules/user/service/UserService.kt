package com.github.andreldsr.librarymanager.modules.user.service

import com.github.andreldsr.librarymanager.exception.AlreadyExistsException
import com.github.andreldsr.librarymanager.modules.role.Role
import com.github.andreldsr.librarymanager.modules.role.RoleDatasource
import com.github.andreldsr.librarymanager.modules.user.data.Profile
import com.github.andreldsr.librarymanager.modules.user.data.User
import com.github.andreldsr.librarymanager.modules.user.data.UserDatasource
import com.github.andreldsr.librarymanager.modules.user.request.RegisterRequest
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userDatasource: UserDatasource,
    private val roleDatasource: RoleDatasource,
    private val passwordEncoder: PasswordEncoder
) : UserDetailsService {
    override fun loadUserByUsername(username: String) = userDatasource.findByLogin(username)

    fun registerNewUser(request: RegisterRequest): User {
        validate(request)
        val (userRole, role) = getRoles(request)
        val profile = buildProfile(request)
        val user = User(
            name = request.name.uppercase(),
            login = request.login,
            userPassword = passwordEncoder.encode(request.password),
            roles = mutableListOf(userRole, role),
            profile = profile
        )
        return userDatasource.create(user)
    }

    private fun buildProfile(request: RegisterRequest) =
        Profile(
            name = if (request.role == "ROLE_STUDENT") "ESTUDANTE" else "PROFESSOR",
            description = request.description ?: "Sem descrição",
            document = request.login
        )

    private fun getRoles(request: RegisterRequest): Pair<Role, Role> {
        val userRole = roleDatasource.findByName("ROLE_USER")
        val role = roleDatasource.findByName(request.role)
        return Pair(userRole, role)
    }

    private fun validate(request: RegisterRequest) {
        if (userDatasource.existsByLogin(request.login)) throw AlreadyExistsException("User already exists")
        if (request.role == "ROLE_ADMIN") throw IllegalArgumentException("Invalid role")
    }
}
