package com.example.setpview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View

// tutorial https://www.raywenderlich.com/142-android-custom-view-tutorial
class NotificationView : View {
    /*
    1. constructor(context: Context)
        To create a new View instance from Kotlin code, it needs the Activity context.
    2. constructor(context: Context, attrs: AttributeSet)
        To create a new View instance from XML.
    3. constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
        To create a new view instance from XML with a style from theme attribute.
    4. constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int)
        To create a new view instance from XML with a style from theme attribute and/or style resource.
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val arrs:TypedArray = context.obtainStyledAttributes(attrs, R.styleable.NotificationView)
        textSize = arrs.getDimension(R.styleable.NotificationView_notice_number_size, 0F)
    }

    private val paint = Paint()
    private val texPaint = Paint()
    private var backGroundColor = Color.RED
    private var borderColor = Color.BLACK
    private var size = 320
    private var textSize = 100F

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawNoticficationCircle(canvas)
        drawNubmer(canvas)
    }

    private fun drawNoticficationCircle(canvas: Canvas){
        paint.isAntiAlias = true
        paint.color = backGroundColor
        paint.style = Paint.Style.FILL
        val radius = size/2f
        canvas.drawCircle(size/2f, size/2f, radius, paint)
    }

    private fun drawNubmer(canvas: Canvas) {
        paint.color = borderColor
        paint.textSize = textSize
        canvas.drawText("1", size/2f, size/2f, paint)
//        paint.color = borderColor
//        paint.style = Paint.Style.FILL
//
//// 2
//        val leftEyeRect = RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f)
//
//        canvas.drawOval(leftEyeRect, paint)
    }
}