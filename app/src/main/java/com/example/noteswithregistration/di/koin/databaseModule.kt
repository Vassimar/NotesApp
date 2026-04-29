package com.example.noteswithregistration.di.koin


import androidx.room.Room
import com.example.noteswithregistration.data.db.TaskDatabase
import com.example.noteswithregistration.data.repository.TaskRepositoryImp
import com.example.noteswithregistration.domain.repository.TaskRepository
import com.example.noteswithregistration.presentation.viewmodels.ActiveTaskScreenViewModel
import com.example.noteswithregistration.presentation.viewmodels.CreateTaskViewModel
import com.example.noteswithregistration.presentation.viewmodels.EditTaskScreenViewModel
import com.example.noteswithregistration.presentation.viewmodels.TasksScreenViewModel
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

    single<TaskRepository> {
        TaskRepositoryImp(get())
    }
    viewModel {
        TasksScreenViewModel(get())
    }
    viewModel {
        EditTaskScreenViewModel(get(),get())
    }
    viewModel {
        ActiveTaskScreenViewModel(get())
    }
    viewModel {
        CreateTaskViewModel(get())
    }
}
