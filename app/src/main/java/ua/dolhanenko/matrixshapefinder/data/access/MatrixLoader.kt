package ua.dolhanenko.matrixshapefinder.data.access


import android.content.Context
import android.os.Handler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ua.dolhanenko.matrixshapefinder.data.model.Matrix


class MatrixLoader(val context: Context) {
    private val handler = Handler()

    companion object {
        private const val ASSETS_PATH = "matrices/"
    }

    fun loadAvailableMatrices(onLoaded: (List<Matrix>) -> Unit) {
        GlobalScope.launch {
            try {
                val assetsList = context.assets.list(ASSETS_PATH)
                val resultList = mutableListOf<Matrix>()
                try {
                    assetsList?.forEach {
                        resultList.add(
                            Matrix(
                                parseMatrixFromAssets("$ASSETS_PATH$it"),
                                it.replace(".txt", "")
                            )
                        )
                    }
                    handler.post { onLoaded(resultList) }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun parseMatrixFromAssets(path: String): Array<Array<Int>> {
        val stream = context.assets.open(path)
        val content = String(stream.readBytes())
        val rows = content.split("\n")
        val resultArray: MutableList<Array<Int>> = mutableListOf()
        for (row in rows) {
            val values = row.split(" ")
            val rowList: MutableList<Int> = mutableListOf()
            values.forEach { value ->
                try {
                    rowList.add(value.toInt())
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            resultArray.add(rowList.toTypedArray())
        }
        return resultArray.toTypedArray()
    }
}