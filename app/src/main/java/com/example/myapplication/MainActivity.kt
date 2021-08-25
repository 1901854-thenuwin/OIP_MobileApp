package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var progr = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val button_increase = findViewById(R.id.button_increase) as Button
//        val button_decrease = findViewById(R.id.button_decrease) as Button
//
//        button_increase.setOnClickListener {
//            if (progr <= 90) {
//                progr += 10
//                updateProgressBar()
//            }
//        }
//        button_decrease.setOnClickListener {
//            if (progr >= 10) {
//                progr -= 10
//                updateProgressBar()
//            }
//        }
    }
//    private fun updateProgressBar() {
//        progress_bar.progress = progr
//        text_view_progress.text = "$progr%"
//
//    }
}