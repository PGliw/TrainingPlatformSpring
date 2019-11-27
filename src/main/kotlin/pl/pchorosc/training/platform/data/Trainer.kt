package pl.pchorosc.training.platform.data

import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.repository.Temporal
import java.sql.Date
import java.time.LocalDateTime
import javax.persistence.*

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
){
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





