package pl.pchorosc.training.platform.data.response

data class TrainingSummaryResponse(
        val trainingID: Long,
        val startDateTime: String,
        val endDateTime: String,
        val centreName: String,
        val photoUrl: String,
        val numberOfTrainees: Int,
        val traineesLimit: Int,
        val trainingStatus: String
)
