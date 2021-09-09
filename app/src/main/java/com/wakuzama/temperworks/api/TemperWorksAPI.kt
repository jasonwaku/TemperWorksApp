package com.wakuzama.temperworks.api

import retrofit2.http.GET
import retrofit2.http.Query

interface TemperWorksAPI {

    companion object {
        const val BASE_URL = "https://temper.works/"
    }

//    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
//    if there are header variables needed for the api connection can use it here, such as authenticating

    @GET("api/v3/shifts")
    suspend fun searchPosts(
        @Query("filter[date]") query: String,
        //pagination parameters can be used if needed
    ): TemperWorksResponse

}