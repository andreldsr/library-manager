package com.github.andreldsr.librarymanager.modules.book.data

import com.github.andreldsr.librarymanager.modules.book.data.projections.BookListDTO
import com.github.andreldsr.librarymanager.modules.lending.data.Lending
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface BookRepository : JpaRepository<Book, Long> {
    fun existsByRegisterNumber(registerNumber: Int): Boolean

    @Query(
        value = """
            SELECT  DISTINCT b.id AS id, 
                    b.title AS title, 
                    STRING_AGG(a.name, ', ') as authorsNames, 
                    p.name AS publisherName, 
                    l.id AS lendingId, 
                    l.returnDate AS lendingReturnDate
            FROM Book b
            LEFT JOIN b.publisher p
            LEFT JOIN b.lending l
            LEFT JOIN b.authors a
            WHERE b.title ILIKE %:titleSearch%
            OR a.name ILIKE %:authorNameSearch%
            OR b.publisher.name ILIKE %:publisherNameSearch%
            GROUP BY b.id, p.name, l.id
            ORDER BY b.title
        """,
        countQuery = """
            SELECT count(distinct b.id)
            FROM Book b
            LEFT JOIN b.publisher p
            LEFT JOIN b.lending l
            LEFT JOIN b.authors a
            WHERE b.title ILIKE %:titleSearch%
            OR a.name ILIKE %:authorNameSearch%
            OR b.publisher.name ILIKE %:publisherNameSearch%
        """
    )
    fun findByQuery(
        titleSearch: String?,
        authorNameSearch: String?,
        publisherNameSearch: String?,
        pageable: Pageable
    ): Page<BookListDTO>

    @EntityGraph(attributePaths = ["authors", "publisher"])
    override fun findAll(pageable: Pageable): Page<Book>

    @Modifying
    @Query("UPDATE Book b SET b.lending = :lending WHERE b.id = :id")
    fun updateLending(id: Long, lending: Lending?)

    @EntityGraph(attributePaths = ["authors", "publisher"])
    override fun findById(id: Long): Optional<Book>
}
