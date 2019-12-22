package pl.pchorosc.training.platform.data

import User
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "trainees")
class Trainee(
        @Column(unique = true)
        override var email: String,
        override var password: String,
        override var firstName: String,
        override var lastName: String,
        override var phone: String,
        override var birthday: LocalDate,
        override var photoUrl: String
) : User(email, password, firstName, lastName, phone, birthday, photoUrl) {
    constructor() : this(
            "", "", "", "", "", LocalDate.now(), ""
    )

    // TODO check if its necessary to override id
    @Id
    @GeneratedValue
    override var id: Long = 0L

    @OneToMany(mappedBy = "author")
    var givenOpinions = listOf<OpinionTraineeAboutTrainer>()

    @OneToMany(mappedBy = "subject")
    var receivedOpinions = listOf<OpinionTrainerAboutTrainee>()

    @OneToMany(mappedBy = "trainee")
    var traineeTrainings = listOf<TraineeTraining>()
}