package ui.smartpro.nasageek.mars.api

import retrofit2.http.GET
import retrofit2.http.Query
import ui.smartpro.nasageek.BuildConfig

interface MarsApi {

    /**
     *  https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=fhaz&api_key=DEMO_KEY
     */

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getPictureOfMars(
        @Query("sol") sol: Int = 1000,
        @Query("camera") camera: String = "fhaz",
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): MarsApiDtoPhotos

}