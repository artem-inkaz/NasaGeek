package ui.smartpro.nasageek.earth.api

import retrofit2.http.GET
import retrofit2.http.Query
import ui.smartpro.nasageek.BuildConfig

interface EarthApi {

    /**
     * https://api.nasa.gov/EPIC/api/natural?api_key=DEMO_KEY
     */
    @GET("EPIC/api/natural")
    suspend fun getPictureOfEarth(
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): List<EarthApiDto>

    /**
     * https://api.nasa.gov/EPIC/archive/natural/2021/07/05/png/epic_1b_20210705005516.png?api_key=DEMO_KEY
     */

}