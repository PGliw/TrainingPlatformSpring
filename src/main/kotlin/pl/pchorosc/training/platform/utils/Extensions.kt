package pl.pchorosc.training.platform.utils

import pl.pchorosc.training.platform.data.Centre
import pl.pchorosc.training.platform.data.Sport
import pl.pchorosc.training.platform.data.Trainer2
import pl.pchorosc.training.platform.data.dto.CentreDTO
import pl.pchorosc.training.platform.data.dto.SportDTO
import pl.pchorosc.training.platform.data.dto.Trainer2DTO
import pl.pchorosc.training.platform.data.response.CentreResponse
import pl.pchorosc.training.platform.data.response.SportResponse
import pl.pchorosc.training.platform.data.response.Trainer2Response
import java.time.LocalDate

fun Trainer2.toTrainer2Response() : Trainer2Response {
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

fun Trainer2DTO.toTrainer2() : Trainer2{
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

fun Centre.toCentreResponse() : CentreResponse{
    return CentreResponse(
            id = id,
            name = name,
            latitude = latitude,
            longitude = longitude,
            photoUrl = photoUrl,
            imagesUrls = image.map { it.url }
    )
}

fun CentreDTO.toCentre() : Centre {
    return Centre(
            name = name,
            latitude = latitude,
            longitude = longitude,
            photoUrl = photoUrl
    )
}

fun Sport.toSportResponse(): SportResponse {
    return SportResponse(
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