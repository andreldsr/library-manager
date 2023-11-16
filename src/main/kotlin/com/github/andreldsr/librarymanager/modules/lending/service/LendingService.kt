package com.github.andreldsr.librarymanager.modules.lending.service

import com.github.andreldsr.librarymanager.exception.NotFoundException
import com.github.andreldsr.librarymanager.modules.book.data.Book
import com.github.andreldsr.librarymanager.modules.book.data.BookDatasource
import com.github.andreldsr.librarymanager.modules.lending.data.Lending
import com.github.andreldsr.librarymanager.modules.lending.data.LendingDatasource
import com.github.andreldsr.librarymanager.modules.lending.request.CreateLengingRequest
import com.github.andreldsr.librarymanager.modules.user.data.User
import com.github.andreldsr.librarymanager.modules.user.data.UserDatasource
import jakarta.transaction.Transactional
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class LendingService(
    private val lendingDatasource: LendingDatasource,
    private val bookDatasource: BookDatasource,
    private val userDatasource: UserDatasource
) {
    fun findAll(pageable: Pageable) = lendingDatasource.findAll(pageable)
    fun findDetailById(id: Long) = lendingDatasource.findDetailById(id)
    fun findAllByUserId(userId: Long, pageable: Pageable) = lendingDatasource.findAllByUserId(userId, pageable)
    fun findAllByBookId(bookId: Long, pageable: Pageable) = lendingDatasource.findAllByBookId(bookId, pageable)
    fun findAllForToday(pageable: Pageable) = lendingDatasource.findAllForToday(pageable)
    fun findAllOverdue(pageable: Pageable) = lendingDatasource.findAllOverdue(pageable)
    fun findAllOpen(pageable: Pageable) = lendingDatasource.findAllOpen(pageable)

    @Transactional
    fun create(request: CreateLengingRequest): Lending {
        val book = bookDatasource.findById(request.bookId).orElseThrow { throw NotFoundException("Book not found") }
        validateBook(book)
        val user = userDatasource.findById(request.userId).orElseThrow { throw NotFoundException("User not found") }
        val lending = createLending(book, user, request.returnDate)
        return lendingDatasource.create(lending).also { bookDatasource.updateLending(book.id!!, lending) }
    }

    @Transactional
    fun returnBook(lendingId: Long) {
        val lending = lendingDatasource.findById(lendingId).orElseThrow { NotFoundException("Lending not found") }
        lendingDatasource.returnBook(lendingId)
        bookDatasource.removeLending(lending.book.id!!)
    }

    private fun createLending(book: Book, user: User, returnDate: LocalDate) = Lending(
        book = book,
        user = user,
        returnDate = returnDate
    )

    private fun validateBook(book: Book) {
        if (book.lending != null) throw IllegalStateException("Book is already lent")
    }
}
