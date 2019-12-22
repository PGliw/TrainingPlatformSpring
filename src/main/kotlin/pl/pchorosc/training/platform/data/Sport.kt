package pl.pchorosc.training.platform.data

import javax.persistence.*

@Entity
@Table(name = "sports")
class Sport(
        @Column(unique = true)
        var name: String,
        var photoUrl: String
) {
    constructor() : this("", "")

    @Id
    @GeneratedValue
    var id: Long = 0L

    @OneToMany(mappedBy = "sport")
    var offers = listOf<Offer>()

    @ManyToMany(mappedBy = "sports")
    var centres = setOf<Centre>()
}