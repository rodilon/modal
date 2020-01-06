package br.com.rodilon.testebancomodal.home

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.rodilon.testebancomodal.R
import br.com.rodilon.testebancomodal.home.TypeConstants.TYPE_CONST_FILTER
import br.com.rodilon.testebancomodal.home.TypeConstants.TYPE_CONST_HOME
import kotlinx.android.synthetic.main.view_button_filter.view.*

class ButtonFilterView : FrameLayout {

    private lateinit var container: ConstraintLayout
    private lateinit var textView: AppCompatTextView

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        LayoutInflater.from(context).inflate(R.layout.view_button_filter, this, true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        container = view_button_filter_container
        textView = view_button_text
    }

    fun makeLayout(type: Int): ButtonFilterView {

        when (type) {
            TYPE_CONST_FILTER -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextAppearance(R.style.TextAppearance_MontserratSemiBold_Black)
            } else {
                textView.setTextAppearance(context, R.style.TextAppearance_MontserratSemiBold_Black)
            }
            TYPE_CONST_HOME -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextAppearance(R.style.TextAppearance_ArialNarrowBoldItalic_Black)
            } else {
                textView.setTextAppearance(
                    context,
                    R.style.TextAppearance_ArialNarrowBoldItalic_Black
                )
            }
        }
        return this
    }

    fun setupContentButton(text: String): ButtonFilterView {
        textView.text = text
        return this
    }

    private fun deleteButton() {
        textView.setOnClickListener { container.visibility = View.GONE }
    }

    fun build(): ButtonFilterView {
        deleteButton()
        return this
    }
}