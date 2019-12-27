package pl.pchorosc.training.platform.data

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "centres")
class Centre(
        var name: String,
        var latitude: Float,
        var longitude: Float,
        var photoUrl: String
) {
    constructor() : this("", 0f, 0f, "")

    @Id
    @GeneratedValue
    var id: Long = 0L

    @OneToMany
    var image = mutableListOf<Image>()

    @ManyToMany
    @JoinTable(
            name = "centre_trainer",
            joinColumns = [JoinColumn(name = "fk_centre")],
            inverseJoinColumns = [JoinColumn(name = "fk_trainer")]
    )
    var trainers = mutableSetOf<Trainer2>()

    @ManyToMany(mappedBy = "centres")
    var passes = mutableSetOf<Pass>()

    @OneToMany(mappedBy = "centre")
    var trainings = mutableListOf<Training>()

    @ManyToMany
    @JoinTable(
            name = "centre_sport",
            joinColumns = [JoinColumn(name = "fk_centre")],
            inverseJoinColumns = [JoinColumn(name = "fk_sport")]
    )
    var sports = mutableSetOf<Sport>()
}
