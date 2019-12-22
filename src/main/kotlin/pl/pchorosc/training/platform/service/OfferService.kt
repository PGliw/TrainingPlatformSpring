package pl.pchorosc.training.platform.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.pchorosc.training.platform.data.dto.OfferDTO
import pl.pchorosc.training.platform.data.response.OfferResponse
import pl.pchorosc.training.platform.exceptions.OfferNotFoundException
import pl.pchorosc.training.platform.exceptions.SportNotFoundException
import pl.pchorosc.training.platform.exceptions.TrainerNotFoundException
import pl.pchorosc.training.platform.repository.OfferRepository
import pl.pchorosc.training.platform.repository.SportRepository
import pl.pchorosc.training.platform.repository.Trainer2Repository
import pl.pchorosc.training.platform.utils.toOffer
import pl.pchorosc.training.platform.utils.toOfferResponse

@Service("Offer.kt service")
class OfferService {

    @Autowired
    lateinit var offerRepository: OfferRepository
    @Autowired
    lateinit var trainer2Repository: Trainer2Repository
    @Autowired
    lateinit var sportRepository: SportRepository

    fun getOffers(): Iterable<OfferResponse> = offerRepository.findAll().map { it.toOfferResponse() }

    fun getOffer(id: Long): OfferResponse =
            offerRepository.findByIdOrNull(id)?.toOfferResponse() ?: throw OfferNotFoundException()

    @Transactional
    fun addOffer(offerDTO: OfferDTO): OfferResponse {
        val offer = offerDTO.toOffer()
        val trainer = trainer2Repository.findByIdOrNull(offerDTO.trainerID) ?: throw TrainerNotFoundException()
        val sport = sportRepository.findByIdOrNull(offerDTO.sportID) ?: throw SportNotFoundException()
        offer.trainer = trainer
        offer.sport = sport
        return offerRepository.save(offer).toOfferResponse()
    }
}