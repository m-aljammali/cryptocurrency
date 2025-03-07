package com.jammali.cryptocurrency.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


object storePreference {




    private const val PREFERENCES_DATA_STORE_NAME = "app_settings"

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_DATA_STORE_NAME)

    val PER_PAGE = stringPreferencesKey("per_page")


    val CACHE_EXP = stringPreferencesKey("cache_exp")


    internal suspend fun saveStringValue(
        value: String,
        key: Preferences.Key<String>,
        context: Context
    ) {

        Log.d("qqqq" , "saveStringValue")
        context.dataStore.edit { saveData ->
            saveData[key] = value
        }
    }


    internal fun getStringValueFlow(key: Preferences.Key<String>, context: Context): Flow<String?> =
        context.dataStore.data
            .catch { exception ->
                when (exception) {
                    is IOException -> emit(emptyPreferences())
                    else -> throw exception
                }
            }.map { readData ->
                readData[key]
            }


    internal fun getPerPage( context: Context): Flow<String?>  =
        context.dataStore.data
            .catch { exception ->
                when (exception) {
                    is IOException -> emit(emptyPreferences())
                    else -> throw exception
                }
            }.map { readData ->
                readData[PER_PAGE] ?: "5"
            }



    internal fun getCacheExp( context: Context): Flow<String?>  =
        context.dataStore.data
            .catch { exception ->
                when (exception) {
                    is IOException -> emit(emptyPreferences())
                    else -> throw exception
                }
            }.map { readData ->
                readData[CACHE_EXP]
            }


}