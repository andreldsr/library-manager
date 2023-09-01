package com.github.andreldsr.librarymanager.modules.publisher.data

import org.springframework.data.jpa.repository.JpaRepository

interface PublisherRepository : JpaRepository<Publisher, Long> {
    fun findByName(publisher: String): Publisher?
}
