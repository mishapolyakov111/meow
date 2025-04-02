package ru.polyakov.meow

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import ru.polyakov.meow.model.database.MeowDatabase
import ru.polyakov.meow.model.repositories.CatRepository

val appModule = module {
    single {
        Room.databaseBuilder(androidApplication(), MeowDatabase::class.java, "meow_database")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<MeowDatabase>().catDao() }
    single { CatRepository(get()) }
}