package pl.pchorosc.training.platform.data

import User
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "trainers2")
class Trainer2(
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
        override var photoUrl: String,
        var description: String
) : User(id, email, password, firstName, lastName, phone, birthday, photoUrl) {
    constructor() : this(
            0, "", "", "", "", "", LocalDate.now(), "", ""
    )

    @OneToMany(mappedBy = "trainer")
    var trainings = listOf<Training>()

    @OneToMany
    var images = listOf<Image>()

    @OneToMany(mappedBy = "trainer")
    var offers = listOf<Offer>()

    @OneToMany(mappedBy = "author")
    var givenOpinions = listOf<OpinionTrainerAboutTrainee>()

    @OneToMany(mappedBy = "subject")
    var receivedOpinions = listOf<OpinionTraineeAboutTrainer>()

    @ManyToMany(mappedBy = "trainers")
    var centres = setOf<Centre>()
}