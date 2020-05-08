package ua.dolhanenko.matrixshapefinder

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val array = arrayOf(
            arrayOf(1, 0, 0, 0, 0, 1),
            arrayOf(1, 0, 0, 1, 0, 1),
            arrayOf(0, 1, 0, 0, 0, 1),
            arrayOf(1, 0, 0, 0, 0, 1)
        )
        Toast.makeText(
            this,
            "Number of shapes: ${ShapeFinder(Matrix(array)).findShapes()}",
            Toast.LENGTH_SHORT
        ).show()
    }
}
