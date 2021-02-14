package ua.kpi.comsys.ip8410.feature_films.ui.recycler

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.ColorDrawable
import android.util.TypedValue
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ua.kpi.comsys.ip8410.feature_films.R
import kotlin.math.abs


abstract class SwipeHandler(
    private val context: Context
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    private val colorError by lazy {
        val typedValue = TypedValue()
        context.theme.resolveAttribute(R.attr.colorError, typedValue, true)
        typedValue.data
    }
    private val deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_delete)
    private val intrinsicWidth = deleteIcon?.intrinsicWidth ?: 0
    private val intrinsicHeight = deleteIcon?.intrinsicHeight ?: 0
    private val background = ColorDrawable()
    private val clearPaint = Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val view = viewHolder.itemView
        val isCanceled = dX == 0f && !isCurrentlyActive

        if (isCanceled) {
            clearCanvas(c,
                view.right + dX,
                view.top.toFloat(),
                view.right.toFloat(),
                view.bottom.toFloat()
            )
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            return
        }

        background.color = colorError
        background.setBounds(view.right + dX.toInt(), view.top, view.right, view.bottom)
        background.draw(c)

        if (abs(dX) > view.width * 0.1 + intrinsicWidth) {
            val deleteIconTop = view.top + (view.height - intrinsicHeight) / 2
            val deleteIconLeft = (view.right - intrinsicWidth - view.width * 0.1).toInt()
            val deleteIconRight = (view.right - view.width * 0.1).toInt()
            val deleteIconBottom = deleteIconTop + intrinsicHeight

            deleteIcon?.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
            deleteIcon?.draw(c)
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) = false

    private fun clearCanvas(c: Canvas?, left: Float, top: Float, right: Float, bottom: Float) {
        c?.drawRect(left, top, right, bottom, clearPaint)
    }
}