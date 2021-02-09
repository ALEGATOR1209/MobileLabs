package ua.kpi.comsys.ip8410.croconut.graph_screen.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import ua.kpi.comsys.ip8410.croconut.R

class ChartView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        readAttrs(attrs)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        if (attrs != null) readAttrs(attrs)
    }

    private val paint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.STROKE
        strokeWidth = 50f
    }

    private lateinit var rectF: RectF

    var dataset: List<Data> = listOf(
        Data(500, Color.RED),
        Data(300, Color.GREEN),
        Data(200, Color.BLUE),
    )
        set(value) {
            field = value
            invalidate()
        }
    private val totalSum: Int
        get() = dataset.sumBy(Data::value)

    private fun readAttrs(attrs: AttributeSet) {
        with(context.obtainStyledAttributes(attrs, R.styleable.ChartView)) {
            paint.strokeWidth = getFloat(R.styleable.ChartView_chartWidth, paint.strokeWidth)
            recycle()
        }

        val paintWidth = paint.strokeWidth
        rectF = RectF(
            0f + paintWidth / 2,
            0f + paintWidth / 2,
            width - paintWidth / 2f,
            height - paintWidth / 2f
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val paintWidth = paint.strokeWidth
        rectF = RectF(
            0f + paintWidth / 2,
            0f + paintWidth / 2,
            width - paintWidth / 2f,
            height - paintWidth / 2f
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var angle = 0f
        dataset.forEach {
            val a = it.angle
            paint.color = it.color
            canvas?.drawArc(rectF, angle, a, false, paint)
            angle += a
        }
    }

    private val Data.angle: Float
        get() = (value.toFloat() / totalSum) * (-360)

    data class Data(val value: Int, val color: Int)
}