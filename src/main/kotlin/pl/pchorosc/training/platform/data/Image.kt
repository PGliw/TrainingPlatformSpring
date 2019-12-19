package pl.pchorosc.training.platform.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "images")
class Image(
        @Id
        @GeneratedValue
        var id: Long,
        var url: String
) {
    constructor() : this(0, "")
}