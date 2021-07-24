package fr.esgi.codesurvival.game

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import fr.esgi.codesurvival.R
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.util.*
import kotlin.math.min

class Board : AppCompatActivity(){
    companion object{
        const val TAG = "BoardActivity"
    }
    private var gridView : GridView? = null
    private var mobState : MobState? = null
    private var boardElements: MutableList<BoardElement> = mutableListOf()
    private val coroutineScope: CoroutineScope = CoroutineScope(CoroutineName(TAG))

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

        this.coroutineScope.launch(IO){
            val async1 = async {
                val callback = this@Board.mobState?.let {
                    MapLoadingTask(this@Board.boardElements, it) {
                        Log.d(TAG, "onCreate: $it")
                    }
                }
                callback?.let { BoardElementRepository.getBoardElement(it) }
                delay(1000)
            }
            async1.await()
        }

        // Remove the accumulated spacing between elements for width and height
        val adapter = ImgAdapter(
            this,
            boardElements as ArrayList<BoardElement>,
            metrics.widthPixels - horizontalSpacing * (nbCols + 1),
            metrics.heightPixels - verticalSpacing * (nbRows + 1),
            nbRows,
            nbCols
        )
        gridView?.adapter = adapter


    }


}