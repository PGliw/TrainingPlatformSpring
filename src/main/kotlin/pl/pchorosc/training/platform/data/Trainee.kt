package pl.pchorosc.training.platform.data

import User
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "trainees")
class Trainee(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        override var id: Long,
        @Column(unique = true)
        override var email: String,
        override var password: String,
        override var firstName: String,
        override var lastName: String,
        override var phone: String,
        override var birthday: LocalDate,
        override var photoUrl: String
) : User(id, email, password, firstName, lastName, phone, birthday, photoUrl) {
    constructor() : this(
            0, "", "", "", "", "", LocalDate.now(), ""
    )
}