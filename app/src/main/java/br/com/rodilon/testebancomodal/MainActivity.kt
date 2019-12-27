package br.com.rodilon.testebancomodal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = activity_main_toolbar

        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Teste"
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.filter -> {
                Toast.makeText(this, "Testando botao de filtro", Toast.LENGTH_LONG).show()
                return true
            }

        }
        return super.onOptionsItemSelected(item)

    }

}
