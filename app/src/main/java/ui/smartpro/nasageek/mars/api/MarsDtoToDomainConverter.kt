package ui.smartpro.nasageek.mars.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Конвертер чтобы в результирующем классе MarsModel были все нужные поля
suspend fun convertMarsDtoToDomain(
    marsApiDtoPhotos: List<MarsApiDto>
): List<MarsModel> =
    withContext(Dispatchers.Default) {

        marsApiDtoPhotos.map { marsApiDto: MarsApiDto ->
            MarsModel(
                id = marsApiDto.id,
                img_src = marsApiDto.img_src,
                earth_date = marsApiDto.earth_date,
            )
        }
    }