package pl.pchorosc.training.platform.data.dto

data class OpinionDTO(
        val authorID: Long,
        val subjectID: Long,
        val grade: Int,
        val opinion: String
)