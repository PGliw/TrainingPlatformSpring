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

enum class TrainingStatus{
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
){

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





