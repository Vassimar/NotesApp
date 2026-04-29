package com.example.noteswithregistration.system.screens

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteswithregistration.R
import com.example.noteswithregistration.system.Routes

@Composable
internal fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    MainScreenContent(navController, currentRoute)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreenContent(
    navController: NavHostController,
    currentRoute: String?
) {
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
                        currentRoute = currentRoute,
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar {
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
                            contentDescription = stringResource(id = R.string.Home),
                        )
                    },
                    label = { Text(stringResource(R.string.Home)) },
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
                            contentDescription = stringResource(R.string.Tasks),
                        )
                    },
                    label = { Text(stringResource(R.string.Tasks)) },
                )
            }
        }) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = Routes.MainScreen.route
        ) {
            composable(Routes.MainScreen.route) {
                ActiveTasksScreen(onNavigateToEdit = { taskId ->
                    navController.navigate(Routes.EditTaskScreen.withArgs(taskId))
                })
            }
            composable(Routes.TasksScreen.route) {
                TasksScreen()
            }
            composable(Routes.NewTaskScreen.route) {
                CreateTaskScreen()
            }
            composable(
                route = Routes.EditTaskScreen.route,
                arguments = listOf(navArgument("taskId") { type = NavType.IntType })
            ) {
                EditTaskScreen(
                    onNavigateSave = { navController.popBackStack() },
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }

}

@Composable
private fun topBarText(currentRoute: String?): String {
    return when (currentRoute) {
        Routes.MainScreen.route -> {
            stringResource(id = R.string.active_tasks)
        }

        Routes.TasksScreen.route -> {
            stringResource(id = R.string.Tasks)
        }

        Routes.NewTaskScreen.route -> {
            stringResource(id = R.string.new_task)
        }

        Routes.EditTaskScreen.route -> {
            stringResource(id = R.string.edit_task)
        }

        else -> {
            ""
        }
    }
}

@Composable
private fun TopBarButton(
    currentRoute: String?, navController: NavHostController
) {
    when (currentRoute) {
        Routes.MainScreen.route, Routes.TasksScreen.route -> {
            IconButton(onClick = {
                navController.navigate(Routes.NewTaskScreen.route) {
                    launchSingleTop = true
                }
            })
            {
                Icon(
                    painterResource(id = R.drawable.add),
                    modifier = Modifier.size(40.dp),
                    contentDescription = stringResource(id = R.string.more),
                )
            }
        }
    }
}

@Composable
private fun TopBarNavigationIcon(
    currentRoute: String?,
    navController: NavHostController
) {
    if (currentRoute == Routes.NewTaskScreen.route || currentRoute == Routes.EditTaskScreen.route) {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back)
            )
        }
    }
}

private fun onClickNavigation(
    navController: NavHostController,
    destination: String,
    home: String = Routes.MainScreen.route
) {
    navController.navigate(destination) {
        launchSingleTop = true
        popUpTo(home)
    }
}