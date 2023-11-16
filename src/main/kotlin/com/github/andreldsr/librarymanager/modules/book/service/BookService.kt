package com.github.andreldsr.librarymanager.modules.book.service

import com.github.andreldsr.librarymanager.exception.AlreadyExistsException
import com.github.andreldsr.librarymanager.modules.author.data.Author
import com.github.andreldsr.librarymanager.modules.author.data.AuthorDatasource
import com.github.andreldsr.librarymanager.modules.book.data.Book
import com.github.andreldsr.librarymanager.modules.book.data.BookDatasource
import com.github.andreldsr.librarymanager.modules.book.request.CreateBookRequest
import com.github.andreldsr.librarymanager.modules.publisher.data.Publisher
import com.github.andreldsr.librarymanager.modules.publisher.data.PublisherDatasource
import jakarta.transaction.Transactional
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class BookService(
    private val bookDatasource: BookDatasource,
    private val authorDatasource: AuthorDatasource,
    private val publisherDatasource: PublisherDatasource
) {
    fun findAll(query: String, pageable: Pageable) = bookDatasource.findAll(query, pageable)
    fun findAll(pageable: Pageable) = bookDatasource.findAll(pageable)

    @Transactional
    fun create(request: List<CreateBookRequest>) = request.map {
        validate(it)
        val authors = findOrCreateAuthor(it.authors.split(" e "))
        val publisher = findOrCreatePublisher(it.publisher)
        val book = createBook(it, authors, publisher)
        bookDatasource.create(book)
    }

    private fun createBook(
        request: CreateBookRequest,
        authors: List<Author>,
        publisher: Publisher
    ) = Book(
        title = request.title,
        authors = authors,
        publisher = publisher,
        registerNumber = request.registerNumber,
        registrationDate = LocalDate.parse(request.registrationDate, DateTimeFormatter.ofPattern("dd.MM.yyyy")),
        volume = request.volume,
        copy = request.copy,
        location = request.location,
        publicationYear = request.publicationYear,
        acquisitionForm = request.acquisitionForm,
        index = request.index,
        cdd = request.cdd,
        observation = request.observation
    )

    private fun findOrCreatePublisher(publisher: String): Publisher =
        publisherDatasource.findByName(publisher) ?: publisherDatasource.create(Publisher(name = publisher))

    private fun validate(request: CreateBookRequest) {
        if (bookDatasource.existsByRegisterNumber(request.registerNumber)) {
            throw AlreadyExistsException("Book already exists")
        }
    }

    private fun findOrCreateAuthor(authors: List<String>): List<Author> = authors.map {
        authorDatasource.findByName(it) ?: authorDatasource.create(Author(name = it))
    }

    fun findById(id: Long) = bookDatasource.findById(id)
    fun getStats() = bookDatasource.getStats()
}
