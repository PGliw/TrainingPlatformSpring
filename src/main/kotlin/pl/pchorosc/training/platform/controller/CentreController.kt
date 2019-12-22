package pl.pchorosc.training.platform.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.*
import pl.pchorosc.training.platform.data.dto.CentreDTO
import pl.pchorosc.training.platform.data.response.CentreResponse
import pl.pchorosc.training.platform.service.CentreService

@RestController
@RequestMapping("/centres")
@EnableAutoConfiguration
class CentreController {

    @Autowired
    private lateinit var centreService: CentreService

    @GetMapping
    fun getCentres(): Iterable<CentreResponse> = centreService.getCenters()

    @GetMapping(value = ["/{id}"])
    fun getCentreById(@PathVariable id: Long) = centreService.getCentre(id)

    @PostMapping
    fun addCentre(@RequestBody centreDTO: CentreDTO) = centreService.insertCentre(centreDTO)

}