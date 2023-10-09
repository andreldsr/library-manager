package com.github.andreldsr.librarymanager.modules.lending.data

import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class LendingDatasource(private val lendingRepository: LendingRepository) {
    fun findAll(pageable: Pageable) = lendingRepository.findAll(pageable)
    fun findAllByUserId(userId: Long, pageable: Pageable) = lendingRepository.findAllByUserId(userId, pageable)
    fun findAllByBookId(bookId: Long, pageable: Pageable) = lendingRepository.findAllByBookId(bookId, pageable)
    fun findAllOpen(pageable: Pageable) = lendingRepository.findAllByReturnedAtNull(pageable)
    fun findAllForToday(pageable: Pageable) =
        lendingRepository.findAllByReturnDateAndReturnedAtNull(LocalDate.now(), pageable)

    fun findAllOverdue(pageable: Pageable) =
        lendingRepository.findAllByReturnDateBeforeAndReturnedAtNull(LocalDate.now(), pageable)

    fun create(lending: Lending) = lendingRepository.save(lending)
    fun returnBook(lendingId: Long) = lendingRepository.returnBook(lendingId)
    fun findById(lendingId: Long) = lendingRepository.findById(lendingId)
}
