// screens/BookListScreen.kt
package com.example.firstcomposeapp.presentation.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.firstcomposeapp.presentation.components.BookItem
import com.example.firstcomposeapp.presentation.components.SearchSection
import com.example.firstcomposeapp.presentation.viewmodel.BookViewModel
import com.example.firstcomposeapp.presentation.components.BottomNavBar
data class BookCategory(
    val name: String,
    val icon: ImageVector,
    val genre: String? = null // null means "All Categories"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(
    viewModel: BookViewModel,
    onBookClick: (Int) -> Unit,
    onNavigateToFavorites: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Tous") }

    // Define categories with icons
    val categories = listOf(
        BookCategory("Tous", Icons.Default.Menu, null),
        BookCategory("Science Fiction", Icons.Default.Favorite, "Science Fiction"),
        BookCategory("Développement Personnel", Icons.Default.Search, "Développement Personnel"),
        BookCategory("business", Icons.Default.Person, "business"),

        BookCategory("Philosophie", Icons.Default.Person, "Philosophie"),
        BookCategory("Science Fiction", Icons.Default.Person, "Science Fiction"),
        BookCategory("Enfants", Icons.Default.Person, "Enfants"),

    )

    LaunchedEffect(searchQuery) {
        viewModel.filterBooks(searchQuery)
    }

    LaunchedEffect(selectedCategory) {
        val selectedGenre = categories.find { it.name == selectedCategory }?.genre
        viewModel.filterByCategory(selectedGenre)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catalogue de Livres") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
                bottomBar = {
            BottomNavBar(
                currentDestination = "book_list",
                onNavigate = { destination ->
                    if (destination == "favorite_books") {
                        onNavigateToFavorites()
                    }
                },)}
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Search Section
            SearchSection(
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },

                booksCount = uiState.displayBooks.size
            )

            // Categories Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Catégories",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(horizontal = 4.dp)
                    ) {
                        items(categories) { category ->
                            FilterChip(
                                onClick = {
                                    selectedCategory = category.name
                                },
                                label = {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Icon(
                                            imageVector = category.icon,
                                            contentDescription = null,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Text(
                                            text = category.name,
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                    }
                                },
                                selected = selectedCategory == category.name,
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                    selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            )
                        }
                    }
                }
            }

            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                uiState.errorMessage != null -> {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer
                        )
                    ) {
                        Text(
                            text = "Erreur: ${uiState.errorMessage}",
                            modifier = Modifier.padding(16.dp),
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                    }
                }

                uiState.displayBooks.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                modifier = Modifier.size(64.dp),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "Aucun livre trouvé",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            if (selectedCategory != "Tous" || searchQuery.isNotEmpty()) {
                                Text(
                                    text = "Essayez de modifier vos filtres",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                else -> {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(uiState.displayBooks) { book ->
                            BookItem(
                                book = book,
                                onBookClick = { onBookClick(book.id) },
                                onFavoriteClick = { viewModel.toggleFavorite(book.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}