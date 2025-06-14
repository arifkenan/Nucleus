package com.example.nucleus

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.delay
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

private const val PLAYER_RADIUS = 40f
private const val ELECTRON_RADIUS = 10f

@Composable
fun GameScreen() {
    val player = remember { Particle(Offset(300f, 300f), PLAYER_RADIUS) }
    val electrons = remember { mutableStateListOf<Particle>() }
    var power by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        repeat(20) {
            electrons += Particle(randomOffset(), ELECTRON_RADIUS)
        }
        while (true) {
            delay(16)
            electrons.forEach { it.moveRandom() }
            electrons.removeAll { electron ->
                if (player.overlaps(electron)) {
                    power += 1
                    true
                } else false
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    player.position = change.position
                }
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(Color.Cyan, player.radius, player.position)
            electrons.forEach { drawCircle(Color.Yellow, it.radius, it.position) }
        }
    }
}

private fun randomOffset(): Offset = Offset(Random.nextFloat() * 600f, Random.nextFloat() * 800f)

private data class Particle(var position: Offset, val radius: Float) {
    fun moveRandom() {
        position += Offset(Random.nextFloat() * 4 - 2, Random.nextFloat() * 4 - 2)
    }

    fun overlaps(other: Particle): Boolean {
        val dx = position.x - other.position.x
        val dy = position.y - other.position.y
        val distSq = dx*dx + dy*dy
        val r = radius + other.radius
        return distSq < r*r
    }
}
