package com.github.andreldsr.librarymanager.modules.publisher.data

import org.springframework.stereotype.Repository

@Repository
class PublisherDatasource(private val publisherRepository: PublisherRepository) {
    fun findAll() = publisherRepository.findAll()
    fun create(publisher: Publisher) = publisherRepository.save(publisher)
    fun findByName(publisher: String) = publisherRepository.findByName(publisher)
}
