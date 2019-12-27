package pl.pchorosc.training.platform.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.pchorosc.training.platform.data.dto.SportDTO
import pl.pchorosc.training.platform.data.response.SportResponse
import pl.pchorosc.training.platform.data.response.SummaryResponse
import pl.pchorosc.training.platform.exceptions.SportNotFoundException
import pl.pchorosc.training.platform.repository.SportRepository
import pl.pchorosc.training.platform.utils.toSport
import pl.pchorosc.training.platform.utils.toSportResponse
import pl.pchorosc.training.platform.utils.toSummary

@Service("Sport.kt service")
class SportService {

    @Autowired
    lateinit var repository: SportRepository

    fun getSports(): Iterable<SportResponse> = repository.findAll().map { it.toSportResponse() }

    fun getSportsSummaries(): Iterable<SummaryResponse> = repository.findAll().map { it.toSummary() }

    fun insertSport(sportDTO: SportDTO): SportResponse = repository.save(
            sportDTO.toSport()
    ).toSportResponse()

    fun getSport(id: Long): SportResponse =
            repository.findByIdOrNull(id)?.toSportResponse() ?: throw SportNotFoundException()
}