package ua.dolhanenko.matrixshapefinder.ui

import android.os.Bundle
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ua.dolhanenko.matrixshapefinder.R
import ua.dolhanenko.matrixshapefinder.data.access.MatrixLoader
import ua.dolhanenko.matrixshapefinder.data.model.Matrix

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MatrixLoader().loadAvailableMatrices {
            val mapList = mutableListOf<HashMap<String, String>>()
            it.forEach { matrix ->
                mapList.add(
                    hashMapOf(
                        Pair("MATRIX", matrixToString(matrix)),
                        Pair("NAME", "Matrix # ${it.indexOf(matrix)}")
                    )
                )
            }
            matrix_selector.adapter = SimpleAdapter(
                this, mapList, R.layout.matrix_item,
                arrayOf("MATRIX", "NAME"), intArrayOf(R.id.matrix_preview, R.id.matrix_name)
            )
            matrix_selector.setSelection(0)
        }
    }

    private fun matrixToString(matrix: Matrix): String {
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
