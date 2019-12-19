package pl.pchorosc.training.platform.data

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "centres")
class Centre(
        @Id
        var id: Long,
        var name: String,
        var latitude: Float,
        var longitude: Float
){
    @OneToMany
    var image = listOf<Image>()

    @ManyToMany
    @JoinTable(
            name = "centre_trainer",
            joinColumns = [JoinColumn(name = "fk_centre")],
            inverseJoinColumns = [JoinColumn(name = "fk_trainer")]
    )
    var trainers = setOf<Trainer2>()

    @ManyToMany(mappedBy = "centres")
    var passes = setOf<Pass>()
}
