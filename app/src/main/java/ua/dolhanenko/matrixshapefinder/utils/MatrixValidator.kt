package ua.dolhanenko.matrixshapefinder.utils

import ua.dolhanenko.matrixshapefinder.data.model.Matrix


object MatrixValidator {
    /**
     * Returns true, if the matrix is valid: number of elements in each row is indifferent.
     */
    fun validateMatrix(matrix: Matrix): Boolean {
        var isValid = true
        if (matrix.array.isNotEmpty()) {
            val rowSize = matrix.array[0].size
            for (subArray in matrix.array) {
                if (subArray.size != rowSize) {
                    isValid = false
                    break
                }
            }
        }
        return isValid
    }
}