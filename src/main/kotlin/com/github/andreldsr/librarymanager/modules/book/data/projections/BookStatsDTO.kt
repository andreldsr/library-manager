package com.github.andreldsr.librarymanager.modules.book.data.projections

interface BookStatsDTO {
    fun getTotal(): Int
    fun getLent(): Int
    fun getToday(): Int
    fun getDelayed(): Int
}