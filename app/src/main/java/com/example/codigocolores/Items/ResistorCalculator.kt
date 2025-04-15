package com.example.codigocolores.Items

class ResistorCalculator {
    fun calculateResistance(band1: Int, band2: Int, multiplier: Double): Double {
        val baseValue = (band1 * 10) + band2
        return baseValue * multiplier
    }

    fun formatResistanceValue(resistance: Double): String {
        return when {
            resistance >= 1_000_000 -> String.format("%.2fM", resistance / 1_000_000)
            resistance >= 1_000 -> String.format("%.2fk", resistance / 1_000)
            else -> resistance.toString()
        }
    }

    fun getColorsForValueBands(): List<ColorCode> {
        return ColorCode.values().filter { it.value != null && it.value in 0..9 }
    }

    fun getColorsForMultiplierBand(): List<ColorCode> {
        return ColorCode.values().filter { it.multiplier != null && it.multiplier <= 10000 }
    }

    fun getColorsForToleranceBand(): List<ColorCode> {
        return listOf(ColorCode.DORADO, ColorCode.PLATEADO, ColorCode.NINGUNO)
    }

}
