package pl.pchorosc.training.platform.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.pchorosc.training.platform.data.dto.CentreDTO
import pl.pchorosc.training.platform.data.response.CentreResponse
import pl.pchorosc.training.platform.data.response.Summary
import pl.pchorosc.training.platform.exceptions.CentreNotFoundException
import pl.pchorosc.training.platform.repository.CentreRepository
import pl.pchorosc.training.platform.utils.toCentre
import pl.pchorosc.training.platform.utils.toCentreResponse
import pl.pchorosc.training.platform.utils.toSummary

@Service("Centre.kt service")
class CentreService {

    @Autowired
    lateinit var repository: CentreRepository

    fun getCenters(): Iterable<CentreResponse> = repository.findAll().map { it.toCentreResponse() }

    fun getCentresSummaries(): Iterable<Summary> = repository.findAll().map { it.toSummary() }

    fun getCentre(id: Long): CentreResponse =
            repository.findByIdOrNull(id)?.toCentreResponse() ?: throw CentreNotFoundException()

    fun insertCentre(centreDTO: CentreDTO): CentreResponse = repository.save(
            centreDTO.toCentre()
    ).toCentreResponse()

}