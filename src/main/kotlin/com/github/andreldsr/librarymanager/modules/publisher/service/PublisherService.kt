package com.github.andreldsr.librarymanager.modules.publisher.service

import com.github.andreldsr.librarymanager.modules.publisher.data.PublisherDatasource
import com.github.andreldsr.librarymanager.modules.publisher.request.CreatePublisherRequest
import com.github.andreldsr.librarymanager.modules.publisher.request.toEntity
import org.springframework.stereotype.Service

@Service
class PublisherService(private val publisherDatasource: PublisherDatasource) {
    fun findAll() = publisherDatasource.findAll()
    fun create(request: CreatePublisherRequest) = publisherDatasource.create(request.toEntity())
}
