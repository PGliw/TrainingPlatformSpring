package pl.pchorosc.training.platform.repository

import org.springframework.data.repository.CrudRepository
import pl.pchorosc.training.platform.Trainer

interface TrainerRepository: CrudRepository<Trainer, String>