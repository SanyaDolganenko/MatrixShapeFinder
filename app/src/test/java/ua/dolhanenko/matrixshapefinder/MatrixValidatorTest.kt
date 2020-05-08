package ua.dolhanenko.matrixshapefinder

import junit.framework.Assert
import org.junit.Test
import ua.dolhanenko.matrixshapefinder.data.model.Matrix
import ua.dolhanenko.matrixshapefinder.utils.MatrixValidator

class MatrixValidatorTest {

    @Test
    fun testDetectsValidMatrix() {
        val array = arrayOf(
            arrayOf(1, 0, 0, 1),
            arrayOf(0, 1, 0, 0),
            arrayOf(0, 1, 0, 1)
        )
        Assert.assertEquals(true, MatrixValidator.validateMatrix(Matrix(array)))
    }

    @Test
    fun testDetectsInvalidMatrix() {
        val array = arrayOf(
            arrayOf(1, 0, 0, 1),
            arrayOf(0, 1, 0),
            arrayOf(0, 1)
        )
        Assert.assertEquals(false, MatrixValidator.validateMatrix(Matrix(array)))
    }
}