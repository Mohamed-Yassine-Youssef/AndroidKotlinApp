package com.example.firstcomposeapp.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class BottomNavDestination(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    BOOK_LIST(
        route = "book_list",
        title = "Catalogue",
        selectedIcon = Icons.Filled.Menu,
        unselectedIcon = Icons.Outlined.Menu
    ),
    FAVORITES(
        route = "favorite_books",
        title = "Favoris",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder
    )
}

@Composable
fun BottomNavBar(
    currentDestination: String,
    onNavigate: (String) -> Unit,
    favoriteCount: Int = 0
) {
    // Glassmorphism background with gradient
    val gradientColors = listOf(
        MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.9f)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Main navigation container with modern styling
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(28.dp),
                    ambientColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    spotColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
                )
                .background(
                    brush = Brush.verticalGradient(gradientColors),
                    shape = RoundedCornerShape(28.dp)
                )
                .clip(RoundedCornerShape(28.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomNavDestination.values().forEach { destination ->
                    ModernNavItem(
                        destination = destination,
                        isSelected = currentDestination == destination.route,
                        favoriteCount = if (destination == BottomNavDestination.FAVORITES) favoriteCount else 0,
                        onClick = {
                            if (currentDestination != destination.route) {
                                onNavigate(destination.route)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ModernNavItem(
    destination: BottomNavDestination,
    isSelected: Boolean,
    favoriteCount: Int,
    onClick: () -> Unit
) {
    // Animation for selection state
    val animatedScale by animateFloatAsState(
        targetValue = if (isSelected) 1.1f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
    )

    val animatedAlpha by animateFloatAsState(
        targetValue = if (isSelected) 1f else 0.7f,
        animationSpec = tween(300),
        label = "alpha"
    )

    // Color animation
    val animatedColor by animateColorAsState(
        targetValue = if (isSelected)
            MaterialTheme.colorScheme.primary
        else
            MaterialTheme.colorScheme.onSurfaceVariant,
        animationSpec = tween(300),
        label = "color"
    )

    // Background animation for selected state
    val backgroundAlpha by animateFloatAsState(
        targetValue = if (isSelected) 1f else 0f,
        animationSpec = tween(300),
        label = "background"
    )

    FilledTonalButton(
        onClick = onClick,
        modifier = Modifier
            .scale(animatedScale)
            .size(width = 120.dp, height = 56.dp),
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = backgroundAlpha * 0.8f)
            else
                Color.Transparent,
            contentColor = animatedColor
        ),
        shape = RoundedCornerShape(24.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Icon with badge
                Box(contentAlignment = Alignment.TopEnd) {
                    Icon(
                        imageVector = if (isSelected) destination.selectedIcon else destination.unselectedIcon,
                        contentDescription = destination.title,
                        modifier = Modifier
                            .size(24.dp),
                        tint = animatedColor
                    )

                    // Modern badge design
                    if (destination == BottomNavDestination.FAVORITES && favoriteCount > 0) {
                        val badgeScale by animateFloatAsState(
                            targetValue = 1f,
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessMedium
                            ),
                            label = "badge_scale"
                        )

                        Box(
                            modifier = Modifier
                                .offset(x = 8.dp, y = (-8).dp)
                                .scale(badgeScale)
                                .size(18.dp)
                                .shadow(
                                    elevation = 4.dp,
                                    shape = CircleShape
                                )
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = listOf(
                                            MaterialTheme.colorScheme.error,
                                            MaterialTheme.colorScheme.error.copy(alpha = 0.8f)
                                        )
                                    ),
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (favoriteCount > 99) "99+" else favoriteCount.toString(),
                                color = MaterialTheme.colorScheme.onError,
                                fontSize = 9.sp,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Animated label
                Text(
                    text = destination.title,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = if (isSelected) 11.sp else 10.sp
                    ),
                    color = animatedColor.copy(alpha = animatedAlpha),
                    maxLines = 1
                )
            }

            // Selection indicator
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 20.dp)
                        .size(width = 32.dp, height = 3.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                                )
                            ),
                            shape = RoundedCornerShape(2.dp)
                        )
                )
            }
        }
    }
}