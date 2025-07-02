package com.example.balmax2

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.balmax2.adapters.ParkingSpotAdapter
import com.example.balmax2.data.ParkingSpot
import com.example.balmax2.data.ParkingSpotRepository

class PatioDetailsActivity : AppCompatActivity() {

    private lateinit var parkingSpotAdapter: ParkingSpotAdapter
    private lateinit var parkingSpotRepository: ParkingSpotRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patio_details)

        // Obtener número del patio desde Intent
        val patioNumber = intent.getIntExtra("PATIO_NUMBER", 1)
        title = "Patio $patioNumber"

        // Inicializar repositorio de puestos
        parkingSpotRepository = ParkingSpotRepository(this)
        parkingSpotRepository.open()

        // Obtener puestos del patio desde la base de datos
        val spots = parkingSpotRepository.getSpotsByPatio(patioNumber).toMutableList()

        // Configurar adaptador
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

    override fun onDestroy() {
        super.onDestroy()
        parkingSpotRepository.close()
    }
}
