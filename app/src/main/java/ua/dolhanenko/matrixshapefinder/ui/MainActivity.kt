package ua.dolhanenko.matrixshapefinder.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ua.dolhanenko.matrixshapefinder.R
import ua.dolhanenko.matrixshapefinder.data.access.MatrixLoader
import ua.dolhanenko.matrixshapefinder.data.model.Matrix
import ua.dolhanenko.matrixshapefinder.utils.MatrixConverter
import ua.dolhanenko.matrixshapefinder.utils.MatrixShapeFinder
import ua.dolhanenko.matrixshapefinder.utils.MatrixValidator

class MainActivity : AppCompatActivity() {
    companion object {
        const val ADAPTER_FIELD_MATRIX_VALUE = "MATRIX"
        const val ADAPTER_FIELD_MATRIX_NAME = "NAME"
    }

    private var matricesList = listOf<Matrix>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        attachListeners()
        initiateMatrixLoading()
    }

    private fun initiateMatrixLoading() {
        MatrixLoader(this).loadAvailableMatrices {
            matricesList = it
            updateSelectorAdapter()
        }
    }

    private fun attachListeners() {
        button_find_shapes.setOnClickListener {
            val selection = matrix_selector.selectedItemPosition
            val matrix = matricesList[selection]
            val result = MatrixShapeFinder(matrix).findShapes().size
            text_view_result.text = getString(R.string.result_text, result.toString())
        }
        matrix_selector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                displayMatrixValidState()
                val matrix = matricesList[p2]
                matrix_field.text = MatrixConverter.convertMatrixToString(matrix)
                if (!MatrixValidator.validateMatrix(matrix)) {
                    displayMatrixInvalidState()
                }
            }
        }
    }

    private fun displayMatrixValidState() {
        text_view_result.text = getString(R.string.result_text, "")
        button_find_shapes.isEnabled = true
        matrix_field.setTextColor(Color.BLACK)
    }

    private fun displayMatrixInvalidState() {
        text_view_result.text = getString(R.string.invalid_matrix)
        button_find_shapes.isEnabled = false
        matrix_field.setTextColor(Color.RED)
    }

    private fun updateSelectorAdapter() {
        val mapList = mutableListOf<HashMap<String, String>>()
        matricesList.forEach { matrix ->
            mapList.add(
                hashMapOf(
                    Pair(ADAPTER_FIELD_MATRIX_VALUE, MatrixConverter.convertMatrixToString(matrix)),
                    Pair(ADAPTER_FIELD_MATRIX_NAME, matrix.name)
                )
            )
        }
        matrix_selector.adapter = SimpleAdapter(
            this,
            mapList,
            R.layout.matrix_item,
            arrayOf(ADAPTER_FIELD_MATRIX_VALUE, ADAPTER_FIELD_MATRIX_NAME),
            intArrayOf(R.id.matrix_preview, R.id.matrix_name)
        )
        matrix_selector.setSelection(0)
    }
}
