package pl.pchorosc.training.platform.data

import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.repository.Temporal
import java.sql.Date
import java.time.LocalDateTime
import javax.persistence.*

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





