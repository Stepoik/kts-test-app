package ru.stepan.reddit.core.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.Preferences.Key
import okio.Path.Companion.toPath

fun createDataStore(
    producePath: () -> String,
): DataStore<Preferences> = PreferenceDataStoreFactory.createWithPath(
    corruptionHandler = null,
    migrations = emptyList(),
    produceFile = { producePath().toPath() },
)

internal const val dataStoreFileName = "pulse.preferences_pb"

fun <T> MutablePreferences.setIfNotExists(key: Key<T>, value: T) {
    if (!contains(key)) {
        this[key] = value
    }
}