package pl.pchorosc.training.platform.exceptions

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ResponseStatus

@Component
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class TrainerNotFoundException : RuntimeException("No trainer with given ID was found")

@Component
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class SportNotFoundException : RuntimeException("No sport with given ID was found")
