package br.com.rodilon.testebancomodal.home.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rodilon.testebancomodal.*
import br.com.rodilon.testebancomodal.commom.NameButtons.NAME_FIRST_BUTTON
import br.com.rodilon.testebancomodal.commom.NameButtons.NAME_SECOND_BUTTON
import br.com.rodilon.testebancomodal.commom.NameButtons.NAME_THIRD_BUTTON
import br.com.rodilon.testebancomodal.commom.PaginationScroll
import br.com.rodilon.testebancomodal.model.Resource.Companion.INTERNAL_SERVER_ERROR
import br.com.rodilon.testebancomodal.model.Resource.Companion.LOADING
import br.com.rodilon.testebancomodal.model.Resource.Companion.SUCCESS
import br.com.rodilon.testebancomodal.home.ButtonFilterView
import br.com.rodilon.testebancomodal.commom.TypeConstants.TYPE_CONST_HOME
import br.com.rodilon.testebancomodal.filter.FilterActivity
import br.com.rodilon.testebancomodal.home.model.Repository
import br.com.rodilon.testebancomodal.home.model.RepositoryItemsList
import br.com.rodilon.testebancomodal.home.ui.adapter.RepositoryAdapter
import br.com.rodilon.testebancomodal.home.ui.view_model.RepositoryViewModel
import br.com.rodilon.testebancomodal.model.Resource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var repositoryAdapter: RepositoryAdapter
    private val listRepository = ArrayList<Repository>()
    private var releasedLoad: Boolean = true
    private var page = 1
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var toolbar: Toolbar
    private lateinit var buttonFilterViewFirst: ButtonFilterView
    private lateinit var buttonFilterViewSecond: ButtonFilterView
    private lateinit var buttonFilterViewThird: ButtonFilterView

    private var repositoryViewModel: RepositoryViewModel? = null
    private val statusObserver: Observer<Resource<RepositoryItemsList>> = getRepoObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        bindViews()
        fetchData(page)
        setupLayout()
        initListeners()
        setupFilterButtonViews()

        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Teste"
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))

    }

    private fun setupFilterButtonViews() {
        buttonFilterViewFirst
            .makeLayout(TYPE_CONST_HOME)
            .setupContentButton(NAME_FIRST_BUTTON)
            .build()

        buttonFilterViewSecond
            .makeLayout(TYPE_CONST_HOME)
            .setupContentButton(NAME_SECOND_BUTTON)
            .build()

        buttonFilterViewThird
            .makeLayout(TYPE_CONST_HOME)
            .setupContentButton(NAME_THIRD_BUTTON)
            .build()
    }

    private fun initViewModel() {
        repositoryViewModel = ViewModelProviders.of(this).get(RepositoryViewModel::class.java)
        repositoryViewModel!!.repositoryLiveData.observe(this, statusObserver)
    }

    private fun initListeners() {
        act_main_recycler.addOnScrollListener(object : PaginationScroll(linearLayoutManager) {
            override fun loadMoreItems() {
                releasedLoad = false
                act_main_progress_bottom.visibility = View.VISIBLE
                page++
                fetchData(page)
            }

            override fun hideMoreItems() {
                act_main_progress_center.visibility = View.GONE
            }

            override fun isLoading(): Boolean {
                return releasedLoad
            }
        })
    }

    private fun successRepository(repositoryItemsList: List<Repository>) {
        listRepository.addAll(repositoryItemsList)
        releasedLoad = true
        repositoryAdapter.notifyDataSetChanged()
    }

    private fun fetchData(page: Int) {
        repositoryViewModel!!.getRepo("language:Java", "stars", page)
    }

    private fun setupLayout() {
        repositoryAdapter =
            RepositoryAdapter(listRepository as List<Repository>)
        act_main_recycler.itemAnimator = DefaultItemAnimator()
        linearLayoutManager = LinearLayoutManager(this)
        act_main_recycler.layoutManager = linearLayoutManager
        act_main_recycler.adapter = repositoryAdapter
    }

    private fun bindViews() {
        toolbar = activity_main_toolbar
        buttonFilterViewFirst = act_main_button_first
        buttonFilterViewSecond = act_main_button_second
        buttonFilterViewThird = act_main_button_third
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        val iconFilter = menu!!.findItem(R.id.filter)
        val iconView = iconFilter.actionView
        iconView.setOnClickListener {
            val intent = Intent(this, FilterActivity::class.java)
            startActivity(intent)
        }
        return true
    }

    private fun getRepoObserver(): Observer<Resource<RepositoryItemsList>> {
        return Observer { resource ->
            if (resource != null) {
                when (resource.status) {
                    SUCCESS -> {
                        act_main_progress_center.visibility = View.GONE
                        act_main_progress_bottom.visibility = View.GONE
                        act_main_recycler.visibility = View.VISIBLE
                        successRepository(resource.data!!.repositoryItemsList)
                    }

                    LOADING -> {
                        if (releasedLoad) act_main_progress_center.visibility = View.VISIBLE
                    }

                    INTERNAL_SERVER_ERROR -> {

                    }
                }
            }
        }
    }
}
