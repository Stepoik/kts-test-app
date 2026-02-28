package ru.stepan.reddit.core.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

fun dataStore(context: Context): DataStore<Preferences> {
    return createDataStore(
        producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
    )
}