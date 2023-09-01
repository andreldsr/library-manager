package com.github.andreldsr.librarymanager.modules.publisher.request

import com.github.andreldsr.librarymanager.modules.publisher.data.Publisher

data class CreatePublisherRequest(
    val name: String
)

fun CreatePublisherRequest.toEntity() = Publisher(
    name = name
)
