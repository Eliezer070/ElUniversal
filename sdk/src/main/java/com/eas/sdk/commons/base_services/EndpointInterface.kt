package com.eas.sdk.baseservices.commons.base_services

import com.eas.sdk.commons.models.Note
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface EndpointInterface {
    @GET("https://api.eluniversal.com.mx/v3/notes/eluniversal/mxm/json/espectaculos/{id}/{count}")
    fun getHistorical(
        @Path("id") id: Int,
        @Path("count") count: Int
    ): Call<ArrayList<Note>>
}