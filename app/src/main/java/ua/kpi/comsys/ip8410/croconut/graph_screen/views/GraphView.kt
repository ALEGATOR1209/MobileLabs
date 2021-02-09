package ua.kpi.comsys.ip8410.croconut.graph_screen.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import ua.kpi.comsys.ip8410.croconut.R
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

typealias X = Float
typealias Y = Float
typealias Point = Pair<X, Y>

class GraphView : View {
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

    private val axisPaint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 5F
    }
    private val graphPaint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 5F
    }
    private var xMin = -5
    private var xMax = 5
    private var yMin = -5
    private var yMax = 5
    var points: List<Point> = listOf()
        set(value) {
            field = value
            invalidate()
        }
    private val axesAngle = PI / 12

    private val xSegCount: Int
        get() = xMax - xMin

    private val ySegCount: Int
        get() = yMax - yMin

    private val xSegSize: Float
        get() = width.toFloat() / xSegCount

    private val ySegSize: Float
        get() = height.toFloat() / ySegCount

    private val xZero: Float
        get() = width / 2f

    private val yZero: Float
        get() = height / 2f

    private val segSymbolSize: Float
        get() = width / 15f


    private fun readAttrs(attrs: AttributeSet) {
        with(context.obtainStyledAttributes(attrs, R.styleable.GraphView)) {
            xMin = getInt(R.styleable.GraphView_xMin, xMin)
            xMax = getInt(R.styleable.GraphView_xMax, xMax)
            yMin = getInt(R.styleable.GraphView_yMin, yMin)
            yMax = getInt(R.styleable.GraphView_yMax, yMax)
            axisPaint.color = getColor(R.styleable.GraphView_color, axisPaint.color)
            axisPaint.strokeWidth = getFloat(R.styleable.GraphView_stroke, axisPaint.strokeWidth)
            graphPaint.color = getColor(R.styleable.GraphView_graphColor, graphPaint.color)
            graphPaint.strokeWidth = getFloat(
                R.styleable.GraphView_graphWidth,
                graphPaint.strokeWidth
            )
            recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        drawAxes(canvas)
        if (points.isNotEmpty()) drawPoints(canvas)
    }

    private fun drawAxes(canvas: Canvas) {
        val abscissaX1 = 0f
        val abscissaX2 = width.toFloat()
        canvas.drawLine(abscissaX1, yZero, abscissaX2, yZero, axisPaint)

        val ordinateY1 = 0f
        val ordinateY2 = height.toFloat()
        canvas.drawLine(xZero, ordinateY1, xZero, ordinateY2, axisPaint)

        val xSegLineX = (abscissaX2 + abscissaX1) / 2f
        val ySegLineY = (ordinateY1 + ordinateY2) / 2f

        canvas.drawLine(
            xSegLineX + xSegSize,
            yZero + segSymbolSize / 4,
            xSegLineX + xSegSize,
            yZero - segSymbolSize / 4,
            axisPaint
        )

        canvas.drawLine(
            xZero - segSymbolSize / 4,
            ySegLineY - ySegSize,
            xZero + segSymbolSize / 4,
            ySegLineY - ySegSize,
            axisPaint
        )

        val xArrowX2 = abscissaX2
        val xArrowX1 = (xArrowX2 - segSymbolSize * cos(axesAngle)).toFloat()
        val xArrowTopY2 = yZero
        val xArrowTopY1 = (xArrowTopY2 - segSymbolSize * sin(axesAngle)).toFloat()
        val xArrowBottomY2 = xArrowTopY2
        val xArrowBottomY1 = (xArrowTopY2 + segSymbolSize * sin(axesAngle)).toFloat()
        canvas.drawLine(xArrowX1, xArrowTopY1, xArrowX2, xArrowTopY2, axisPaint)
        canvas.drawLine(xArrowX1, xArrowBottomY1, xArrowX2, xArrowBottomY2, axisPaint)

        val yArrowY1 = ordinateY1
        val yArrowY2 = (yArrowY1 + segSymbolSize * sin(PI / 2 - axesAngle)).toFloat()
        val yArrowLeftX1 = xZero
        val yArrowLeftX2 = (yArrowLeftX1 - segSymbolSize * cos(PI / 2 - axesAngle)).toFloat()
        val yArrowRightX1 = yArrowLeftX1
        val yArrowRightX2 = (yArrowRightX1 + segSymbolSize * cos(PI / 2 - axesAngle)).toFloat()
        canvas.drawLine(yArrowLeftX1, yArrowY1, yArrowLeftX2, yArrowY2, axisPaint)
        canvas.drawLine(yArrowRightX1, yArrowY1, yArrowRightX2, yArrowY2, axisPaint)
    }

    private fun drawPoints(canvas: Canvas) {
        var prev: Point = points[0]
        for (i in points.indices) {
            val cur = points[i]
            canvas draw cur
            canvas.drawLine(prev, cur)
            prev = cur
        }
    }

    private fun Point.translate(): Point {
        val x = first * xSegSize + xZero
        val y = yZero - second * ySegSize
        return x to y
    }

    private infix fun Canvas.draw(point: Point) {
        val (x, y) = point.translate()
        drawPoint(x, y, graphPaint)
    }

    private fun Canvas.drawLine(p1: Point, p2: Point) {
        val (x1, y1) = p1.translate()
        val (x2, y2) = p2.translate()
        drawLine(x1, y1, x2, y2, graphPaint)
    }
}
