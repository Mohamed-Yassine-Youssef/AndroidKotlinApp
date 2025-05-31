package com.example.firstcomposeapp.data.repository
import com.example.firstcomposeapp.data.source.FakeBookDataSource
import com.example.firstcomposeapp.domain.model.Book
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BookRepository {
    private val _books = MutableStateFlow(FakeBookDataSource.sampleBooks)
    val books: Flow<List<Book>> = _books.asStateFlow()

    suspend fun getBooks(): List<Book> {
        delay(500) // Just to Simulate network delay
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


}

