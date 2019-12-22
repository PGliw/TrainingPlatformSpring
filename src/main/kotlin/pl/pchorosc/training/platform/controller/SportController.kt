package pl.pchorosc.training.platform.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.*
import pl.pchorosc.training.platform.data.dto.SportDTO
import pl.pchorosc.training.platform.data.response.SportResponse
import pl.pchorosc.training.platform.service.SportService

@RestController
@RequestMapping("/sports")
@EnableAutoConfiguration
class SportController {

    @Autowired
    private lateinit var sportService: SportService

    @GetMapping
    fun getSports(): Iterable<SportResponse> = sportService.getSports()

    @GetMapping(value = ["/{id}"])
    fun getSportById(@PathVariable id: Long) = sportService.getSport(id)

    @PostMapping
    fun addSport(@RequestBody sportDTO: SportDTO) = sportService.insertSport(sportDTO)
}