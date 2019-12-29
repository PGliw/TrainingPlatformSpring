package pl.pchorosc.training.platform.data.dto

import pl.pchorosc.training.platform.data.TrainingStatus
import java.time.LocalDateTime
import javax.persistence.*

data class TrainingDTO(
        val startDateTime: String,
        val endDateTime: String,
        val traineeLimit: Int,
        val sportID: Long,
        val centreID: Long,
        val trainerID: Long
) {
    val status = TrainingStatus.PROPOSED
}