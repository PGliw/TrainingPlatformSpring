package pl.pchorosc.training.platform.data.response

/**
 * Data Transfer Object used to transfer via REST API (HTTP)
 */

data class Trainer2Response(
        val id: Long,
        val email: String,
        val firstName: String,
        val lastName: String,
        val photoUrl: String,
        val phone: String,
        val birthday: String,
        val description: String
)





