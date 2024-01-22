package com.example.myapplication

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class JsonFileReader {
    fun readJsonFile(context: Context, fileName: String?): String {
        val stringBuilder = StringBuilder()
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open(fileName!!)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }
}