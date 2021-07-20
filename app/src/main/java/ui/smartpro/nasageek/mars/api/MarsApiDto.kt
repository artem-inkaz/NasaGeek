package ui.smartpro.nasageek.mars.api

data class MarsApiDtoPhotos(
        val photos: List<MarsApiDto>,
)

data class MarsApiDto(
        val id: Int,
        val img_src: String,
        val earth_date: String,
)