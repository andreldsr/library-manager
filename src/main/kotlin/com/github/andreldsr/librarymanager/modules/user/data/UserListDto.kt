package com.github.andreldsr.librarymanager.modules.user.data

interface UserListDto {
    fun getId(): Long
    fun getName(): String
    fun getLogin(): String
    fun getDescription(): String
}
