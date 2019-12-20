package pl.pchorosc.training.platform.data.dto

import pl.pchorosc.training.platform.data.Trainer2
import java.text.SimpleDateFormat

/**
 * Data Transfer Object used to transfer via REST API (HTTP)
 */

data class Trainer2DTO(
        var email: String,
        var firstName: String,
        var lastName: String,
        var photoUrl: String,
        var phone: String,
        var birthday: String,
        var description: String
) {
    constructor(trainer2: Trainer2)
            : this(
            email = trainer2.email,
            firstName = trainer2.firstName,
            lastName = trainer2.lastName,
            photoUrl = trainer2.photoUrl,
            phone = trainer2.phone,
            // TODO change toString to formatter
            birthday = trainer2.birthday.toString(),
            description = trainer2.description
    ){
        id = trainer2.id
    }
    var id = 0L
}





