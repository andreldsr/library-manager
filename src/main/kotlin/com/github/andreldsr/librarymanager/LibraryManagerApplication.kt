package com.github.andreldsr.librarymanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LibraryManagerApplication

fun main(args: Array<String>) {
    runApplication<LibraryManagerApplication>(*args)
}
