package com.eas.sdk.historical.service

import com.eas.sdk.baseservices.commons.base_services.BuildRequest
import com.eas.sdk.baseservices.commons.base_services.Endpoints
import com.eas.sdk.commons.models.Note
import com.eas.sdk.commons.models.Results
import com.ko.sdk.commons.util.UtilConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HistoricalService {

    fun requestHistorical (id: Int,callback: (ArrayList<Note>?) -> Unit = { _ -> }) {

        val request = BuildRequest()
        val client = request.getClient()

        client.getHistorical(id, id + 10).enqueue(object :
            Callback<ArrayList<Note>> {
            override fun onResponse(call: Call<ArrayList<Note>>, response: Response<ArrayList<Note>>) {
                    callback(response.body())
            }

            override fun onFailure(call: Call<ArrayList<Note>>, t: Throwable) {
                t.printStackTrace()
                callback(null)
            }
        })

    }


}