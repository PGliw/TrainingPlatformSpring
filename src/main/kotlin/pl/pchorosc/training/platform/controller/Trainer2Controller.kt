package pl.pchorosc.training.platform.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.*
import pl.pchorosc.training.platform.data.dto.OpinionDTO
import pl.pchorosc.training.platform.data.dto.Trainer2CentresDTO
import pl.pchorosc.training.platform.data.dto.Trainer2DTO
import pl.pchorosc.training.platform.data.response.CentreResponse
import pl.pchorosc.training.platform.data.response.SummaryResponse
import pl.pchorosc.training.platform.data.response.Trainer2Response
import pl.pchorosc.training.platform.service.OpinionService
import pl.pchorosc.training.platform.service.Trainer2Service

@RestController
@RequestMapping("/trainers2")
@EnableAutoConfiguration
class Trainer2Controller {

    @Autowired
    private lateinit var trainer2Service: Trainer2Service

    @Autowired
    private lateinit var opinionService: OpinionService

    @GetMapping
    fun getTrainers(
            @RequestParam sportID: Long?,
            @RequestParam centreID: Long?
    ): Iterable<Trainer2Response> = trainer2Service.getTrainers(sportID, centreID)

    @GetMapping(value = ["/summaries"])
    fun getTrainersSummaries(): Iterable<SummaryResponse> = trainer2Service.getTrainersSummaries()

    @GetMapping(value = ["{id}/centres"])
    fun getTrainerCentres(@PathVariable id: Long): Iterable<CentreResponse> = trainer2Service.getTrainerCentres(id)

    @GetMapping(value = ["/{id}"])
    fun getTrainer(@PathVariable id: Long) = trainer2Service.getTrainer(id)

    @PostMapping
    fun addTrainer(@RequestBody trainer2DTO: Trainer2DTO) = trainer2Service.insertTrainer(trainer2DTO)

    @GetMapping(value = ["/{id}/offers"])
    fun getTrainerOffers(@PathVariable id: Long) = trainer2Service.getTrainerOffers(id)

    @GetMapping(value = ["/{id}/trainings"])
    fun getTrainerTrainings(@PathVariable id: Long) = trainer2Service.getTrainingsSummaries(id)

    @GetMapping(value = ["/{id}/images"])
    fun getTrainerImages(@PathVariable id: Long) = trainer2Service.getTrainerImages(id)

    @PostMapping(value = ["/{id}/images"])
    fun addTrainerImages(@PathVariable id: Long,
                         @RequestBody imagesUrls: List<String>) = trainer2Service.insertTrainerImages(id, imagesUrls)

    @GetMapping(value = ["/{id}/opinions"])
    fun getTrainerOpinions(@PathVariable id: Long) = opinionService.getOpinionsAboutTrainer(id)

    @PostMapping(value = ["/{id}/opinions"])
    fun addTrainerOpinions(@RequestBody opinionDTO: OpinionDTO) = opinionService.insertOpinionAboutTrainer(opinionDTO)

    @PostMapping(value = ["{id}/centres"])
    fun addTrainerCentres(@PathVariable id: Long,
                          @RequestBody trainer2CentresDTO: Trainer2CentresDTO)
            : Iterable<CentreResponse> = trainer2Service.assignCentresToTrainer(id, trainer2CentresDTO)
}