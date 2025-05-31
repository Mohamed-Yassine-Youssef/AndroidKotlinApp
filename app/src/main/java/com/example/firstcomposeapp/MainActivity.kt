package com.example.firstcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstcomposeapp.ui.theme.BookCatalogTheme
import com.example.firstcomposeapp.presentation.screens.BookDetailScreen
import com.example.firstcomposeapp.presentation.screens.BookListScreen
import com.example.firstcomposeapp.presentation.screens.FavoriteBooksScreen
import com.example.firstcomposeapp.presentation.viewmodel.BookViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BookCatalogTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BookCatalogApp()
                }
            }
        }
    }
}


@Composable
fun BookCatalogApp() {
    val navController = rememberNavController()
    val viewModel: BookViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") {
            WelcomeHomeScreen(
                onNavigateToBooks = {
                    navController.navigate("book_list")
                }
            )
        }
        composable("book_list") {
            BookListScreen(
                viewModel = viewModel,
                onBookClick = { bookId ->
                    navController.navigate("book_detail/$bookId")
                },
                        onNavigateToFavorites={navController.navigate("favorite_books")}
            )
        }
        composable("book_detail/{bookId}") { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId")?.toIntOrNull() ?: 0
            BookDetailScreen(
                viewModel = viewModel,
                bookId = bookId,
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("favorite_books") {
            FavoriteBooksScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                onBookClick = { bookId ->
                    navController.navigate("book_detail/$bookId")
                },
                        onNavigateToBooks = {
                    navController.navigate("book_list")
                }
            )
        }
    }
}