package com.example.codigocolores.Items

enum class ColorCode(val displayName: String, val value: Int?, val multiplier: Double?, val tolerance: String?, val colorHex: String) {
    NEGRO("Negro", 0, 1.0, null, "#000000"),
    MARRON("Marrón", 1, 10.0, null, "#964B00"),
    ROJO("Rojo", 2, 100.0, null, "#FF0000"),
    NARANJA("Naranja", 3, 1000.0, null, "#FF8800"),
    AMARILLO("Amarillo", 4, 10000.0, null, "#FFFF00"),
    VERDE("Verde", 5, null, null, "#00FF00"),
    AZUL("Azul", 6, null, null, "#0000FF"),
    VIOLETA("Violeta", 7, null, null, "#8B00FF"),
    GRIS("Gris", 8, null, null, "#808080"),
    BLANCO("Blanco", 9, null, null, "#FFFFFF"),
    DORADO("Dorado", null, 0.1, "±5%", "#FFD700"),
    PLATEADO("Plateado", null, 0.01, "±10%", "#C0C0C0"),
    NINGUNO("Ninguno", null, null, "±20%", "#E0E0E0")
}