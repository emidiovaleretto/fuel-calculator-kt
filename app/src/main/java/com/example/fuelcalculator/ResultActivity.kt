package com.example.fuelcalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

const val RESULT_KEY = "ResultActivity.Key"

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        val result = intent.getFloatExtra(RESULT_KEY, 0f)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        resultTextView.text = result.toString()

        val fuelTextView = findViewById<TextView>(R.id.fuelTextView)
        val efficiencyTextView = findViewById<TextView>(R.id.efficiencyTextView)
        val distanceTextView = findViewById<TextView>(R.id.distanceTextView)

        val extras : Bundle? = intent.extras;
        val fuel = extras?.getString("fuel")
        val efficiency = extras?.getFloat("efficiency")
        val distance = extras?.getFloat("distance")

        fuelTextView.text = fuel
        efficiencyTextView.text = efficiency.toString()
        distanceTextView.text = distance.toString()

        val btnStartOver = findViewById<Button>(R.id.btnStartOver)

        btnStartOver.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}