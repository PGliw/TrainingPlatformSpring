package pl.pchorosc.training.platform

import javafx.fxml.FXMLLoader
import org.springframework.data.jpa.repository.Temporal
import java.sql.Date
import javax.persistence.*

@Entity
data class Trainer(
        @Id
        var id: String = "",
        var firstName: String,
        var lastName: String,
        var photoUrl: String,
        @Temporal(TemporalType.DATE)
        var birthDate: Date,
        var pricePerHour: Float,
        var description: String = ""
)





