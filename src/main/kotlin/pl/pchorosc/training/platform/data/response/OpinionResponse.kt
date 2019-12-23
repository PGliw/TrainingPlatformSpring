package pl.pchorosc.training.platform.data.response

data class OpinionResponse(
        val id: Long,
        val authorID: Long,
        val subjectID: Long,
        val grade: Int,
        val opinion: String
)