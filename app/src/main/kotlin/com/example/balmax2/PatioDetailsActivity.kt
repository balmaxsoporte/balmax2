package com.example.balmax2

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.balmax2.adapters.ParkingSpotAdapter
import com.example.balmax2.data.ParkingSpot

class PatioDetailsActivity : AppCompatActivity() {

    private lateinit var parkingSpotAdapter: ParkingSpotAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patio_details)

        val patioNumber = intent.getIntExtra("PATIO_NUMBER", 1)

        // Mostrar número del patio en el título
        title = "Patio $patioNumber"

        // Lista de puestos (simulada)
        val spots = generateDummySpots(20) // 20 puestos por patio

        val listView = findViewById<ListView>(R.id.parkingSpotsListView)

        parkingSpotAdapter = ParkingSpotAdapter(this, spots)
        listView.adapter = parkingSpotAdapter

        // Al hacer clic en un puesto, abrir detalles
        listView.setOnItemClickListener { _, _, position, _ ->
            val spot = spots[position]
            val intent = Intent(this, ParkingSpotDetailsActivity::class.java).apply {
                putExtra("SPOT_NUMBER", spot.number)
                putExtra("PATIO_NUMBER", patioNumber)
            }
            startActivity(intent)
        }
    }

    // Genera datos simulados para mostrar en la lista
    private fun generateDummySpots(count: Int): List<ParkingSpot> {
        val spots = mutableListOf<ParkingSpot>()
        for (i in 1..count) {
            val plate = if (i % 3 == 0) "ARREND-$i" else if (i % 5 == 0) "PART-$i" else ""
            spots.add(ParkingSpot(i, "", "", plate))
        }
        return spots
    }
}
