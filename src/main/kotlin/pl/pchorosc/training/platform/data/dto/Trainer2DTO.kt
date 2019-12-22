package pl.pchorosc.training.platform.data.dto

import pl.pchorosc.training.platform.data.Trainer2
import java.text.SimpleDateFormat

/**
 * Data Transfer Object used to transfer via REST API (HTTP)
 */

data class Trainer2DTO(
        val email: String,
        val password: String,
        val firstName: String,
        val lastName: String,
        val photoUrl: String,
        val phone: String,
        val birthday: String,
        val description: String
)





