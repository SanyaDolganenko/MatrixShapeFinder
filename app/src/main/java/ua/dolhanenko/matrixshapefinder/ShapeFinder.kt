package ua.dolhanenko.matrixshapefinder

import android.util.Log
import kotlin.math.abs


data class Vertex(val offsetX: Int, val offsetY: Int) {
    fun isAdjacentTo(other: Vertex): Boolean {
        return abs(offsetX - other.offsetX) == 1 &&
                abs(offsetY - other.offsetY) == 1
    }
}

data class Shape(val vertices: MutableSet<Vertex>)

class Matrix(val array: Array<Array<Int>>) {
    fun valueOf(vertex: Vertex): Int {
        try {
            return array[vertex.offsetY][vertex.offsetX]
        } catch (e: IndexOutOfBoundsException) {
        }
        return -1
    }
}

class ShapeFinder(private val matrix: Matrix) {
    companion object {
        private const val LOG_TAG = "SHAPE_FINDER"
    }

    fun findShapes(): Int {
        val foundShapes = mutableListOf<Shape>()
        val matrixArray = matrix.array
        for (offsetY in matrixArray.indices) {
            for (offsetX in matrixArray[offsetY]) {
                val currentVertex = Vertex(offsetX, offsetY)
                if (matrix.valueOf(currentVertex) == 1) {
                    foundShapes.forEach { shape ->
                        //TODO break here too, if needed.
                        for (vertex in shape.vertices) {
                            if (vertex.isAdjacentTo(currentVertex)) {
                                shape.vertices.add(currentVertex)
                                break
                            }
                        }
                    }
                }
            }
        }
        Log.d(LOG_TAG, "Found following shapes count: ${foundShapes.size}")
        return foundShapes.size
    }
}