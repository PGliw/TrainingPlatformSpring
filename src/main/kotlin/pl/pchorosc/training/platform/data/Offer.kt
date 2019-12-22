package pl.pchorosc.training.platform.data

import javax.persistence.*

@Entity
@Table(name = "offers")
class Offer(
        var pricePerHour: Float
) {
    constructor() : this(0f)

    @Id
    @GeneratedValue
    var id: Long = 0L

    @ManyToOne
    var sport = Sport()

    @ManyToOne
    var trainer = Trainer2()
}