package ru.bedsus.data.room

import android.content.Context
import androidx.room.Room
import ru.bedsus.data.resource.DataResource

class DatabaseManager(
    applicationContext: Context,
    resource: DataResource
) {

    val appDatabase = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        resource.nameDatabase
    ).allowMainThreadQueries()
        .build()
}