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

data class Trainer2OverviewResponse(
        val id: Long,
        val firstName: String,
        val lastName: String,
        val photoUrl: String,
        val meanGrade: Float?,
        val opinionsCount: Int,
        val minPrice: Float?,
        val maxPrice: Float?,
        val sports: List<String>
)

data class Trainer2DetailsResponse(
        val id: Long,
        val firstName: String,
        val lastName: String,
        val photoUrl: String,
        val age: Int,
        val meanGrade: Float?,
        val opinionsCount: Int,
        val description: String,
        val imagesUrls: List<String>,
        val offers: List<OfferResponse>
)





