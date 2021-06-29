package ui.smartpro.nasageek.api

import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {

    @GET("planetary/apod")
   suspend fun getPictureOfTheDay(@Query("api_key") apiKey: String): NasaModel
}