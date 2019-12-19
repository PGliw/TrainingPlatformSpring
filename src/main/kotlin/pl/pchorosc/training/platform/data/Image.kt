package pl.pchorosc.training.platform.data

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "images")
class Image(
        @Id
        var id: Long,
        var url: String
)