package ru.polyakov.meow

import org.koin.dsl.module
import ru.polyakov.meow.model.repositories.CatRepository

val appModule = module {
    single { CatRepository() }
}