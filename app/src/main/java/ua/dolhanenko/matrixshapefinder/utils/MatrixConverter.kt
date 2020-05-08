package ua.dolhanenko.matrixshapefinder.utils

import ua.dolhanenko.matrixshapefinder.data.model.Matrix


object MatrixConverter {
    fun convertMatrixToString(matrix: Matrix): String {
        val matrixArray = matrix.array
        val builder = StringBuilder()
        matrixArray.forEach {
            it.indices.forEach { index ->
                builder.append(it[index].toString())
                if (index != it.size - 1) {
                    builder.append(" ")
                }
            }
            builder.append("\n")
        }
        return builder.toString().trim()
    }
}