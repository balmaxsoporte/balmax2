package com.example.balmax2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.balmax2.R
import com.example.balmax2.data.User

class UserAdapter(context: Context, private val users: MutableList<User>) :
    ArrayAdapter<User>(context, 0, users) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_user, parent, false)
        val user = users[position]

        val usernameText = view.findViewById<TextView>(R.id.usernameTextView)
        val permissionsText = view.findViewById<TextView>(R.id.permissionsTextView)

        usernameText.text = user.username

        val permissions = mutableListOf<String>()
        if (user.canEditUsers) permissions.add("Editar Usuarios")
        if (user.canEditSpots) permissions.add("Editar Puestos")
        if (user.canEditPatioLicenses) permissions.add("Editar Patentes")

        permissionsText.text = permissions.joinToString(", ")

        return view
    }
}
