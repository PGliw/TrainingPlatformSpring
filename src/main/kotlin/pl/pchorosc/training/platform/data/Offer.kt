package pl.pchorosc.training.platform.data

import javax.persistence.*

@Entity
@Table(name = "offers")
class Offer(
        @Id
        @GeneratedValue
        var id: Long,
        var pricePerHour: Float
) {
    constructor() : this(0, 0f)

    @ManyToOne
    var sport = Sport()

    @ManyToOne
    var trainer = Trainer2()
}