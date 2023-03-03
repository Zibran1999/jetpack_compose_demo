package com.zibran.jetpackcomposedemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.zibran.jetpackcomposedemo.compose.TvShowListItem
import com.zibran.jetpackcomposedemo.coroutineflow.CoroutineFlowActivity
import com.zibran.jetpackcomposedemo.data.TvShowList
import com.zibran.jetpackcomposedemo.model.TvShow
import com.zibran.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

//            Column {
//                DisplaySnackBar {
//                    Toast.makeText(this@MainActivity, "$it", Toast.LENGTH_SHORT).show()
//                }
//
//                MessageCard(name = "Android")
//                MessageCard(name = "Zibran")
//                MessageCard(name = "Developer")
//            }

            DisplayListItem {
                startActivity(InfoActivity.intent(this, it))
//                Toast.makeText(this, "Item : ${it.name}", Toast.LENGTH_SHORT).show()
            }
        }

    }
}

@Composable
fun MessageCard(name: String) {  // Text works like a TextView
    Text(
        text = name,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .background(color = Color.Gray)
            .border(2.dp, Color.DarkGray)
            .padding(10.dp)

    )
}


@Composable
fun DisplaySnackBar(selectedItem: (String) -> Unit) {   // SnackBar
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(scaffoldState = scaffoldState) {

        Button(onClick = {

            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    "This is Snack Bar",
                    actionLabel = "UNDO",
                    duration = SnackbarDuration.Indefinite
                )
            }
        }, modifier = Modifier.padding(it)) {
            Text(text = "Display SnackBar")
        }
    }

}


@Composable
fun LazyColoumnDemoWithClick(selectedItem: (String) -> Unit) {  // LazyColumn works like a recyclerView
    LazyColumn {
        items(100) {
            Text(text = "Item $it",
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        selectedItem("$it  Item Selected")
                    })
            Divider(color = Color.Black, thickness = 5.dp)
        }
    }
}

@Composable
fun LazyColumnDemo() {  // LazyColumn works like a recyclerView
    LazyColumn {
        items(100) {
            Text(
                text = "Item $it",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(10.dp)

            )
            Divider(color = Color.Black, thickness = 5.dp)
        }
    }
}

@Composable
fun DisplayListItem(selectedItem: (TvShow) -> Unit) {
    val tvShow = remember { TvShowList.tvShows }
    val context = LocalContext.current
    Column {
        Button(onClick = {
            startActivity(context, Intent(context, CoroutineFlowActivity::class.java), null)

        }, modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "Check Flow", style = MaterialTheme.typography.h5
            )
        }
        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
            items(tvShow) {
                TvShowListItem(tvShow = it, selectedItem = selectedItem)
            }
        }
    }

}


@Preview
@Composable
fun PreviewCard() {
    JetpackComposeDemoTheme {
        DisplayListItem {

        }
    }
}
