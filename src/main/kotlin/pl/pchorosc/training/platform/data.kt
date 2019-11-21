package pl.pchorosc.training.platform

import javafx.fxml.FXMLLoader
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.jpa.repository.Temporal
import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "trainers")
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
        var description: String = ""
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





