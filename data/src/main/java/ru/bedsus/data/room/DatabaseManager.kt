package ru.bedsus.data.room

import android.content.Context
import androidx.room.Room
import ru.bedsus.data.R

class DatabaseManager(applicationContext: Context) {

    val appDatabase = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        applicationContext.getString(R.string.nameDB)
    ).build()
}