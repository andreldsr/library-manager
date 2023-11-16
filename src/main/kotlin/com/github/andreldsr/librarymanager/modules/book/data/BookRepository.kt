package com.github.andreldsr.librarymanager.modules.book.data

import com.github.andreldsr.librarymanager.modules.book.data.projections.BookListDTO
import com.github.andreldsr.librarymanager.modules.book.data.projections.BookStatsDTO
import com.github.andreldsr.librarymanager.modules.lending.data.Lending
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate
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
    @Query("""
       SELECT 
            count(*) as total, 
            SUM(CASE WHEN l IS NOT NULL then 1 else 0 END) AS lent,
            SUM(CASE WHEN l IS NOT NULL AND l.returnDate = :today THEN 1 ELSE 0 END) AS today,
            SUM(CASE WHEN l IS NOT NULL AND l.returnDate < :today THEN 1 ELSE 0 END) AS delayed
       FROM Book b LEFT JOIN b.lending l 
    """)
    fun getStats(today: LocalDate): BookStatsDTO

    /*
    SELECT
    count(*) as total,
    SUM(CASE WHEN l.id IS NOT NULL then 1 else 0 END) AS lent,
    SUM(CASE WHEN l.id IS NOT NULL AND l.return_date = :today THEN 1 ELSE 0 END) AS today,
    SUM(CASE WHEN l.id IS NOT NULL AND l.return_date < :today THEN 1 ELSE 0 END) AS delayed
FROM Book b LEFT JOIN lending l ON b.lending_id = l.id;
     */
}
