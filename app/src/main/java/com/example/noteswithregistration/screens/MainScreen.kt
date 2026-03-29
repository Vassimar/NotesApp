package com.example.noteswithregistration.screens


import androidx.compose.foundation.background
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
import androidx.compose.material3.NavigationBarItemDefaults


import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.backgroundDark
import com.example.compose.primaryDark
import com.example.compose.secondaryDark
import com.example.compose.surfaceDark
import com.example.noteswithregistration.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val viewModel: MainViewModel = viewModel()
    val navController = rememberNavController()
    var plusColor by remember { mutableStateOf(primaryDark) }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = { Text("Create a new task") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceDark,
                    titleContentColor = primaryDark,
                    navigationIconContentColor = primaryDark,
                    actionIconContentColor = plusColor
                ),
                actions = {
                    IconButton(onClick = {
                        plusColor = if (plusColor == Color.Yellow) {
                            secondaryDark
                        } else {
                            primaryDark
                        }
                        navController.navigate(Routes.NewTaskScreen.route)


                    }) {
                        Icon(
                            painterResource(id = R.drawable.add),
                            modifier = Modifier.size(40.dp),
                            contentDescription = "More",

                            )

                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier,

                containerColor = surfaceDark,
                contentColor = primaryDark
            ) {
                NavigationBarItem(

                    selected = true,
                    onClick = { navController.navigate(Routes.MainScreen.route) },
                    icon = {
                        Icon(
                            painterResource(id = R.drawable.outline_home_24),
                            contentDescription = "Home"
                        )
                    },
                    label = { Text("Home", color = primaryDark) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = primaryDark,
                        selectedTextColor = primaryDark,
                        indicatorColor = backgroundDark,
                        unselectedIconColor = primaryDark,
                        unselectedTextColor = primaryDark
                    )
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate(Routes.TasksScreen.route) },
                    icon = {
                        Icon(
                            painterResource(R.drawable.baseline_checklist_24),
                            contentDescription = "Tasks",


                            )
                    },
                    label = { Text("Tasks", color = primaryDark) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = primaryDark,
                        selectedTextColor = primaryDark,
                        indicatorColor = backgroundDark,
                        unselectedIconColor = primaryDark,
                        unselectedTextColor = primaryDark
                    )
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
                if(viewModel.tasks.isEmpty()){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = surfaceDark
                        )
                )
                {
                    Text(
                        "Active task should be here", color = primaryDark,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                }else{
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
