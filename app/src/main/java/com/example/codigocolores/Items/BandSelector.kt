package com.example.codigocolores.Items

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BandSelector(
    label: String,
    options: List<String>,
    selectedIndex: Int,
    onValueChange: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    // Animación para la rotación del ícono
    val rotationAngle by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

    // Colores personalizados por banda
    val bandColor = when (options[selectedIndex]) {
        "Rojo" -> Color.Red
        "Verde" -> Color.Green
        "Azul" -> Color.Blue
        else -> MaterialTheme.colorScheme.surface
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = options[selectedIndex],
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .graphicsLayer {
                    shadowElevation = 4.dp.toPx()
                    shape = RoundedCornerShape(12.dp)
                    clip = true
                },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.graphicsLayer {
                        rotationZ = rotationAngle
                    }
                )
            }
        )

        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            ExposedDropdownMenu(
                expanded = true,
                onDismissRequest = { expanded = false }
            ) {
                options.forEachIndexed { index, option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                option,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        },
                        onClick = {
                            onValueChange(index)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
