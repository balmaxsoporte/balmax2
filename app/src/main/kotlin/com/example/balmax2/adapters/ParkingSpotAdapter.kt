package com.example.balmax2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.balmax2.R
import com.example.balmax2.data.ParkingSpot
import com.example.balmax2.utils.ColorUtils

class ParkingSpotAdapter(context: Context, private val spots: List<ParkingSpot>) :
    ArrayAdapter<ParkingSpot>(context, 0, spots) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_parking_spot, parent, false)

        val spot = spots[position]

        val spotNumber = view.findViewById<TextView>(R.id.spotNumberTextView)
        val plateText = view.findViewById<TextView>(R.id.plateTextView)
        val editButton = view.findViewById<ImageButton>(R.id.editButton)
        val ticketButton = view.findViewById<ImageButton>(R.id.ticketButton)

        spotNumber.text = "Puesto ${spot.number}"
        plateText.text = "Placa: ${spot.plate}"

        // Cambiar color de fondo según tipo de patente
        when {
            spot.plate.startsWith("ARREND") -> view.setBackgroundColor(ColorUtils.getARRENDColor(context))
            spot.plate.startsWith("PART") -> view.setBackgroundColor(ColorUtils.getPARTColor(context))
            else -> view.setBackgroundColor(ColorUtils.getDefaultColor(context))
        }

        // Solo mostrar botón de edición si hay permiso (simulado)
        editButton.visibility = View.VISIBLE
        editButton.setOnClickListener {
            // Aquí se abriría la ventana de edición
        }

        ticketButton.setOnClickListener {
            // Marcar como ticket
        }

        return view
    }
}
