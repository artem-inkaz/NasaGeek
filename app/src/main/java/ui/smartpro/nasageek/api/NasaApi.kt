package ui.smartpro.nasageek.api

import ui.smartpro.nasageek.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {

    /**
     * https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY
     */
    @GET("planetary/apod")
   suspend fun getPictureOfTheDay(
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): NasaModel
}