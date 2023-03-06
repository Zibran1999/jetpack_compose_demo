package com.zibran.jetpackcomposedemo.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zibran.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme

class NavigationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                // A surface container using the 'background' color from the theme

                Navigation()
            }
        }
    }
}


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {

            MainScreen(navController = navController)
        }

        composable(
            route = Screen.DetailScreen.route + "/{name}",
            arguments = listOf(navArgument("name") {
                type = NavType.StringType
                defaultValue = "Zibran"
                nullable = true
            })
        ) { entry ->
            DetailScreen(name = entry.arguments?.getString("name"))
        }
    }

}


@Composable
fun MainScreen(navController: NavController) {

    var text by remember {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {

        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            navController.navigate(Screen.DetailScreen.withArgs(text))
        }) {
            Text(text = "to DetailScreen")

        }
    }

}

@Composable
fun DetailScreen(name: String?) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Text(text = "Hello $name")
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview6() {
    JetpackComposeDemoTheme {
        Navigation()
    }
}