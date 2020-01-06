package br.com.rodilon.testebancomodal.filter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.rodilon.testebancomodal.R
import br.com.rodilon.testebancomodal.commom.NameButtons
import br.com.rodilon.testebancomodal.commom.TypeConstants
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.toolbar.*

class FilterActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        toolbar = findViewById(R.id.act_filter_include_toolbar)
        setupFilterButtonViews()
        setupToolbar()
    }

    private fun setupToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_delete_filter_white)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
            finish()
        }
        toolbar_title.text = "Filtrar"
    }

    private fun setupFilterButtonViews() {
        act_filter_button_first
            .makeLayout(TypeConstants.TYPE_CONST_FILTER)
            .setupContentButton(NameButtons.NAME_FIRST_BUTTON)
            .build()

        act_filter_button_second
            .makeLayout(TypeConstants.TYPE_CONST_FILTER)
            .setupContentButton(NameButtons.NAME_SECOND_BUTTON)
            .build()

        act_filter_button_third
            .makeLayout(TypeConstants.TYPE_CONST_FILTER)
            .setupContentButton(NameButtons.NAME_THIRD_BUTTON)
            .build()

        act_filter_button_growing
            .makeLayout(TypeConstants.TYPE_CONST_FILTER)
            .setupContentButton(NameButtons.NAME_GROWING_BUTTON)
            .build()

        act_filter_button_descending
            .makeLayout(TypeConstants.TYPE_CONST_FILTER)
            .setupContentButton(NameButtons.NAME_STARS_BUTTON)
            .build()
    }
}
