package ch.whattowat.android.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import ch.whattowat.android.R
import ch.whattowat.android.dagger.ViewModelFactory
import ch.whattowat.api.model.Film
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

        model.film.observe(this, Observer { film -> handleFilmLoaded(film) })
    }

    private fun handleFilmLoaded(film: Film?) {
        txtFilmName.text = film?.title
        progress.visibility = View.GONE
        txtFilmName.visibility = View.VISIBLE
    }
}