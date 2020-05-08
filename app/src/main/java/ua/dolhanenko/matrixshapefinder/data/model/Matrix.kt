package ua.dolhanenko.matrixshapefinder.data.model


class Matrix(val array: Array<Array<Int>>) {
    fun valueOf(vertex: Vertex): Int {
        try {
            return array[vertex.offsetY][vertex.offsetX]
        } catch (e: IndexOutOfBoundsException) {
        }
        return -1
    }
}