package pl.pchorosc.training.platform.data.response

data class OfferResponse(
        val id: Long,
        val pricePerHour: Float,
        val trainerID: Long,
        val sportID: Long,
        val sportName: String
)