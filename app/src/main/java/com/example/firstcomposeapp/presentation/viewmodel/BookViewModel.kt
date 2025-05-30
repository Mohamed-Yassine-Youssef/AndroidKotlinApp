// viewmodel/BookViewModel.kt
package com.example.firstcomposeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstcomposeapp.data.repository.BookRepository
import com.example.firstcomposeapp.domain.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {
    private val repository = BookRepository()

    private val _uiState = MutableStateFlow(BookUiState())
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()

    private val _selectedBook = MutableStateFlow<Book?>(null)
    val selectedBook: StateFlow<Book?> = _selectedBook.asStateFlow()

    init {
        loadBooks()
    }

    fun loadBooks() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val books = repository.getBooks()
                _uiState.value = _uiState.value.copy(
                    books = books,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message
                )
            }
        }
    }

    fun loadBookById(id: Int) {
        viewModelScope.launch {
            try {
                val book = repository.getBookById(id)
                _selectedBook.value = book
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(errorMessage = e.message)
            }
        }
    }


    fun toggleFavorite(bookId: Int) {
        viewModelScope.launch {
            repository.toggleFavorite(bookId)
            // Refresh the books list and selected book
            loadBooks()
            _selectedBook.value?.let { currentBook ->
                if (currentBook.id == bookId) {
                    loadBookById(bookId)
                }
            }
        }
    }

    fun filterBooks(query: String) {
        val filteredBooks = if (query.isEmpty()) {
            _uiState.value.books
        } else {
            _uiState.value.books.filter { book ->
                book.title.contains(query, ignoreCase = true) ||
                        book.author.contains(query, ignoreCase = true) ||
                        book.genre.contains(query, ignoreCase = true)
            }
        }
        _uiState.value = _uiState.value.copy(filteredBooks = filteredBooks)
    }

    fun showFavoritesOnly(showFavorites: Boolean) {
        _uiState.value = _uiState.value.copy(showFavoritesOnly = showFavorites)
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}

data class BookUiState(
    val books: List<Book> = emptyList(),
    val filteredBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val showFavoritesOnly: Boolean = false
) {
    val displayBooks: List<Book>
        get() = when {
            showFavoritesOnly -> books.filter { it.isFavorite }
            filteredBooks.isNotEmpty() -> filteredBooks
            else -> books
        }
}