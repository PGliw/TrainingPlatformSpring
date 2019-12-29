package pl.pchorosc.training.platform.utils

import User
import pl.pchorosc.training.platform.data.*
import pl.pchorosc.training.platform.data.dto.*
import pl.pchorosc.training.platform.data.response.*
import java.time.LocalDate
import java.time.LocalDateTime

fun Trainer2.toTrainer2Response(): Trainer2Response {
    val birthdayStr = birthday.toString()
    return Trainer2Response(
            id = id,
            email = email,
            firstName = firstName,
            lastName = lastName,
            photoUrl = photoUrl,
            phone = phone,
            birthday = birthdayStr,
            description = description
    )
}

fun Trainer2DTO.toTrainer2(): Trainer2 {
    val birthday = LocalDate.parse(birthday)
    // TODO encode password
    val encryptedPassword = password
    return Trainer2(
            email = email,
            password = encryptedPassword,
            firstName = firstName,
            lastName = lastName,
            photoUrl = photoUrl,
            phone = phone,
            birthday = birthday,
            description = description
    )
}


fun Trainee.toTraineeResponse(): TraineeResponse {
    val birthdayStr = birthday.toString()
    return TraineeResponse(
            id = id,
            email = email,
            firstName = firstName,
            lastName = lastName,
            photoUrl = photoUrl,
            phone = phone,
            birthday = birthdayStr
    )
}

fun User.toUserResponse(): UserResponse {
    val birthdayStr = birthday.toString()
    return UserResponse(
            id = id,
            email = email,
            firstName = firstName,
            lastName = lastName,
            photoUrl = photoUrl,
            phone = phone,
            birthday = birthdayStr
    )
}

fun TraineeDTO.toTrainee(): Trainee {
    val birthday = LocalDate.parse(birthday)
    // TODO encode password
    val encryptedPassword = password
    return Trainee(
            email = email,
            password = encryptedPassword,
            firstName = firstName,
            lastName = lastName,
            photoUrl = photoUrl,
            phone = phone,
            birthday = birthday
    )
}

fun Centre.toCentreResponse(): CentreResponse {
    return CentreResponse(
            id = id,
            name = name,
            latitude = latitude,
            longitude = longitude,
            photoUrl = photoUrl,
            imagesUrls = image.map { it.url }
    )
}

fun CentreDTO.toCentre(): Centre {
    return Centre(
            name = name,
            latitude = latitude,
            longitude = longitude,
            photoUrl = photoUrl
    )
}

fun Sport.toSportResponse(): SportResponse {
    return SportResponse(
            id = id,
            name = name,
            photoUrl = photoUrl
    )
}

fun SportDTO.toSport(): Sport {
    return Sport(
            name = name,
            photoUrl = photoUrl
    )
}

fun Offer.toOfferResponse(): OfferResponse {
    return OfferResponse(
            id = id,
            pricePerHour = pricePerHour,
            trainerID = trainer.id,
            sportID = sport.id,
            sportName = sport.name
    )
}

fun OfferDTO.toOffer(): Offer {
    return Offer(pricePerHour)
}

fun OpinionTraineeAboutTrainer.toOpinionResponse(): OpinionResponse {
    return OpinionResponse(
            id = id,
            authorID = author.id,
            subjectID = subject.id,
            grade = grade,
            opinion = opinion
    )
}


fun OpinionDTO.toOpinionTraineeAboutTrainer(): OpinionTraineeAboutTrainer {
    return OpinionTraineeAboutTrainer(
            grade = grade,
            opinion = opinion
    )
}

fun OpinionTrainerAboutTrainee.toOpinionResponse(): OpinionResponse {
    return OpinionResponse(
            id = id,
            authorID = author.id,
            subjectID = subject.id,
            grade = grade,
            opinion = opinion
    )
}


fun OpinionDTO.toOpinionTrainerAboutTrainee(): OpinionTrainerAboutTrainee {
    return OpinionTrainerAboutTrainee(
            grade = grade,
            opinion = opinion
    )
}

fun Centre.toSummary() = SummaryResponse(
        id = id,
        imageUrl = photoUrl,
        title = name
)

fun Trainer2.toSummary() = SummaryResponse(
        id = id,
        imageUrl = photoUrl,
        title = "$firstName $lastName"
)

fun Sport.toSummary() = SummaryResponse(
        id = id,
        imageUrl = photoUrl,
        title = name
)

fun Training.toTrainingSummary() = TrainingSummaryResponse(
        trainingID = id,
        startDateTime = startDateTime.toString(),
        endDateTime = endDateTime.toString(),
        centreName = centre.name,
        photoUrl = centre.photoUrl,
        numberOfTrainees = trainingTrainees.size,
        traineesLimit = traineeLimit,
        trainingStatus = status.name
)

fun TrainingDTO.toTraining() = Training(
        startDateTime = LocalDateTime.parse(startDateTime),
        endDateTime = LocalDateTime.parse(startDateTime),
        traineeLimit = traineeLimit,
        status = status
)

fun Training.isOverlapping(another: Training): Boolean {
    return (
            trainer.id == another.trainer.id
                    &&
                    (startDateTime > another.startDateTime && startDateTime < another.endDateTime
                            || endDateTime < another.endDateTime && endDateTime < another.startDateTime)
            )
}

private val Trainer2.prices: List<Float>
    get() = offers.map { it.pricePerHour }

private val Trainer2.mean: Float?
    get() {
        val opinionsCount = receivedOpinions.size
        return if (opinionsCount == 0) null else receivedOpinions.map { it.grade }.sum().toFloat() / opinionsCount
    }

fun Trainer2.toOverview(): Trainer2OverviewResponse {
    return Trainer2OverviewResponse(
            id = id,
            firstName = firstName,
            lastName = lastName,
            photoUrl = photoUrl,
            meanGrade = mean,
            opinionsCount = receivedOpinions.size,
            minPrice = prices.min(),
            maxPrice = prices.max(),
            sports = offers.map { it.sport.name }
    )
}

fun Trainer2.toDetails(): Trainer2DetailsResponse {
    return Trainer2DetailsResponse(
            id = id,
            firstName = firstName,
            lastName = lastName,
            photoUrl = photoUrl,
            meanGrade = mean,
            opinionsCount = receivedOpinions.size,
            description = description,
            age = LocalDateTime.now().year - birthday.year,
            imagesUrls = images.map { it.url },
            offers = offers.map { it.toOfferResponse() }
    )
}

fun TraineeTraining.toShortSummary(): TraineeTrainingShortSummary{
    return TraineeTrainingShortSummary(
            trainingID = training.id,
            centreName = training.centre.name,
            trainerFirstName = training.trainer.firstName,
            trainerLastName = training.trainer.lastName,
            trainingStatus = training.status.name,
            trainingStartDateTime = training.startDateTime.toString(),
            trainingEndDateTime = training.endDateTime.toString()
    )
}



