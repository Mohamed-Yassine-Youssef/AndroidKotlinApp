package com.example.firstcomposeapp.data.repository

import com.example.firstcomposeapp.domain.model.Book
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BookRepository {
    private val _books = MutableStateFlow(sampleBooks)
    val books: Flow<List<Book>> = _books.asStateFlow()

    suspend fun getBooks(): List<Book> {
        delay(500) // Simulate network delay
        return _books.value
    }

    suspend fun getBookById(id: Int): Book? {
        delay(200)
        return _books.value.find { it.id == id }
    }

    suspend fun toggleFavorite(bookId: Int) {
        val currentBooks = _books.value.toMutableList()
        val bookIndex = currentBooks.indexOfFirst { it.id == bookId }
        if (bookIndex != -1) {
            currentBooks[bookIndex] = currentBooks[bookIndex].copy(
                isFavorite = !currentBooks[bookIndex].isFavorite
            )
            _books.value = currentBooks
        }
    }

    fun getFavoriteBooks(): List<Book> {
        return _books.value.filter { it.isFavorite }
    }
}

private val sampleBooks = listOf(
    Book(
        id = 1,
        title = "Le Petit Prince",
        author = "Antoine de Saint-Exupéry",
        genre = "Fiction",
        description = "Un conte poétique et philosophique sous l'apparence d'un conte pour enfants. L'histoire d'un aviateur qui rencontre un petit prince venu d'une autre planète.",
        publishedYear = 1943,
        rating = 4.6f,
        coverUrl = "https://via.placeholder.com/300x400/4CAF50/white?text=Le+Petit+Prince"
    ),
    Book(
        id = 2,
        title = "1984",
        author = "George Orwell",
        genre = "Science Fiction",
        description = "Un roman dystopique qui dépeint une société totalitaire où la liberté individuelle est anéantie par un système de surveillance omniprésent.",
        publishedYear = 1949,
        rating = 4.4f,
        coverUrl = "https://via.placeholder.com/300x400/2196F3/white?text=1984"
    ),
    Book(
        id = 3,
        title = "Pride and Prejudice",
        author = "Jane Austen",
        genre = "Romance",
        description = "L'histoire d'Elizabeth Bennet et de sa relation compliquée avec le fier Mr. Darcy dans l'Angleterre du XIXe siècle.",
        publishedYear = 1813,
        rating = 4.3f,
        coverUrl = "https://via.placeholder.com/300x400/E91E63/white?text=Pride+and+Prejudice"
    ),
    Book(
        id = 4,
        title = "To Kill a Mockingbird",
        author = "Harper Lee",
        genre = "Fiction",
        description = "Un roman sur l'injustice raciale dans le Sud américain des années 1930, vu à travers les yeux d'une jeune fille.",
        publishedYear = 1960,
        rating = 4.5f,
        coverUrl = "https://via.placeholder.com/300x400/FF5722/white?text=To+Kill+a+Mockingbird"
    ),
    Book(
        id = 5,
        title = "Harry Potter à l'école des sorciers",
        author = "J.K. Rowling",
        genre = "Fantasy",
        description = "L'histoire d'un jeune garçon qui découvre qu'il est un sorcier et entre dans une école de magie.",
        publishedYear = 1997,
        rating = 4.7f,
        coverUrl = "https://via.placeholder.com/300x400/9C27B0/white?text=Harry+Potter"
    ),
    Book(
        id = 6,
        title = "L'Étranger",
        author = "Albert Camus",
        genre = "Philosophie",
        description = "L'histoire de Meursault, un homme indifférent qui commet un meurtre apparemment sans motif.",
        publishedYear = 1942,
        rating = 4.1f,
        coverUrl = "https://via.placeholder.com/300x400/607D8B/white?text=L'Étranger"
    )
)