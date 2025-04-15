package com.example.codigocolores.Items

import android.graphics.Color as AndroidColor //Se usa para definir la identidad de un color
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size

// Extensión para convertir hex a Color de Compose
fun String.toComposeColor(): Color = Color(AndroidColor.parseColor(this))

@Composable
fun CalculaResistenciaScreen() {
    val resistorCalculator = remember { ResistorCalculator() }

    var band1Index by remember { mutableStateOf(0) }
    var band2Index by remember { mutableStateOf(0) }
    var band3Index by remember { mutableStateOf(0) }
    var band4Index by remember { mutableStateOf(0) }

    val band1Color = resistorCalculator.getColorsForValueBands()[band1Index]
    val band2Color = resistorCalculator.getColorsForValueBands()[band2Index]
    val band3Color = resistorCalculator.getColorsForMultiplierBand()[band3Index]
    val band4Color = resistorCalculator.getColorsForToleranceBand()[band4Index]



    val resistance = resistorCalculator.calculateResistance(
        band1Color.value ?: 0,
        band2Color.value ?: 0,
        band3Color.multiplier ?: 1.0
    )
    val formattedResistance = resistorCalculator.formatResistanceValue(resistance)
    val toleranceString = band4Color.tolerance ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🔌Calculadora de Resistencias 🔎",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        BandSelector(
            label = "Banda 1:",
            options = resistorCalculator.getColorsForValueBands().map { it.displayName },
            selectedIndex = band1Index,
            onValueChange = { band1Index = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        BandSelector(
            label = "Banda 2:",
            options = resistorCalculator.getColorsForValueBands().map { it.displayName },
            selectedIndex = band2Index,
            onValueChange = { band2Index = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        BandSelector(
            label = "Banda 3 (Multiplicador):",
            options = resistorCalculator.getColorsForMultiplierBand().map { it.displayName },
            selectedIndex = band3Index,
            onValueChange = { band3Index = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        BandSelector(
            label = "Banda 4 (Tolerancia):",
            options = resistorCalculator.getColorsForToleranceBand().map { it.displayName },
            selectedIndex = band4Index,
            onValueChange = { band4Index = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Valor: $formattedResistance",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Tolerancia: $toleranceString",
            fontSize = 18.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Representación gráfica de la resistencia
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.LightGray)
        ) {
            val totalWidth = size.width
            val bandWidth = totalWidth / 10
            val spacing = bandWidth

            drawRect(
                color = band1Color.colorHex.toComposeColor(),
                topLeft = Offset(x = spacing * 2, y = 0f),
                size = Size(width = bandWidth, height = size.height)
            )
            drawRect(
                color = band2Color.colorHex.toComposeColor(),
                topLeft = Offset(x = spacing * 3, y = 0f),
                size = Size(width = bandWidth, height = size.height)
            )
            drawRect(
                color = band3Color.colorHex.toComposeColor(),
                topLeft = Offset(x = spacing * 4, y = 0f),
                size = Size(width = bandWidth, height = size.height)
            )
            drawRect(
                color = band4Color.colorHex.toComposeColor(),
                topLeft = Offset(x = spacing * 6, y = 0f),
                size = Size(width = bandWidth, height = size.height)
            )
        }
    }

}