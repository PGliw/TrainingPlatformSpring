package pl.pchorosc.training.platform.data

import javax.persistence.*

// TODO make pair (trainee, training) unique
@Entity
class TraineeTraining {
    @Id
    @GeneratedValue
    var id: Long = 0L

    var grade: Int = 0
    var opinion: String = ""

    @OneToMany
    var images = mutableListOf<Image>()

    @ManyToOne
    var trainee = Trainee()

    @ManyToOne
    var training = Training()
}