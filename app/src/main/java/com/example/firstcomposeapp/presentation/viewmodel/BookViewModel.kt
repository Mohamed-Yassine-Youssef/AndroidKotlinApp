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
                    isLoading = false,
                    errorMessage = null
                )
                applyFilters()
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
                _selectedBook.value = repository.getBookById(id)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(errorMessage = e.message)
            }
        }
    }

    fun toggleFavorite(bookId: Int) {
        viewModelScope.launch {
            try {
                repository.toggleFavorite(bookId)
                updateFavoriteState(bookId)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(errorMessage = e.message)
            }
        }
    }

    private fun updateFavoriteState(bookId: Int) {
        _uiState.value = _uiState.value.let { current ->
            current.copy(
                books = current.books.map {
                    if (it.id == bookId) it.copy(isFavorite = !it.isFavorite) else it
                },
                filteredBooks = current.filteredBooks.map {
                    if (it.id == bookId) it.copy(isFavorite = !it.isFavorite) else it
                }
            )
        }

        _selectedBook.value = _selectedBook.value?.let {
            if (it.id == bookId) it.copy(isFavorite = !it.isFavorite) else it
        }
    }

    fun filterBooks(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
        applyFilters()
    }

    fun filterByCategory(genre: String?) {
        _uiState.value = _uiState.value.copy(selectedCategory = genre)
        applyFilters()
    }

    private fun applyFilters() {
        _uiState.value = _uiState.value.let { current ->
            var filtered = current.books

            current.selectedCategory?.let { category ->
                filtered = filtered.filter { it.genre.equals(category, ignoreCase = true) }
            }

            if (current.searchQuery.isNotEmpty()) {
                filtered = filtered.filter { book ->
                    book.title.contains(current.searchQuery, ignoreCase = true) ||
                            book.author.contains(current.searchQuery, ignoreCase = true) ||
                            book.genre.contains(current.searchQuery, ignoreCase = true)
                }
            }

            current.copy(filteredBooks = filtered)
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    fun getFavoriteBooks(): List<Book> = _uiState.value.books.filter { it.isFavorite }
}

data class BookUiState(
    val books: List<Book> = emptyList(),
    val filteredBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String = "",
    val selectedCategory: String? = null
) {
    val displayBooks: List<Book>
        get() = if (filteredBooks.isNotEmpty() || searchQuery.isNotEmpty() || selectedCategory != null) {
            filteredBooks
        } else {
            books
        }
}