package pl.pchorosc.training.platform.data

import User
import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDate
import javax.persistence.*

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "trainers2")
class Trainer2(
        @Column(unique = true)
        override var email: String,
        override var password: String,
        override var firstName: String,
        override var lastName: String,
        override var phone: String,
        override var birthday: LocalDate,
        override var photoUrl: String,
        var description: String
) : User(email, password, firstName, lastName, phone, birthday, photoUrl) {
    constructor() : this(
            "", "", "", "", "", LocalDate.now(), "", ""
    )

    // TODO check if its necessary to override id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    override var id: Long = 0L

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
    var centres = mutableSetOf<Centre>()
}