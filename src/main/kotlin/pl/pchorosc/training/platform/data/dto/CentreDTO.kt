package pl.pchorosc.training.platform.data.dto

import pl.pchorosc.training.platform.data.Trainer2
import java.text.SimpleDateFormat

/**
 * Data Transfer Object used to transfer via REST API (HTTP)
 */

data class CentreDTO(
        val name: String,
        val latitude: Float,
        val longitude: Float,
        val photoUrl: String
)





