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

    private lateinit var codingResources: MutableLiveData<MutableList<CodingResource>>

    fun getAllResources(): MutableLiveData<MutableList<CodingResource>> {
        val resourcesRequest = StringRequest(Method.GET, url,
            { response ->
                val responseArray = JSONArray(response)
                val myList = mutableListOf<CodingResource>()
                for (pos in 0..responseArray.length()){
                    val resourceObject = responseArray.getJSONObject(pos)
                    val codingResource = CodingResource(resourceObject.getInt("id"),
                    resourceObject.getString("description"),
                    resourceObject.getString("url"),
                    resourceObject.getJSONObject("types") as List<String>,
                    resourceObject.getJSONObject("topics") as List<String>,
                    resourceObject.getJSONObject("levels") as List<String>
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