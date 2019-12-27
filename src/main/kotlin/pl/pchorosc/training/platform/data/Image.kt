package pl.pchorosc.training.platform.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "images")
class Image(
        var url: String
) {
    constructor() : this("")

    @Id
    @GeneratedValue
    var id: Long = 0L
}