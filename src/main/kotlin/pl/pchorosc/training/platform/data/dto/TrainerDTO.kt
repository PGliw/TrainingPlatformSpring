package pl.pchorosc.training.platform.data.dto

import pl.pchorosc.training.platform.data.Trainer

/**
 * Data Transfer Object used to transfer via REST API (HTTP)
 */

data class TrainerDTO(
        var firstName: String,
        var lastName: String,
        var photoUrl: String,
        //@Temporal(TemporalType.DATE)
        var birthDate: String,
        var pricePerHour: Float,
        var description: String = ""
) {
    var id = ""
    var created = java.util.Date()
    var modified = java.util.Date()

    constructor(trainer: Trainer) : this(
            trainer.firstName,
            trainer.lastName,
            trainer.photoUrl,
            trainer.birthDate,
            trainer.pricePerHour,
            trainer.description
    ) {
        id = trainer.id
        created = trainer.created
        modified = trainer.modified
    }
}





