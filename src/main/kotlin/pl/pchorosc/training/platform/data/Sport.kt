package pl.pchorosc.training.platform.data

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "sports")
class Sport(
        @Id
        var name: String,
        var photoUrl: String
) {
    @OneToMany(mappedBy = "sport")
    var offers = listOf<Offer>()
}