package fr.esgi.codesurvival.game

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import fr.esgi.codesurvival.R
import java.util.*
import kotlin.math.min

class Board : AppCompatActivity(){
    private var gridView : GridView? = null
    private var boardElements: ArrayList<BoardElement>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connected_page)

        // Screen size, used to compute values for scaling
        val metrics = DisplayMetrics()

        // Total border spacing will be 5% of the screen
        val verticalSpacing = (0.05 * metrics.heightPixels).toInt()
        val horizontalSpacing = (0.05 * metrics.widthPixels).toInt()
        // Spacing will be the minimum between those two values
        val spacing = min(verticalSpacing, horizontalSpacing)

        gridView = findViewById(R.id.gridView)
        gridView?.gravity = Gravity.CENTER
        gridView?.setPadding(0, verticalSpacing, 0, 0)
        gridView?.verticalSpacing = spacing
        gridView?.horizontalSpacing = spacing

        val nbRows = 5
        val nbCols = 5

        gridView?.numColumns = nbCols


        // Remove the accumulated spacing between elements for width and height
        if(boardElements != null) {
            val adapter = ImgAdapter(
                this,
                boardElements!!,
                metrics.widthPixels - horizontalSpacing * (nbCols + 1),
                metrics.heightPixels - verticalSpacing * (nbRows + 1),
                nbRows,
                nbCols
            )
            gridView?.adapter = adapter
        }




    }


}