package pl.pchorosc.training.platform.data

import User
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "passes")
class Pass(
        @Id
        var id: Long,
        var name: String,
        var activationDate: LocalDate,
        var expirationDate: LocalDate,
        var frontSidePhotoUrl: String,
        var backSidePhotoUrl: String
){

    @ManyToOne
    var user : User = User()

    @ManyToMany
    @JoinTable(
            name = "pass_centre",
            joinColumns = [JoinColumn(name = "fk_pass")],
            inverseJoinColumns = [JoinColumn(name = "fk_centre")]
    )
    var centres = setOf<Centre>()
}