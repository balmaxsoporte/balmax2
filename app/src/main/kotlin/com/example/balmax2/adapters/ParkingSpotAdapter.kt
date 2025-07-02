package com.example.balmax2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.example.balmax2.R
import com.example.balmax2.data.ParkingSpot

class ParkingSpotAdapter(context: Context, private val spots: MutableList<ParkingSpot>) :
    ArrayAdapter<ParkingSpot>(context, 0, spots) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_parking_spot, parent, false)
        val spot = spots[position]

        val spotNumber = view.findViewById<TextView>(R.id.spotNumberTextView)
        val plateText = view.findViewById<TextView>(R.id.plateTextView)
        val editButton = view.findViewById<ImageButton>(R.id.editButton)
        val ticketButton = view.findViewById<ImageButton>(R.id.ticketButton)

        // Mostrar número de puesto y patente
        spotNumber.text = "Puesto ${spot.number}"
        plateText.text = if (spot.plate.isNotBlank()) "Placa: ${spot.plate}" else "Sin registro"

        // Cambiar color de fondo según tipo de patente
        when {
            isArrendatario(spot.plate) -> {
                view.setBackgroundColor(ColorUtils.getARRENDColor(context))
            }
            spot.plate.isNotBlank() -> {
                view.setBackgroundColor(ColorUtils.getPARTColor(context))
            }
            else -> {
                view.setBackgroundColor(ColorUtils.getDefaultColor(context))
            }
        }

        // Configurar visibilidad de botones según permisos del usuario (simulado)
        editButton.visibility = View.VISIBLE
        editButton.setOnClickListener {
            // Aquí se abriría la ventana de edición
        }

        ticketButton.setOnClickListener {
            // Acción al marcar como ticket
        }

        return view
    }

    // Simulación de verificación de arrendatario (en producción se usa desde archivo .txt)
    private fun isArrendatario(plate: String): Boolean {
        return plate.startsWith("ARREND")
    }
}
