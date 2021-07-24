package fr.esgi.codesurvival.game

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import java.util.*

class ImgAdapter(
    private val context: Context,
    boardElements: ArrayList<BoardElement>,
    screenWidth: Int,
    screenHeight: Int,
    nbRows: Int,
    nbCols: Int
) : BaseAdapter() {

    private val nbRows: Int
    //, nbCol;
    private val cellWidth: Int
    private val cellHeight: Int
    private val boardElements: MutableList<BoardElement>

    override fun getCount(): Int {
        return boardElements.size
    }

    override fun getItem(position: Int): Any {
        return boardElements[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        val imageView = ImageView(context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        imageView.adjustViewBounds = true
        //imageView.setPadding(15, 0, 15, 0);

        /* boardElement size */

        val layoutParamsForBoardElements: AbsListView.LayoutParams = AbsListView.LayoutParams(cellWidth, cellHeight)
        imageView.layoutParams = layoutParamsForBoardElements

        //imageView.setImageResource(boardElement.getImageBack()) Image ressource from board element
        return imageView
    }

    companion object {
        private const val TAG = "ImgAdapter"
    }

    init {
        this.boardElements = boardElements
        this.nbRows = nbRows
        cellWidth = screenWidth / nbCols
        cellHeight = screenHeight / nbRows
    }
}