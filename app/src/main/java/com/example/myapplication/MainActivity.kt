package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var list : MutableList<CauHoi> = mutableListOf<CauHoi>()
        val jsonString: String = readJsonFile(this, "exceltojson.json")

        val jsonObject = JSONObject(jsonString)

        val jsonArray : JSONArray = jsonObject.optJSONArray("Sheet1")!!
        for (i in 0 until jsonArray.length()) {
            val jsonObject: JSONObject = jsonArray.getJSONObject(i)
            val person: CauHoi = convertJsonObjectToPerson(jsonObject);
            list.add(person)
        }

        val adapter  = PersonAdapter(list)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView);
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}

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

private fun convertJsonObjectToPerson(jsonObject: JSONObject): CauHoi {
    val STT = jsonObject.optInt("STT")
    val CAUHOI = jsonObject.optString("CAUHOI")
    val DAPAN1 = jsonObject.optString("DAPAN1")
    val DAPAN2 = jsonObject.optString("DAPAN2")
    val DAPAN3 = jsonObject.optString("DAPAN3")
    val DAPAN4 = jsonObject.optString("DAPAN4")
    val DUNG = jsonObject.optInt("DUNG")
    return CauHoi(STT, CAUHOI, DAPAN1, DAPAN2, DAPAN3, DAPAN4, DUNG)
}