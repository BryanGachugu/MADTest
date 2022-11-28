package com.ics.mad.model.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request.Method
import com.android.volley.toolbox.StringRequest
import com.ics.mad.model.api.MySingleton
import com.ics.mad.model.data.CodingResource
import org.json.JSONArray

class CodingResourcesRepo(private val context: Context) {

    private val url = "https://api.sampleapis.com/codingresources/codingResources"

    private var codingResources = MutableLiveData<MutableList<CodingResource>>()

    fun getAllResources(): MutableLiveData<MutableList<CodingResource>> {
        val resourcesRequest = StringRequest(Method.GET, url,
            { response ->
                val responseArray = JSONArray(response)
                val myList = mutableListOf<CodingResource>()
                for (pos in 0 until  responseArray.length()) {
                    //each object
                    val resourceObject = responseArray.getJSONObject(pos)

                    //types list
                    val types = mutableListOf<String>()
                    val typesArrayObject = resourceObject.getJSONArray("types")
                    for (typePos in 0 until typesArrayObject.length())
                        types.add(typesArrayObject.getString(typePos))

                    // topics list
                    val topics = mutableListOf<String>()
                    val topicsArrayObject = resourceObject.getJSONArray("topics")
                    for (topicPos in 0 until topicsArrayObject.length())
                        topics.add(topicsArrayObject.getString(topicPos))

                    //levels
                    val levels = mutableListOf<String>()
                    val levelsArrayObject = resourceObject.getJSONArray("levels")
                    for (levelPos in 0 until  levelsArrayObject.length())
                        levels.add(levelsArrayObject.getString(levelPos))

                    val codingResource = CodingResource(
                        resourceObject.getInt("id"),
                        resourceObject.getString("description"),
                        resourceObject.getString("url"),
                        types,
                        topics,
                        levels
                    )
                    myList.add(codingResource)

                    codingResources.postValue(myList)
                }
            }, {

            })

        MySingleton.getInstance(context).addToRequestQueue(resourcesRequest)

        return codingResources

    }
}