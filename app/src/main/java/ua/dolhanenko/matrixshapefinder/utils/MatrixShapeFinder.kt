package ua.dolhanenko.matrixshapefinder.utils

import ua.dolhanenko.matrixshapefinder.data.model.Matrix
import ua.dolhanenko.matrixshapefinder.data.model.Shape
import ua.dolhanenko.matrixshapefinder.data.model.Vertex


class MatrixShapeFinder(private val matrix: Matrix) {
    companion object {
        private const val LOG_TAG = "SHAPE_FINDER"
    }

    fun findShapes(): List<Shape> {
        val foundShapes = mutableListOf<Shape>()
        val usedVertices = mutableSetOf<Vertex>()
        val matrixArray = matrix.array
        for (offsetY in matrixArray.indices) {
            for (offsetX in matrixArray[offsetY].indices) {
                val currentVertex = Vertex(offsetX, offsetY)
                if (matrix.valueOf(currentVertex) == 1 && !usedVertices.contains(currentVertex)) {
                    val adjacentVertices = mutableSetOf(currentVertex)
                    getAdjacentVerticesTo(currentVertex, matrix, adjacentVertices)
                    usedVertices.addAll(adjacentVertices)
                    foundShapes.add(Shape(adjacentVertices))
                }
            }
        }
        return foundShapes
    }

    private fun getAdjacentVerticesTo(
        vertex: Vertex,
        matrix: Matrix,
        resultSet: MutableSet<Vertex>
    ) {
        val matrixArray = matrix.array
        for (offsetY in vertex.offsetY - 1..vertex.offsetY + 1) {
            if (offsetY >= 0 && offsetY < matrixArray.size) {
                for (offsetX in vertex.offsetX - 1..vertex.offsetX + 1) {
                    if (offsetX >= 0 && offsetX < matrixArray[offsetY].size) {
                        val currentVertex = Vertex(offsetX, offsetY)
                        if (currentVertex != vertex) {
                            if (matrix.valueOf(currentVertex) == 1 &&
                                currentVertex.isAdjacentTo(vertex)
                            ) {
                                if (!resultSet.contains(currentVertex)) {
                                    resultSet.add(currentVertex)
                                    getAdjacentVerticesTo(currentVertex, matrix, resultSet)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}