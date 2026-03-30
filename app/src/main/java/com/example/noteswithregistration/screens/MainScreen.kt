package com.example.noteswithregistration.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.noteswithregistration.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val viewModel: MainViewModel = viewModel()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(

                modifier = Modifier.statusBarsPadding(),
                title = { Text(topBarText(currentRoute)) },

                actions = {
                    TopBarButton(
                        navController = navController,
                        controller = currentRoute,
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier,


                ) {
                NavigationBarItem(

                    selected = currentRoute == Routes.MainScreen.route,
                    onClick = { navController.navigate(Routes.MainScreen.route) },
                    icon = {
                        Icon(
                            painterResource(id = R.drawable.outline_home_24),
                            contentDescription = "Home"
                        )
                    },
                    label = { Text("Home") },

                    )
                NavigationBarItem(
                    selected = currentRoute == Routes.TasksScreen.route,
                    onClick = { navController.navigate(Routes.TasksScreen.route) },
                    icon = {
                        Icon(
                            painterResource(R.drawable.baseline_checklist_24),
                            contentDescription = "Tasks",


                            )
                    },
                    label = { Text("Tasks") },

                    )


            }
        }
    ) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = Routes.MainScreen.route
        )
        {
            composable(Routes.MainScreen.route) {
                if (viewModel.tasks.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()

                    )
                    {
                        Text(
                            "No active tasks",
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                } else {
                    ActiveTasks(viewModel)
                }
            }
            composable(Routes.TasksScreen.route) {
                TasksScreen(viewModel)
            }
            composable(Routes.NewTaskScreen.route) {
                NewTask(viewModel)
            }
        }

    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}

fun topBarText(controller: String?): String {
    return when (controller) {
        Routes.MainScreen.route -> {
            "Create a new task"
        }

        Routes.TasksScreen.route -> {
            "Tasks"
        }

        Routes.NewTaskScreen.route -> {
            "New Task"
        }

        else -> "Create a new task"
    }
}

@Composable
fun TopBarButton(
    controller: String?,
    navController: NavHostController

) {
    when (controller) {
        Routes.MainScreen.route -> {
            IconButton(onClick = {
                navController.navigate(Routes.NewTaskScreen.route)
            }) {
                Icon(
                    painterResource(id = R.drawable.add),
                    modifier = Modifier.size(40.dp),
                    contentDescription = "More",

                    )

            }

        }
    }
}
