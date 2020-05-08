package ua.dolhanenko.matrixshapefinder.utils

import android.util.Log
import ua.dolhanenko.matrixshapefinder.data.model.Matrix
import ua.dolhanenko.matrixshapefinder.data.model.Shape
import ua.dolhanenko.matrixshapefinder.data.model.Vertex


class MatrixShapeFinder(private val matrix: Matrix) {
    companion object {
        private const val LOG_TAG = "SHAPE_FINDER"
    }

    fun findShapes(): List<Shape> {
        val foundShapes = mutableListOf<Shape>()
        val matrixArray = matrix.array
        for (offsetY in matrixArray.indices) {
            for (offsetX in matrixArray[offsetY].indices) {
                val currentVertex = Vertex(offsetX, offsetY)
                if (matrix.valueOf(currentVertex) == 1) {
                    var addedToShape = false
                    for (shape in foundShapes) {
                        if (addedToShape) break
                        for (vertex in shape.vertices) {
                            if (vertex.isAdjacentTo(currentVertex)) {
                                shape.vertices.add(currentVertex)
                                addedToShape = true
                                break
                            }
                        }
                    }
                    if (!addedToShape) {
                        foundShapes.add(Shape(mutableSetOf(currentVertex)))
                    }
                }
            }
        }
        return foundShapes
    }
}