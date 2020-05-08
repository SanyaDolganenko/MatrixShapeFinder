package ua.dolhanenko.matrixshapefinder.data.model

import kotlin.math.abs


data class Vertex(val offsetX: Int, val offsetY: Int) {
    fun isAdjacentTo(other: Vertex): Boolean {
        return abs(offsetX - other.offsetX) in 0..1 &&
                abs(offsetY - other.offsetY) in 0..1
    }
}