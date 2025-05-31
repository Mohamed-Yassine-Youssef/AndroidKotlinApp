package com.example.firstcomposeapp.presentation.screens
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import com.example.firstcomposeapp.R
@Composable
fun WelcomeHomeScreen(
    onNavigateToBooks: () -> Unit
) {

    var visible by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else 0.8f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        )
    )

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1200,
            easing = LinearOutSlowInEasing
        )
    )


    LaunchedEffect(Unit) {
        delay(300)
        visible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF667EEA),
                        Color(0xFF764BA2),
                        Color(0xFF6B73FF)
                    ),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
    ) {

        DecorativeBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
                .scale(scale),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Card(
                modifier = Modifier
                    .size(120.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.2f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.online_library),
                        contentDescription = "Book Icon",
                        modifier = Modifier.size(60.dp),
                        tint = Color.Unspecified
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.alpha(alpha)
            ) {
                Text(
                    text = "Bienvenue dans",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White.copy(alpha = 0.9f),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "BookVerse",
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Découvrez des histoires infinies et un savoir illimité dans votre bibliothèque numérique",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp
                )
            }

            Spacer(modifier = Modifier.height(60.dp))


            Button(
                onClick = onNavigateToBooks,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .alpha(alpha),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF667EEA)
                ),
                shape = RoundedCornerShape(28.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Accéder à la bibliothèque",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Enter",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }


        }


        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp)
                .alpha(alpha)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(3) { index ->
                    val animatedAlpha by animateFloatAsState(
                        targetValue = if (visible) 0.6f else 0f,
                        animationSpec = tween(
                            durationMillis = 800,
                            delayMillis = index * 200,
                            easing = LinearOutSlowInEasing
                        )
                    )

                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = animatedAlpha))
                    )
                }
            }
        }
    }
}

@Composable
fun DecorativeBackground() {

    Box(
        modifier = Modifier
            .offset(x = (-100).dp, y = (-150).dp)
            .size(300.dp)
            .clip(CircleShape)
            .background(
                Color.White.copy(alpha = 0.1f)
            )
    )

    Box(
        modifier = Modifier
            .offset(x = 200.dp, y = 400.dp)
            .size(200.dp)
            .clip(CircleShape)
            .background(
                Color.White.copy(alpha = 0.08f)
            )
    )

    Box(
        modifier = Modifier
            .offset(x = (-50).dp, y = 600.dp)
            .size(150.dp)
            .clip(CircleShape)
            .background(
                Color.White.copy(alpha = 0.06f)
            )
    )

    @Composable
    fun WelcomeHomeScreenPreview() {
        MaterialTheme {
            WelcomeHomeScreen(
                onNavigateToBooks = { }
            )
        }}
}


