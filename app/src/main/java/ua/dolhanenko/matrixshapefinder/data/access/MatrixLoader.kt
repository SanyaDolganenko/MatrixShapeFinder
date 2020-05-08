package ua.dolhanenko.matrixshapefinder.data.access


import android.os.Handler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ua.dolhanenko.matrixshapefinder.data.model.Matrix


class MatrixLoader {
    private val handler = Handler()
    fun loadAvailableMatrices(onLoaded: (List<Matrix>) -> Unit) {
        GlobalScope.launch {
            //TODO retrieve from assets.
            val array = arrayOf(
                arrayOf(1, 0, 0, 0, 0, 1),
                arrayOf(1, 0, 0, 1, 0, 1),
                arrayOf(0, 1, 0, 0, 0, 1),
                arrayOf(1, 0, 0, 0, 0, 1)
            )
            handler.post { onLoaded(listOf(Matrix(array))) }
        }
    }
}