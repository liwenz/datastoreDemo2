package com.liwensoft.datastoredemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    //define dataStore
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Study")

    //定义要操作的key
    private val key = stringPreferencesKey("name")
    private val keynum = intPreferencesKey("number")

    private lateinit var tv: TextView
    private lateinit var et: EditText

    private suspend fun put() = dataStore.edit { it[key] = "Home"
        var num:Int
        try {
            num = et.text.toString().toInt()
        }
        catch (ex: Exception)
        {
            num=2
        }
        it[keynum]=num
    }

    private fun get() = runBlocking {
        return@runBlocking dataStore.data.map { it[key] ?: "Office" }.first()
    }

    private fun get2() = runBlocking {
        return@runBlocking dataStore.data.map { it[keynum] ?: 5 }.first()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et=findViewById(R.id.number)
        tv=findViewById(R.id.textView)

        val btn_put: Button = findViewById(R.id.btn_put)
        btn_put.setOnClickListener {
            Log.i("ACTIVITY", "ACTIVITY1 btn put")
            runBlocking { put() }
        }

        val btn_get: Button = findViewById(R.id.btn_get)
        btn_get.setOnClickListener {
            val data=get()
            val num=get2()
            tv.setText("$data count=$num")
        }

        val btn_clear: Button = findViewById(R.id.btn_clear)
        btn_clear.setOnClickListener {
            runBlocking { dataStore.edit { it.clear() } }
         }
    }
}