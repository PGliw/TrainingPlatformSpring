import pl.pchorosc.training.platform.data.Pass
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
open class User(
        @Column(unique = true)
        open var email: String,
        open var password: String,
        open var firstName: String,
        open var lastName: String,
        open var phone: String,
        open var birthday: LocalDate,
        open var photoUrl: String
) {
    constructor() : this(
            "", "", "", "", "", LocalDate.now(), ""
    )

    @Id
    @GeneratedValue
    open var id: Long = 0L

    @OneToMany(mappedBy = "user")
    var passes = listOf<Pass>()
}
