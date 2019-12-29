package pl.pchorosc.training.platform.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.pchorosc.training.platform.data.Image
import pl.pchorosc.training.platform.data.Trainer2
import pl.pchorosc.training.platform.data.Training
import pl.pchorosc.training.platform.data.dto.Trainer2CentresDTO
import pl.pchorosc.training.platform.data.dto.Trainer2DTO
import pl.pchorosc.training.platform.data.response.CentreResponse
import pl.pchorosc.training.platform.data.response.SummaryResponse
import pl.pchorosc.training.platform.data.response.TimeSlot
import pl.pchorosc.training.platform.data.response.Trainer2Response
import pl.pchorosc.training.platform.exceptions.CentreNotFoundException
import pl.pchorosc.training.platform.exceptions.TrainerNotFoundException
import pl.pchorosc.training.platform.repository.CentreRepository
import pl.pchorosc.training.platform.repository.ImageRepository
import pl.pchorosc.training.platform.repository.Trainer2Repository
import pl.pchorosc.training.platform.utils.*
import java.time.LocalDateTime

@Service("Trainer2.kt service")
class Trainer2Service {

    @Autowired
    lateinit var trainer2Repository: Trainer2Repository

    @Autowired
    lateinit var centreRepository: CentreRepository

    @Autowired
    lateinit var imageRepository: ImageRepository

    fun getTrainers(
            sportID: Long? = null, centreID: Long? = null
    ) = getTrainersBySportAndCentre(sportID, centreID).map { it.toTrainer2Response() }

    fun getTrainersOverviews(
            sportID: Long? = null, centreID: Long? = null
    ) = getTrainersBySportAndCentre(sportID, centreID).map { it.toOverview() }

    fun getTrainersSummaries(): Iterable<SummaryResponse> = trainer2Repository.findAll().map { it.toSummary() }

    fun getTrainingsSummaries(trainerID: Long) = getTrainerTrainings(trainerID).map { it.toTrainingSummary() }

    fun getTrainerCentres(id: Long) = trainer2Repository.findByIdOrNull(id)?.centres?.map { it.toCentreResponse() } ?: throw TrainerNotFoundException()

    fun getTrainerOffers(id: Long) = trainer2Repository.findByIdOrNull(id)?.offers?.map { it.toOfferResponse() } ?: throw TrainerNotFoundException()

    fun getTrainerUpcomingTrainings(id: Long) = trainer2Repository.findByIdOrNull(id)?.trainings?.filter {
        it.startDateTime >= LocalDateTime.now() // TODO consider using frontend localDateTime
    }?.map { it.toTrainingSummary() } ?: throw  TrainerNotFoundException()

    fun getTrainerImages(id: Long) = trainer2Repository.findByIdOrNull(id)?.images?.map { it.url } ?: throw TrainerNotFoundException()

    fun getTrainerDetails(id: Long) = trainer2Repository.findByIdOrNull(id)?.toDetails() ?: throw  TrainerNotFoundException()

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

    @Transactional
    fun insertTrainerImages(trainerID: Long,
                            imagesUrls: List<String>): Iterable<String>{
        val trainer = trainer2Repository.findByIdOrNull(trainerID) ?: throw TrainerNotFoundException()
        val trainerImagesUrls = trainer.images.map { it.url }
        for (imageUrl in imagesUrls){
            if(imageUrl !in trainerImagesUrls){
                val image = imageRepository.save(Image(imageUrl))
                trainer.images.add(image) // TODO throw exception if image url already exists?
            }
        }
        return trainer.images.map { it.url }
    }

    private fun getTrainerTrainings(trainerID: Long): Iterable<Training>{
        val trainer = trainer2Repository.findByIdOrNull(trainerID) ?: throw TrainerNotFoundException()
        return trainer.trainings
    }

    private fun getTrainersBySportAndCentre(
            sportID: Long? = null, centreID: Long? = null
    ): Iterable<Trainer2> = when {
        centreID != null && sportID != null -> trainer2Repository.findByCentresIdAndOffersSportId(centreID, sportID)
        centreID != null -> trainer2Repository.findByCentresId(centreID)
        sportID != null -> trainer2Repository.findByOffersSportId(sportID)
        else -> trainer2Repository.findAll()
    }

    // TODO implement this method and check whether the slot is not already occupied
    private fun genTimeSlots(daysCount: Int, dayStartHour: Int, dayEndHour: Int, lengthInHours: Int): List<TimeSlot>{
        val startHours = (dayStartHour until dayEndHour step lengthInHours).toList()
        val endHours = ((dayStartHour + lengthInHours)..dayEndHour step lengthInHours).toList()
        val startEndHours = startHours.zip(endHours)
        val initialDateTime = when{
            startHours.last() >= LocalDateTime.now().hour -> LocalDateTime.now().plusDays(1)
            else -> LocalDateTime.now()
        }
        val timeSlots = mutableListOf<TimeSlot>()
        for(dayShift in 0..daysCount) {
            for (startEndHour in startEndHours) {
                if (initialDateTime.hour > startEndHour.first) {
                    //val
                }
            }
        }
        return listOf()
    }

}