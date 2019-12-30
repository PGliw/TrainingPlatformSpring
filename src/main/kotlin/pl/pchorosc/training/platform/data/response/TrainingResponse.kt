package pl.pchorosc.training.platform.data.response

data class TrainingSummaryResponse(
        val trainingID: Long,
        val startDateTime: String,
        val endDateTime: String,
        val centreName: String,
        val photoUrl: String,
        val numberOfTrainees: Int,
        val traineesLimit: Int,
        val trainingStatus: String,
        val sportName: String
)

data class TimeSlot(
        val startDateTime: String,
        val endDateTime: String
)

data class TraineeTrainingShortSummary(
        val trainingID: Long,
        val centreName: String,
        val trainerFirstName: String,
        val trainerLastName: String,
        val trainingStartDateTime: String,
        val trainingEndDateTime: String,
        val trainingStatus: String,
        val sportPhotoUrl: String,
        val sportName: String
)

