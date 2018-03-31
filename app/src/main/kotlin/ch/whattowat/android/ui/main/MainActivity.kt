package ch.whattowat.android.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import ch.whattowat.android.R
import ch.whattowat.android.dagger.ViewModelFactory
import ch.whattowat.api.model.Film
import ch.whattowat.api.model.WhatToWatchApiException
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

    private lateinit var model: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        setContentView(R.layout.activity_main)

        model = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        model.loadSuccess.observe(this, Observer { film -> handleFilmLoaded(film) })
        model.loadFailure.observe(this, Observer { error -> handleError(error) })

        if (savedInstanceState == null) {
            model.loadFilm()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.main_activity_option_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.main_option_item_refresh -> {
            model.loadFilm()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun handleFilmLoaded(film: Film?) {
        if (film != null) {
            txtFilmName.text = getString(R.string.film_title_format, film.title, film.year)
            progress.visibility = View.GONE
            txtFilmName.visibility = View.VISIBLE
        } else {
            txtFilmName.text = null
        }
    }

    private fun handleError(error: WhatToWatchApiException?) {
        if (error != null) {
            Toast.makeText(this, "Error: $error", Toast.LENGTH_LONG).show()
        }
    }
}