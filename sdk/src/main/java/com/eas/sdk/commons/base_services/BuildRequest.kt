/*
 *
 * BuildRequest.kt
 * CDS
 *
 * Created by Eliezer Alcázar on 24/04/20 12:16 PM.
 */

package com.eas.sdk.baseservices.commons.base_services

import com.ko.sdk.commons.util.UtilConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class BuildRequest {

    fun getClient(): EndpointInterface {
        var request = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(UtilConstants.Urls.DATA_LINK)
        return request.build().create(EndpointInterface::class.java)
    }
}