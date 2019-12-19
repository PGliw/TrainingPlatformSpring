package pl.pchorosc.training.platform.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class OpinionTrainerAboutTrainee(
        @Id
        @GeneratedValue
        var id: Long,
        var grade: Int,
        var opinion: String
){
    
}