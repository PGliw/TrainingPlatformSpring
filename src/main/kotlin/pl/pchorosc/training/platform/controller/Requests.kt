package pl.pchorosc.training.platform.controller

import java.util.*

data class TrainerRegisteredLaterThanRequest(val date: Date)

data class TrainerFindByLastNameRequest(val name: String)