package com.example.fuelcalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

const val PETROL_PRICE = 1.78
const val ETHANOL_PRICE = 1.67
const val DIESEL_PRICE = 1.79

class HomeActivity : AppCompatActivity() {

    private lateinit var btnPetrol: FrameLayout
    private lateinit var btnEthanol: FrameLayout
    private lateinit var btnDiesel: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        btnPetrol = findViewById(R.id.petrol)
        btnEthanol = findViewById(R.id.ethanol)
        btnDiesel = findViewById(R.id.diesel)

        val fuelEfficiency = findViewById<TextInputEditText>(R.id.textInputFuelEfficiencyEditText)
        val distance = findViewById<TextInputEditText>(R.id.textInputDistanceEditText)
        val fuelPrice = findViewById<TextInputEditText>(R.id.fuelPriceEditText)

        val btnCalculate = findViewById<Button>(R.id.btnStartOver)

        val extras = Bundle()

        btnCalculate.setOnClickListener {

            try {

                val fuelEfficiencyValue = fuelEfficiency.text.toString().toFloat()
                val distanceValue = distance.text.toString().toInt()
                val fuelPriceValue = fuelPrice.text.toString().toFloat()

                val result =
                    calculateFuelEfficiency(fuelEfficiencyValue, distanceValue, fuelPriceValue)

                val intent = Intent(this, ResultActivity::class.java)

                extras.putFloat("efficiency", fuelEfficiencyValue)
                extras.putFloat("distance", distanceValue.toFloat())

                intent.putExtra(RESULT_KEY, result)
                intent.putExtras(extras)
                startActivity(intent)

            } catch (e: NumberFormatException) {
                Snackbar.make(
                    btnCalculate,
                    getString(R.string.error_invalid_input),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        btnPetrol.setOnClickListener {
            selectedButton(btnPetrol)
            fuelPrice.setText(PETROL_PRICE.toString())
            extras.putString("fuel", "Petrol")
            unselectedButton(btnEthanol, btnDiesel)
        }

        btnEthanol.setOnClickListener {
            selectedButton(btnEthanol)
            extras.putString("fuel", "Ethanol")
            fuelPrice.setText(ETHANOL_PRICE.toString())
            unselectedButton(btnPetrol, btnDiesel)
        }

        btnDiesel.setOnClickListener {
            selectedButton(btnDiesel)
            extras.putString("fuel", "Diesel")
            fuelPrice.setText(DIESEL_PRICE.toString())
            unselectedButton(btnEthanol, btnPetrol)
        }
    }


    private fun selectedButton(button: FrameLayout) {
        button.setBackgroundResource(R.drawable.fuel_btn_selected)
    }

    private fun unselectedButton(vararg buttons: FrameLayout) {
        buttons.forEach { it.setBackgroundResource(R.drawable.fuel_btn_unselected) }
    }

    private fun calculateFuelEfficiency(
        fuelEfficiency: Float, distance: Int, fuelPrice: Float
    ): Float {
        return (distance / fuelEfficiency) * fuelPrice
    }
}