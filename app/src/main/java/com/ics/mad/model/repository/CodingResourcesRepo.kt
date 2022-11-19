package com.ics.mad.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request.Method
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.ics.mad.model.data.CodingResource
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.Flow

class CodingResourcesRepo {

    val url = "https://api.sampleapis.com/codingresources/codingResources"

    private lateinit var codingResources: MutableLiveData<MutableList<CodingResource>>
    fun getAllResources(): MutableLiveData<MutableList<CodingResource>> {
        val resourcesRequest = StringRequest(Method.GET, url,
            { response ->
                val responseArray = JSONArray(response)
                val myList = mutableListOf<CodingResource>()
                for (pos in 0..responseArray.length()){
                    val resourseObject = responseArray.getJSONObject(pos)
                    val codingResource = CodingResource(resourseObject.getInt("id"),
                    resourseObject.getString("description"),
                    resourseObject.getString("url"),
                    resourseObject.getJSONObject("types") as List<String>,
                    resourseObject.getJSONObject("topics") as List<String>,
                    resourseObject.getJSONObject("levels") as List<String>
                    )
                    myList.add(codingResource)
                    codingResources.postValue(myList)
                }
            }, {

            })

        return codingResources

    }
}