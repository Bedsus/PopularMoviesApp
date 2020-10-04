package ru.bedsus.data.resource

import android.content.Context
import ru.bedsus.data.R

class DataResourceImpl(
    private val applicationContext: Context
) : DataResource {

    override val apiKet: String
        get() = applicationContext.getString(R.string.apiKey)

    override val nameDatabase: String
        get() = applicationContext.getString(R.string.nameDB)

    override val imageApiUrl: String
        get() = applicationContext.getString(R.string.imageApiUrl)

    override val baseUrl: String
        get() = applicationContext.getString(R.string.baseUrl)
}