/*
 *
 * SharedPreferencesUtil.kt
 * CDS
 *
 * Created by Eliezer Alcazar on 9/27/19 12:25 PM.
 * Copyright © 2019 Coca-Cola Company. All rights reserved.
 */

package com.ko.sdk.commons.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder


object SharedPreferencesUtil {

    fun setLong(context: Context, file: String, key: String, value: Long?) {
        val prefs = context.getSharedPreferences(file, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putLong(key, value!!)
        editor.commit()
    }

    fun getLong(context: Context, file: String, key: String): Long {
        val prefs = context.getSharedPreferences(file, Context.MODE_PRIVATE)
        return prefs.getLong(key, System.currentTimeMillis())
    }

    fun setString(context: Context, file: String, key: String, value: String?) {
        val prefs = context.getSharedPreferences(file, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun getString(context: Context, file: String, key: String): String? {
        val prefs = context.getSharedPreferences(file, Context.MODE_PRIVATE)
        return prefs.getString(key, "Cambiar pos string")
    }

    fun setBoolean(context: Context, file: String, key: String, value: Boolean?) {
        val prefs = context.getSharedPreferences(file, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean(key, value ?: false)
        editor.commit()
    }

    fun getBoolean(context: Context, file: String, key: String): Boolean? {
        val prefs = context.getSharedPreferences(file, Context.MODE_PRIVATE)
        return prefs.getBoolean(key, false)
    }

    fun setObject(context: Context, file: String, key: String, value: Any?) {
        val prefs = context.getSharedPreferences(file, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        var gson = GsonBuilder()
            .setLenient()
            .create()
        var objectToJson = gson.toJson(value)
        editor.putString(key, objectToJson)
        editor.commit()
    }

    fun getObject(context: Context, file: String, key: String, clazz: Class<*>): Any? {
        val prefs = context.getSharedPreferences(file, Context.MODE_PRIVATE)
        var gson: Gson = GsonBuilder()
            .setLenient()
            .create()
        var value = prefs.getString(key, "")
        val response = gson.fromJson(value, clazz)

        return response
    }


    fun setCryptedObject(context: Context, file: String, key: String, value: Any?) {
        val prefs = context.getSharedPreferences(file, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        var gson = GsonBuilder()
            .setLenient()
            .create()
        var objectToJson = gson.toJson(value)
        editor.putString(key, objectToJson)
        editor.commit()
    }


    inline fun <reified T> Gson.fromJson(json: String): T =
        this.fromJson<T>(json, T::class.java)

    fun getCryptedObject(context: Context, file: String, key: String, clazz: Class<*>): Any? {
        val prefs = context.getSharedPreferences(file, Context.MODE_PRIVATE)
        var gson: Gson = GsonBuilder()
            .setLenient()
            .create()
        val objectResponse = prefs.getString(key, "")
        when {
            !objectResponse.isNullOrEmpty() -> {
                var value =  objectResponse
                val response = gson.fromJson(value, clazz)

                return response
            }
            else -> return null
        }
    }

    fun setCryptedString(context: Context, file: String, key: String, value: String?) {
        val prefs = context.getSharedPreferences(file, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(key, value.toString())
        editor.commit()
    }

    fun getCryptedString(context: Context, file: String, key: String): String? {
        val prefs = context.getSharedPreferences(file, Context.MODE_PRIVATE)
        return prefs.getString(key, "Cambiar pos string")
    }

    fun removeObject(context: Context, file: String, key: String){
        val sharedPreference =  context.getSharedPreferences(file,Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.remove(key)
        editor.commit()
    }


}