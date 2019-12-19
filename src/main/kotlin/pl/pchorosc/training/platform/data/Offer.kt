package pl.pchorosc.training.platform.data

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "offers")
class Offer(
        @Id
        var id: Long,
        var pricePerHour: Float,
        @ManyToOne
        var sport: Sport,
        @ManyToOne
        var trainer: Trainer2
)