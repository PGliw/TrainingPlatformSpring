package pl.pchorosc.training.platform.data.response

import java.time.LocalDate
import javax.persistence.*

data class CentreResponse(
       val id: Long,
       val name: String,
       val latitude: Float,
       val longitude: Float,
       val photoUrl: String,
       val imagesUrls: Iterable<String>
)

