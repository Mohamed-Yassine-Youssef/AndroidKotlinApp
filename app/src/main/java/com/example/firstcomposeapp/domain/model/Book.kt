// models/Book.kt
package com.example.firstcomposeapp.domain.model

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val genre: String,
    val description: String,
    val publishedYear: Int,
    val rating: Float,
    val coverResId: Int,
    val amazonLink:String,
    var isFavorite: Boolean = false
)