package com.example.balmax2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ParkingSpotDetailsActivity : AppCompatActivity() {

    private val vehicleTypes = arrayOf(
        "Camión", "Camión 3/4", "Auto", "Camioneta", "Van", "Maquinaria Pesada"
    )

    private val trailerOptions = arrayOf(
        "Ninguno", "Rampla", "Termo", "Cama Baja", "Container", "Tolba", "Estanque", "Carro"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_spot_details)

        val spotNumber = intent.getIntExtra("SPOT_NUMBER", 1)
        val patioNumber = intent.getIntExtra("PATIO_NUMBER", 1)

        // Configurar título
        title = "Puesto $spotNumber - Patio $patioNumber"

        // Referencias UI
        val vehicleTypeSpinner = findViewById<Spinner>(R.id.vehicleTypeSpinner)
        val plateEditText = findViewById<EditText>(R.id.plateEditText)
        val trailerSpinner = findViewById<Spinner>(R.id.trailerSpinner)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val largePlateTextView = findViewById<TextView>(R.id.largePlateTextView)

        // Adaptadores para spinners
        ArrayAdapter(this, android.R.layout.simple_spinner_item, vehicleTypes).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            vehicleTypeSpinner.adapter = it
        }

        ArrayAdapter(this, android.R.layout.simple_spinner_item, trailerOptions).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            trailerSpinner.adapter = it
        }

        // Mostrar patente grande al escribir
        plateEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                largePlateTextView.visibility = TextView.VISIBLE
                largePlateTextView.text = plateEditText.text
            } else {
                largePlateTextView.visibility = TextView.GONE
            }
        }

        plateEditText.setOnEditorActionListener { _, _, _ ->
            largePlateTextView.visibility = TextView.GONE
            false
        }

        // Guardar datos
        saveButton.setOnClickListener {
            val selectedVehicle = vehicleTypeSpinner.selectedItem.toString()
            val plate = plateEditText.text.toString().trim()
            val selectedTrailer = trailerSpinner.selectedItem.toString()

            if (plate.isEmpty()) {
                Toast.makeText(this, "Por favor ingrese una patente", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Guardado: $selectedVehicle - $plate", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
