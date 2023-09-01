package com.github.andreldsr.librarymanager.modules.author.request

import com.github.andreldsr.librarymanager.modules.author.data.Author

data class CreateAuthorRequest(
    val name: String
)

fun CreateAuthorRequest.toEntity() = Author(name = name)
