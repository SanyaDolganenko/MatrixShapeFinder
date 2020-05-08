package ua.dolhanenko.matrixshapefinder

import junit.framework.Assert.assertEquals
import org.junit.Test
import ua.dolhanenko.matrixshapefinder.data.model.Matrix
import ua.dolhanenko.matrixshapefinder.utils.MatrixShapeFinder

class ShapeFinderTest {
    @Test
    fun testSidewaysAdjacency() {
        val array = arrayOf(
            arrayOf(1, 1, 0),
            arrayOf(0, 0, 0),
            arrayOf(0, 1, 1)
        )
        val finder = MatrixShapeFinder(Matrix(array))
        assertEquals(2, finder.findShapes().size)
    }

    @Test
    fun testTopDownAdjacency() {
        val array = arrayOf(
            arrayOf(1, 0, 0),
            arrayOf(1, 0, 1),
            arrayOf(0, 0, 1)
        )
        val finder = MatrixShapeFinder(Matrix(array))
        assertEquals(2, finder.findShapes().size)
    }

    @Test
    fun testDiagonalAdjacency() {
        val array = arrayOf(
            arrayOf(1, 0, 0),
            arrayOf(0, 1, 0),
            arrayOf(0, 0, 1)
        )
        val finder = MatrixShapeFinder(Matrix(array))
        assertEquals(1, finder.findShapes().size)
    }

    @Test
    fun testRandomAdjacency() {
        val array = arrayOf(
            arrayOf(0, 1, 0, 0, 1),
            arrayOf(0, 0, 1, 1, 1),
            arrayOf(0, 1, 0, 0, 0)
        )
        val finder = MatrixShapeFinder(Matrix(array))
        assertEquals(1, finder.findShapes().size)
    }
}
