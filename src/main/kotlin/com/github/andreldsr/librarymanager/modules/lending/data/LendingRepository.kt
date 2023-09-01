package com.github.andreldsr.librarymanager.modules.lending.data

import com.github.andreldsr.librarymanager.modules.lending.data.projection.LendingListDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface LendingRepository : JpaRepository<Lending, Long> {
    fun findAllByUserId(userId: Long, pageable: Pageable): Page<LendingListDTO>
    fun findAllByBookId(bookId: Long, pageable: Pageable): Page<LendingListDTO>
    @EntityGraph(attributePaths = ["book", "user"])
    fun findAllByReturnedAtNull(pageable: Pageable): Page<LendingListDTO>
    @EntityGraph(attributePaths = ["book", "user"])
    fun findAllByReturnDateAndReturnedAtNull(returnDate: LocalDate, pageable: Pageable): Page<LendingListDTO>
    @EntityGraph(attributePaths = ["book", "user"])
    fun findAllByReturnDateBeforeAndReturnedAtNull(returnDate: LocalDate, pageable: Pageable): Page<LendingListDTO>

    @Modifying
    @Query("UPDATE Lending l SET l.returnedAt = :returnDate WHERE l.id = :lendingId")
    fun returnBook(lendingId: Long, returnDate: LocalDate = LocalDate.now())
}
