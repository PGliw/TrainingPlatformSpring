package pl.pchorosc.training.platform.exceptions

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ResponseStatus

@Component
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class UserNotFoundException : RuntimeException("No user with given email was found")

@Component
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class TrainerNotFoundException : RuntimeException("No trainer with given ID was found")

@Component
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class SportNotFoundException : RuntimeException("No sport with given ID was found")

@Component
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class TraineeNotFoundException : RuntimeException("No trainee with given ID was found")

@Component
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class OfferNotFoundException : RuntimeException("No offer with given ID was found")

@Component
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class CentreNotFoundException : RuntimeException("No centre with given ID was found")

@Component
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
class UnauthorizedException : RuntimeException("You are unauthorized")
