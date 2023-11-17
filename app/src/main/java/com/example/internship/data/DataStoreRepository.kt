package com.example.internship.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

const val PREFERENCE_NAME = "App Preferences"

class DataStoreRepository(val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)
    private val dataStore = context.dataStore

    companion object PreferenceKeys {
        val userStatus: Preferences.Key<String> = stringPreferencesKey("user_status")
    }
    suspend fun setPref(prefValue: String, prefKey: Preferences.Key<String>) {
        dataStore.edit { pref->
            pref[prefKey] = prefValue
        }
        Log.d("DATASTORE","setPref отработал String ${prefValue}")
    }

    fun getPref(prefKey: Preferences.Key<String>) : Flow<String> {
        return dataStore.data
            .catch { exception->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { pref->
                val prefMode = pref[prefKey] ?: ""
                prefMode
            }

    }

}