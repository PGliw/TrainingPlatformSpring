package pl.pchorosc.training.platform.controller

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import pl.pchorosc.training.platform.Trainer
import java.sql.Date
import java.util.UUID

@RestController
@RequestMapping("/trainers")
@EnableAutoConfiguration
class TrainerController {

    @GetMapping(
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getTraineres() : List<Trainer> = listOf(
            Trainer(
                    UUID.randomUUID().toString(),
                    "Juan",
                    "Mondralez",
                    "https://images.pexels.com/photos/733500/pexels-photo-733500.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    Date(1992, 3, 12),
                    30f,
                    "Jestem profesjonalistą"
            ),
            Trainer(
                    UUID.randomUUID().toString(),
                    "Andrzej",
                    "Duda",
                    "https://images.pexels.com/photos/733500/pexels-photo-733500.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    Date(2000, 11, 1),
                    100f,
                    "Trenuję już 10 lat"
            )
    )

    @PutMapping(
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun insertTrainer(
            @RequestBody trainer: Trainer
    ) : Trainer {
        trainer.id = UUID.randomUUID().toString()
        return trainer
    }

    @PostMapping(
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun updateTrainer(
            @RequestBody trainer: Trainer
    ) : Trainer {
        trainer.description += " [updated] "
        return trainer
    }




}