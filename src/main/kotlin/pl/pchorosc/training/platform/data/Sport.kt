package pl.pchorosc.training.platform.data

import javax.persistence.*

@Entity
@Table(name = "sports")
class Sport(
        @Id
        @GeneratedValue
        var id: Long,
        @Column(unique = true)
        var name: String,
        var photoUrl: String
) {
    constructor() : this(0, "", "")

    @OneToMany(mappedBy = "sport")
    var offers = listOf<Offer>()

    @ManyToMany(mappedBy = "sports")
    var centres = setOf<Centre>()
}