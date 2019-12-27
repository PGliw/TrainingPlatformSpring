package pl.pchorosc.training.platform.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.pchorosc.training.platform.data.Centre
import pl.pchorosc.training.platform.data.dto.Trainer2CentresDTO
import pl.pchorosc.training.platform.data.dto.Trainer2DTO
import pl.pchorosc.training.platform.data.response.CentreResponse
import pl.pchorosc.training.platform.data.response.Summary
import pl.pchorosc.training.platform.data.response.Trainer2Response
import pl.pchorosc.training.platform.exceptions.CentreNotFoundException
import pl.pchorosc.training.platform.exceptions.TrainerNotFoundException
import pl.pchorosc.training.platform.repository.CentreRepository
import pl.pchorosc.training.platform.repository.Trainer2Repository
import pl.pchorosc.training.platform.utils.toCentreResponse
import pl.pchorosc.training.platform.utils.toSummary
import pl.pchorosc.training.platform.utils.toTrainer2
import pl.pchorosc.training.platform.utils.toTrainer2Response

@Service("Trainer2.kt service")
class Trainer2Service {

    @Autowired
    lateinit var trainer2Repository: Trainer2Repository

    @Autowired
    lateinit var centreRepository: CentreRepository

    fun getTrainers(
            sportID: Long? = null, centreID: Long? = null
    ): Iterable<Trainer2Response> = when {
        centreID != null -> trainer2Repository.findByCentresId(centreID)
        else -> trainer2Repository.findAll()
    }.map { it.toTrainer2Response() }

    fun getTrainersSummaries(): Iterable<Summary> = trainer2Repository.findAll().map { it.toSummary() }

    fun getTrainerCentres(id: Long) = trainer2Repository.findByIdOrNull(id)?.centres?.map { it.toCentreResponse() } ?: throw TrainerNotFoundException()

    fun getTrainer(id: Long): Trainer2Response =
            trainer2Repository.findByIdOrNull(id)?.toTrainer2Response() ?: throw TrainerNotFoundException()

    fun insertTrainer(trainer2DTO: Trainer2DTO): Trainer2Response = trainer2Repository.save(
            trainer2DTO.toTrainer2()
    ).toTrainer2Response()

    @Transactional
    fun assignCentresToTrainer(trainerID: Long,
                       trainer2CentresDTO: Trainer2CentresDTO): Iterable<CentreResponse>{
        val trainer = trainer2Repository.findByIdOrNull(trainerID) ?: throw TrainerNotFoundException()
        val trainerCentresIDs = trainer.centres.map { it.id }
        for( centreID in trainer2CentresDTO.centresIDs){
            if(centreID !in trainerCentresIDs) {
                val centre = centreRepository.findByIdOrNull(centreID) ?: throw CentreNotFoundException()
                centre.trainers.add(trainer)
                trainer.centres.add(centre)
            }
        }
        return trainer.centres.map { it.toCentreResponse() }
    }

}