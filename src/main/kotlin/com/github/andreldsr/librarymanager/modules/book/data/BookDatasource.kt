package com.github.andreldsr.librarymanager.modules.book.data

import com.github.andreldsr.librarymanager.modules.lending.data.Lending
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class BookDatasource(private val bookRepository: BookRepository) {
    fun findAll(query: String, pageable: Pageable) =
        bookRepository.findByQuery(
            query,
            query,
            query,
            pageable
        )

    fun findAll(pageable: Pageable) = bookRepository.findAll(pageable)
    fun create(book: Book) = bookRepository.save(book)
    fun existsByRegisterNumber(registerNumber: Int) = bookRepository.existsByRegisterNumber(registerNumber)
    fun findById(bookId: Long) = bookRepository.findById(bookId)
    fun updateLending(id: Long, lending: Lending) = bookRepository.updateLending(id, lending)
    fun removeLending(id: Long) = bookRepository.updateLending(id, null)
    fun getStats() = bookRepository.getStats(LocalDate.now())
}

