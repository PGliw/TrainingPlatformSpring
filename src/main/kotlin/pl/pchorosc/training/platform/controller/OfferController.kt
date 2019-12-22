package pl.pchorosc.training.platform.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.*
import pl.pchorosc.training.platform.data.dto.OfferDTO
import pl.pchorosc.training.platform.data.response.OfferResponse
import pl.pchorosc.training.platform.service.OfferService

@RestController
@RequestMapping("/offers")
@EnableAutoConfiguration
class OfferController {

    @Autowired
    private lateinit var offerService: OfferService

    @GetMapping
    fun getOffers(): Iterable<OfferResponse> = offerService.getOffers()

    @GetMapping(value = ["/{id}"])
    fun getOfferById(@PathVariable id: Long) = offerService.getOffer(id)

    @PostMapping
    fun addOffer(@RequestBody offerDTO: OfferDTO) = offerService.addOffer(offerDTO)
}