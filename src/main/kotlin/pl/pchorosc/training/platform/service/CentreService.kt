package pl.pchorosc.training.platform.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.pchorosc.training.platform.data.Trainer2
import pl.pchorosc.training.platform.data.dto.CentreDTO
import pl.pchorosc.training.platform.data.dto.Trainer2DTO
import pl.pchorosc.training.platform.data.response.CentreResponse
import pl.pchorosc.training.platform.data.response.Trainer2Response
import pl.pchorosc.training.platform.repository.CentreRepository
import pl.pchorosc.training.platform.repository.Trainer2Repository
import pl.pchorosc.training.platform.utils.toCentre
import pl.pchorosc.training.platform.utils.toCentreResponse
import pl.pchorosc.training.platform.utils.toTrainer2
import pl.pchorosc.training.platform.utils.toTrainer2Response
import java.time.LocalDate

@Service("Centre.kt service")
class CentreService {

    @Autowired
    lateinit var repository: CentreRepository

    fun getCenters(): Iterable<CentreResponse> = repository.findAll().map { it.toCentreResponse() }

    fun insertCentre(centreDTO: CentreDTO): CentreResponse = repository.save(
            centreDTO.toCentre()
    ).toCentreResponse()

    fun getCentre(id: Long): CentreResponse = repository.findById(id).get().toCentreResponse()
}