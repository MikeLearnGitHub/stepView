package com.example.setpview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class StepView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mBaseLayer_color = Color.YELLOW
    private var mOverlay_color = Color.BLUE
    private var mBorderWidth : Float
    private var mStepTextColor = Color.BLUE
    private var mStepTextSize : Float
    private val mOuterPaint = Paint()
    private var mCurrentSteps : Int
    private val maxSteps = 10000
    private var mText : String = ""
//    private val mInnerPaint = Paint()
    private var mTextPaint = Paint()

    init {
        val arry: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.StepView)
        mBaseLayer_color = arry.getColor(R.styleable.StepView_baseLayer_color, mBaseLayer_color)
        mOverlay_color = arry.getColor(R.styleable.StepView_overlay_color, mBaseLayer_color)
        mBorderWidth = arry.getDimension(R.styleable.StepView_border_width, 0F)
        mStepTextColor = arry.getColor(R.styleable.StepView_step_text_color, mStepTextColor)
        mStepTextSize = arry.getDimension(R.styleable.StepView_step_text_size, 0F)
        mCurrentSteps = arry.getInt(R.styleable.StepView_step_text_number, 0)
        arry.recycle()
        mOuterPaint.isAntiAlias = true
        mOuterPaint.color = mBaseLayer_color
        mOuterPaint.strokeWidth = mBorderWidth
        mOuterPaint.style = Paint.Style.STROKE
        mOuterPaint.strokeCap = Paint.Cap.ROUND

//        mInnerPaint.isAntiAlias = true
//        mInnerPaint.color = mOverlay_color
//        mInnerPaint.strokeWidth = mBorderWidth
//        mInnerPaint.style = Paint.Style.STROKE
//        mInnerPaint.strokeCap = Paint.Cap.ROUND
        mTextPaint.isAntiAlias = true
        mTextPaint.color = mOverlay_color
        mTextPaint.textSize = mStepTextSize

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width: Int = MeasureSpec.getSize(widthMeasureSpec)
        val height: Int = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(
            if (width > height) height else width,
            if (width > height) height else width
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val rectF: RectF = RectF(
            mBorderWidth/2,
            mBorderWidth/2,
            width.toFloat() - mBorderWidth/2,
            width.toFloat() - mBorderWidth/2)
        mOuterPaint.color = mBaseLayer_color
        canvas?.drawArc(rectF,135F,270F,false,mOuterPaint)
        mOuterPaint.color = mOverlay_color
        if (maxSteps == 0) return
//        canvas?.drawArc(rectF,135F,270F * (mCurrentSteps/maxSteps), false, mOuterPaint)
        val ratio: Float = mCurrentSteps.toFloat() / maxSteps
        canvas?.drawArc(rectF,135F,270F * ratio, false, mOuterPaint)
        mText = mCurrentSteps.toString()
        val rect: Rect = Rect()
        mTextPaint.getTextBounds(mText,0,mText.length,rect)
        val dx: Float = (width/2 - rect.width()/2).toFloat()
        val fontMetricsInt: Paint.FontMetricsInt = mTextPaint.fontMetricsInt
        val dy: Float = (fontMetricsInt.bottom - fontMetricsInt.top).toFloat()
        val baseLine: Float = height/2 + dy/2
        canvas?.drawText(mText, dx, baseLine, mTextPaint)
    }

    public fun setCurrentSteps( currentStep: Int) {
        mCurrentSteps = currentStep
        invalidate()
    }
}