package pl.pchorosc.training.platform.data

import javax.persistence.*

@Entity
class OpinionTrainerAboutTrainee(
        @Id
        @GeneratedValue
        var id: Long,
        var grade: Int,
        var opinion: String
) {
    constructor() : this(0, 0, "")

    @ManyToOne
    var author = Trainer2()

    @ManyToOne
    var subject = Trainee()
}

@Entity
class OpinionTraineeAboutTrainer(
        @Id
        @GeneratedValue
        var id: Long,
        var grade: Int,
        var opinion: String
) {
    constructor() : this(0, 0, "")

    @ManyToOne
    var author = Trainee()

    @ManyToOne
    var subject = Trainer2()
}


