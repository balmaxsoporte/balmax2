package com.example.balmax2

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.balmax2.adapters.UserAdapter
import com.example.balmax2.data.User
import com.example.balmax2.data.UserRepository
import com.example.balmax2.utils.FileUtils
import java.util.*

class UserManagementActivity : AppCompatActivity() {

    private lateinit var userRepository: UserRepository
    private lateinit var userAdapter: UserAdapter
    private lateinit var userListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_management)

        // Inicializar repositorio de usuarios
        userRepository = UserRepository(this)

        // Cargar usuario admin por defecto si no existe
        if (userRepository.getAllUsers().isEmpty()) {
            userRepository.addUser(
                User(0, "admin", "admin", true, true, true)
            )
        }

        // Configurar adaptador
        userAdapter = UserAdapter(this, userRepository.getAllUsers().toMutableList())
        userListView = findViewById(R.id.userListView)
        userListView.adapter = userAdapter

        setupAddUserForm()
    }

    private fun setupAddUserForm() {
        val newUsernameEditText = findViewById<EditText>(R.id.newUsernameEditText)
        val newPasswordEditText = findViewById<EditText>(R.id.newPasswordEditText)
        val editUsersCheckbox = findViewById<CheckBox>(R.id.editUsersCheckbox)
        val editSpotsCheckbox = findViewById<CheckBox>(R.id.editSpotsCheckbox)
        val editPatioLicensesCheckbox = findViewById<CheckBox>(R.id.editPatioLicensesCheckbox)
        val addUserButton = findViewById<Button>(R.id.addButton)

        addUserButton.setOnClickListener {
            val username = newUsernameEditText.text.toString().trim()
            val password = newPasswordEditText.text.toString().trim()
            val canEditUsers = editUsersCheckbox.isChecked
            val canEditSpots = editSpotsCheckbox.isChecked
            val canEditPatioLicenses = editPatioLicensesCheckbox.isChecked

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newUser = User(0, username, password, canEditUsers, canEditSpots, canEditPatioLicenses)
            userRepository.addUser(newUser)
            userAdapter.updateUsers(userRepository.getAllUsers().toMutableList())

            // Exportar patentes a txt
            val allUsers = userRepository.getAllUsers()
            val plates = allUsers.map { it.username }
            FileUtils.saveRegistroToFile(this, plates)
            FileUtils.saveArrendatariosToFile(this, plates.filter { it.startsWith("ARREND") })

            // Limpiar formulario
            newUsernameEditText.text.clear()
            newPasswordEditText.text.clear()
            editUsersCheckbox.isChecked = false
            editSpotsCheckbox.isChecked = false
            editPatioLicensesCheckbox.isChecked = false

            Toast.makeText(this, "Usuario agregado correctamente", Toast.LENGTH_SHORT).show()
        }
    }
}
