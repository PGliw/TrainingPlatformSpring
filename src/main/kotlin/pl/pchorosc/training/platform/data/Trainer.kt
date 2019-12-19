package pl.pchorosc.training.platform.data

import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
open class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        open var id: Long,
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
            0, "", "", "", "", "", LocalDate.now(), ""
    )

    @OneToMany(mappedBy = "user")
    var passes = listOf<Pass>()
}

@Entity
@Table(name = "trainers2")
class Trainer2(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        override var id: Long,
        @Column(unique = true)
        override var email: String,
        override var password: String,
        override var firstName: String,
        override var lastName: String,
        override var phone: String,
        override var birthday: LocalDate,
        override var photoUrl: String,
        var description: String
) : User(id, email, password, firstName, lastName, phone, birthday, photoUrl) {
    constructor() : this(
            0, "", "", "", "", "", LocalDate.now(), "", ""
    )

    @OneToMany(mappedBy = "trainer")
    var trainings = listOf<Training>()

    @OneToMany
    var images = listOf<Image>()

    @OneToMany(mappedBy = "trainer")
    var offers = listOf<Offer>()
}

@Entity
@Table(name = "trainees")
class Trainee(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        override var id: Long,
        @Column(unique = true)
        override var email: String,
        override var password: String,
        override var firstName: String,
        override var lastName: String,
        override var phone: String,
        override var birthday: LocalDate,
        override var photoUrl: String
) : User(id, email, password, firstName, lastName, phone, birthday, photoUrl) {
    constructor() : this(
            0, "", "", "", "", "", LocalDate.now(), ""
    )
}

enum class TrainingStatus {
    PROPOSED, DENIED, ACCEPTED, IN_PROGRESS, FINISHED, CANCELLED
}

@Entity
@Table(name = "trainings")
class Training(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long,
        var startDateTime: LocalDateTime,
        var endDateTime: LocalDateTime,
        var traineeLimit: Int,
        @Enumerated(EnumType.STRING)
        var status: TrainingStatus,

        @ManyToOne
        var trainer: Trainer2
) {
    constructor() : this(0, LocalDateTime.now(), LocalDateTime.now(), 0, TrainingStatus.PROPOSED, Trainer2())
}

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

@Entity
@Table(name = "centres")
class Centre(
        @Id
        var id: Long,
        var name: String,
        var latitude: Float,
        var longitude: Float
){
    @OneToMany
    var image = listOf<Image>()

    @ManyToMany
    @JoinTable(
            name = "centre_trainer",
            joinColumns = [JoinColumn(name = "fk_centre")],
            inverseJoinColumns = [JoinColumn(name = "fk_trainer")]
    )
    var trainers = setOf<Trainer2>()

    @ManyToMany(mappedBy = "centres")
    var passes = setOf<Pass>()
}

@Entity
@Table(name = "images")
class Image(
        @Id
        var id: Long,
        var url: String
)

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


/**
 * Entity represents databese entity
 */
@Entity
@Table(name = "trainers")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NamedQuery(
        name = "Trainer.findByLastName",
        query = "SELECT t FROM Trainer t WHERE t.lastName LIKE ?1"
)
data class Trainer(
        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @Column(columnDefinition = "varchar(36)")
        var id: String = "",
        var firstName: String,
        var lastName: String,
        var photoUrl: String,
        //@Temporal(TemporalType.DATE)
        var birthDate: String,
        var pricePerHour: Float,
        var description: String = "",
        @CreationTimestamp
        var created: java.util.Date = java.util.Date(),
        @UpdateTimestamp
        var modified: java.util.Date = java.util.Date()
) {
    /**
     * Hibernate tries creates a bean via reflection.
     * It does the object creation by calling the no-arg constructor.
     * Then it uses the setter methods to set the properties.
     *
     * If there is no default constructor, the following excpetion happens:
     * org.hibernate.InstantiationException: No default constructor for entity...
     */
    constructor() : this(
            "", "", "", "", "", 0f, ""
    )
}





