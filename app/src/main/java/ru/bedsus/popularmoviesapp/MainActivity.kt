package ru.bedsus.popularmoviesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.bedsus.popularmoviesapp.fragments.FilmListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.vContainerFragment, FilmListFragment())
            .commit()
    }
}