package pl.pchorosc.training.platform.data.dto

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

data class Trainer2CentresDTO(
        val centresIDs: List<Long>
)





