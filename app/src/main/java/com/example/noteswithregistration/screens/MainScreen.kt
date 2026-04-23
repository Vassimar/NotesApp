package com.example.noteswithregistration.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteswithregistration.R
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val viewModel: MainViewModel = koinViewModel()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = { Text(topBarText(currentRoute)) },
                navigationIcon = {
                    TopBarNavigationIcon(currentRoute, navController)
                },
                actions = {
                    TopBarButton(
                        navController = navController,
                        controller = currentRoute,
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar{
                NavigationBarItem(
                    selected = currentRoute == Routes.MainScreen.route,
                    onClick = {
                        onClickNavigation(
                            navController = navController,
                            destination = Routes.MainScreen.route,
                        )
                    },
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
                    onClick = {
                        onClickNavigation(
                            navController = navController,
                            destination = Routes.TasksScreen.route,
                        )
                    },
                    icon = {
                        Icon(
                            painterResource(R.drawable.baseline_checklist_24),
                            contentDescription = "Tasks",
                        )
                    },
                    label = { Text("Tasks") },
                )
            }
        }) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = Routes.MainScreen.route
        ) {
            composable(Routes.MainScreen.route) {
                ActiveTasks(viewModel,navController)
            }
            composable(Routes.TasksScreen.route) {
                TasksScreen(viewModel)
            }
            composable(Routes.NewTaskScreen.route) {
                NewTask(viewModel)
            }
            composable(
                route = Routes.EditTaskScreen.route,           // "editTask/{taskId}" — the template
                arguments = listOf(navArgument("taskId") { type = NavType.IntType })
            ) { backStackEntry ->
                val taskId = backStackEntry.arguments?.getInt("taskId") ?: return@composable
                EditTaskScreen(taskId = taskId, viewModel = viewModel, navController = navController)
            }
        }
    }
}
fun topBarText(controller: String?): String {
    return when (controller) {
        Routes.MainScreen.route -> {
            "Active Tasks"
        }

        Routes.TasksScreen.route -> {
            "Tasks"
        }

        Routes.NewTaskScreen.route -> {
            "New Task"
        }

        else -> {
            ""
        }
    }
}
@Composable
fun TopBarButton(
    controller: String?, navController: NavHostController
) {
    when (controller) {
        Routes.MainScreen.route, Routes.TasksScreen.route -> {
            IconButton(onClick = {
                navController.navigate(Routes.NewTaskScreen.route) {
                    launchSingleTop = true
                    restoreState = true
                }
            })
            {
                Icon(
                    painterResource(id = R.drawable.add),
                    modifier = Modifier.size(40.dp),
                    contentDescription = "More",
                )
            }
        }
    }
}
@Composable
fun TopBarNavigationIcon(
    currentRoute: String?,
    navController: NavHostController
) {
    if (currentRoute == Routes.NewTaskScreen.route) {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
    }
}
fun onClickNavigation(
    navController: NavHostController,
    destination: String,
    home: String = Routes.MainScreen.route
) {
    navController.navigate(destination) {
        launchSingleTop = true
        restoreState = true
        popUpTo(home) {
            saveState
        }
    }
}