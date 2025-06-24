package com.example.balmax2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar Drawer Layout
        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Botones de acceso rápido a patios
        val patio1Button = findViewById<Button>(R.id.patio1Button)
        val patio2Button = findViewById<Button>(R.id.patio2Button)
        val patio3Button = findViewById<Button>(R.id.patio3Button)
        val patio4Button = findViewById<Button>(R.id.patio4Button)
        val patio5Button = findViewById<Button>(R.id.patio5Button)
        val patio6Button = findViewById<Button>(R.id.patio6Button)
        val patio7Button = findViewById<Button>(R.id.patio7Button)

        // Configurar listeners para abrir detalles del patio
        patio1Button.setOnClickListener { openPatioDetails(1) }
        patio2Button.setOnClickListener { openPatioDetails(2) }
        patio3Button.setOnClickListener { openPatioDetails(3) }
        patio4Button.setOnClickListener { openPatioDetails(4) }
        patio5Button.setOnClickListener { openPatioDetails(5) }
        patio6Button.setOnClickListener { openPatioDetails(6) }
        patio7Button.setOnClickListener { openPatioDetails(7) }

        // Menú lateral
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.drawerUserManagementBtn -> {
                    startActivity(Intent(this, UserManagementActivity::class.java))
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.drawerLogoutBtn -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    private fun openPatioDetails(patioNumber: Int) {
        val intent = Intent(this, PatioDetailsActivity::class.java).apply {
            putExtra("PATIO_NUMBER", patioNumber)
        }
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        return toggle.onOptionsItemSelected(super.onBackPressedDispatcher.hashCode()) || super.onSupportNavigateUp()
    }
}
