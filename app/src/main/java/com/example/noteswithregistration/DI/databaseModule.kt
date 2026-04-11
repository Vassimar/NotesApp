package com.example.noteswithregistration.DI


import androidx.room.Room
import com.example.noteswithregistration.db.TaskDatabase
import com.example.noteswithregistration.db.TaskRepository
import com.example.noteswithregistration.screens.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            TaskDatabase::class.java,
            "task_database.db"
        ).build()
    }
    single {
        get<TaskDatabase>().taskDao()
    }

    single {
        TaskRepository(get())
    }
    viewModel {
        MainViewModel(get())
    }
}
