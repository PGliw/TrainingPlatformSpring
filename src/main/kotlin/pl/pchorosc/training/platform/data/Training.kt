package pl.pchorosc.training.platform.data

import java.time.LocalDateTime
import javax.persistence.*

enum class TrainingStatus {
    PROPOSED, DENIED, ACCEPTED, IN_PROGRESS, FINISHED, CANCELLED
}

@Entity
@Table(name = "trainings")
class Training(
        var startDateTime: LocalDateTime,
        var endDateTime: LocalDateTime,
        var traineeLimit: Int,
        @Enumerated(EnumType.STRING)
        var status: TrainingStatus
) {
    constructor() : this(LocalDateTime.now(), LocalDateTime.now(), 0, TrainingStatus.PROPOSED)

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0L

    @ManyToOne
    var trainer = Trainer2()

    @OneToMany(mappedBy = "training")
    var trainingTrainees = mutableListOf<TraineeTraining>()

    @ManyToOne
    var centre = Centre()

    @ManyToOne
    var sport = Sport()
}