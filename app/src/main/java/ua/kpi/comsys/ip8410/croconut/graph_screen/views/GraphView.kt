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

    private val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 5F
    }
    private var xMin = -5
    private var xMax = 5
    private var yMin = -5
    private var yMax = 5
    private val axesAngle = PI / 12

    private val xSegCount: Int
        get() = xMax - xMin

    private val ySegCount: Int
        get() = yMax - yMin

    private val xSegSize: Float
        get() = width.toFloat() / xSegCount

    private val ySegSize: Float
        get() = height.toFloat() / ySegCount


    private fun readAttrs(attrs: AttributeSet) {
        with(context.obtainStyledAttributes(attrs, R.styleable.GraphView)) {
            xMin = getInt(R.styleable.GraphView_xMin, xMin)
            xMax = getInt(R.styleable.GraphView_xMax, xMax)
            paint.color = getColor(R.styleable.GraphView_color, paint.color)
            paint.strokeWidth = getFloat(R.styleable.GraphView_stroke, paint.strokeWidth)
            recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        drawAxes(canvas)
    }

    private fun drawAxes(canvas: Canvas) {
        val abscissaX1 = 0f
        val abscissaX2 = width.toFloat()
        val abscissaY = height / 2f
        canvas.drawLine(abscissaX1, abscissaY, abscissaX2, abscissaY, paint)

        val ordinateY1 = 0f
        val ordinateY2 = height.toFloat()
        val ordinateX = width / 2f
        canvas.drawLine(ordinateX, ordinateY1, ordinateX, ordinateY2, paint)

        val xSegLineX = (abscissaX2 + abscissaX1) / 2f
        val ySegLineY = (ordinateY1 + ordinateY2) / 2f

        canvas.drawLine(
            xSegLineX + xSegSize,
            abscissaY + ySegSize / 2,
            xSegLineX + xSegSize,
            abscissaY - ySegSize / 2,
            paint
        )

        canvas.drawLine(
            ordinateX - xSegSize / 2,
            ySegLineY - ySegSize,
            ordinateX + xSegSize / 2,
            ySegLineY - ySegSize,
            paint
        )

        val xArrowX2 = abscissaX2
        val xArrowX1 = (xArrowX2 - xSegSize * cos(axesAngle)).toFloat()
        val xArrowTopY2 = abscissaY
        val xArrowTopY1 = (xArrowTopY2 - xSegSize * sin(axesAngle)).toFloat()
        val xArrowBottomY2 = xArrowTopY2
        val xArrowBottomY1 = (xArrowTopY2 + xSegSize * sin(axesAngle)).toFloat()
        canvas.drawLine(xArrowX1, xArrowTopY1, xArrowX2, xArrowTopY2, paint)
        canvas.drawLine(xArrowX1, xArrowBottomY1, xArrowX2, xArrowBottomY2, paint)

        val yArrowY1 = ordinateY1
        val yArrowY2 = (yArrowY1 + ySegSize * sin(PI / 2 - axesAngle)).toFloat()
        val yArrowLeftX1 = ordinateX
        val yArrowLeftX2 = (yArrowLeftX1 - ySegSize * cos(PI / 2 - axesAngle)).toFloat()
        val yArrowRightX1 = yArrowLeftX1
        val yArrowRightX2 = (yArrowRightX1 + ySegSize * cos(PI / 2 - axesAngle)).toFloat()
        canvas.drawLine(yArrowLeftX1, yArrowY1, yArrowLeftX2, yArrowY2, paint)
        canvas.drawLine(yArrowRightX1, yArrowY1, yArrowRightX2, yArrowY2, paint)
    }
}
