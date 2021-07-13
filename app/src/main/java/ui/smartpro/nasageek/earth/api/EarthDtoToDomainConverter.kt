package ui.smartpro.nasageek.earth.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ui.smartpro.nasageek.BuildConfig

/**
 * https://api.nasa.gov/EPIC/archive/natural/2021/07/05/png/epic_1b_20210705005516.png?api_key=DEMO_KEY
 */

// Конвертер чтобы в результирующем классе MarsModel были все нужные поля
suspend fun convertEarthDtoToDomain(
    earthApiDtoPhotos: List<EarthApiDto>
): List<EarthModel> =
    withContext(Dispatchers.Default) {

        earthApiDtoPhotos.map { earthApiDto: EarthApiDto ->
            EarthModel(
                identifier = earthApiDto.identifier,
                image = earthApiDto.image,
//                    date = earthApiDto.date,
                date = convertText(earthApiDto.date),
//                    imagePath =earthApiDto.image
                imagePath = pathImage(
                    convertText(earthApiDto.date),
                    earthApiDto.image,
                    BuildConfig.NASA_API_KEY
                )
            )
        }
    }

suspend fun convertText(str: String): String =
    withContext(Dispatchers.Default) {
        // подстрока до первого указанного разделителя- пробела
        // "2021-07-05 00:50:27"
        val substr = str.substringBefore(' ') // 2021-07-05
        val result = substr.replace(
            '-', // old char
            '/', // new char
            true // ignore case Boolean = false
        ) // 2021/07/05
        return@withContext result
    }

suspend fun pathImage(dateStr: String, imageStr: String, apikey: String): String =
    withContext(Dispatchers.Default) {
        val path =
            "https://api.nasa.gov/EPIC/archive/natural/$dateStr/png/$imageStr.png?api_key=$apikey"

        return@withContext path
    }