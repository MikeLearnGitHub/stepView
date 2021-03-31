package com.example.setpview

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.widget.RelativeLayout
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var stepView: StepView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stepView = findViewById(R.id.myStepView)
        val animator: ValueAnimator = ObjectAnimator.ofFloat(0F, 8000F)
        animator.interpolator = DecelerateInterpolator()
        animator.duration = 5000
        animator.addUpdateListener {
            val currentStep: Float = it.animatedValue as Float
            stepView.setCurrentSteps(currentStep.toInt())
        }
        animator.start()
        val bell:RelativeLayout = findViewById(R.id.btnBellIcon)
        bell.setOnClickListener {
            Toast.makeText(this,"hello", Toast.LENGTH_SHORT).show()
        }
    }
}