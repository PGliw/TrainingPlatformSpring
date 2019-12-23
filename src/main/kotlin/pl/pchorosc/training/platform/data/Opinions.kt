package pl.pchorosc.training.platform.data

import javax.persistence.*

@Entity
class OpinionTrainerAboutTrainee(
        var grade: Int,
        var opinion: String
) {
    constructor() : this(0, "")

    @Id
    @GeneratedValue
    var id: Long = 0L

    @ManyToOne
    var author = Trainer2()

    @ManyToOne
    var subject = Trainee()
}

@Entity
class OpinionTraineeAboutTrainer(
        var grade: Int,
        var opinion: String
) {
    constructor() : this( 0, "")

    @Id
    @GeneratedValue
    var id: Long = 0L

    @ManyToOne
    var author = Trainee()

    @ManyToOne
    var subject = Trainer2()
}


