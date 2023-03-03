package com.zibran.jetpackcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val painter = painterResource(id = R.drawable.anatomy)
//            val contentDescription = "Kermit playing in the snow"
//            val title = "Kermit playing in the snow"
//
//            Column {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth(0.5f)
//                        .padding(16.dp)
//                ) {
//                    ImageCard(
//                        painter = painter,
//                        title = title,
//                        contentDescription = contentDescription
//                    )
//                }
//
//
//                // text styling
//                Text(
//                    text = buildAnnotatedString {
//
//                        withStyle(
//                            style = SpanStyle(
//                                color = Color.Black,
//                                fontSize = 50.sp,
//                                fontFamily = FontFamily.Cursive
//                            )
//                        ) {
//                            append("Z")
//                        }
//                        append("ibran ")
//
//                        withStyle(
//                            style = SpanStyle(
//                                color = Color.Black,
//                                fontSize = 50.sp,
//                                fontFamily = FontFamily.Cursive
//                            )
//                        ) {
//                            append("K")
//                        }
//                        append("han")
//                    },
//                    color = Color.Gray,
//                    fontSize = 30.sp,
//                    fontFamily = FontFamily.Cursive,
//                    fontWeight = FontWeight.Bold,
//                    fontStyle = FontStyle.Italic,
//                    textAlign = TextAlign.Center,
//                    textDecoration = TextDecoration.Underline
//                )
//
//
//                Column(modifier = Modifier.fillMaxSize()) {
//                    val color = remember {
//                        mutableStateOf(Color.Yellow)
//                    }
//
//                    ColorBox(
//                        Modifier
//                            .weight(1f)
//                            .fillMaxSize()
//                    ) {
//                        color.value = it
//                    }
//
//                    Box(
//                        modifier = Modifier
//                            .background(color = color.value)
//                            .weight(1f)
//                            .fillMaxSize()
//                    )
//                }
//
//                TextFieldDemo()
//            }
//

//            TextFieldDemo()
//            ListDemo()
//            LazyColumnWithIndexItemListDemo()
            ConstraintLayoutDemo()

        }
    }
}

@Composable
fun Greeting(name: String) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Hello $name!")
        Text(text = "Hi $name!")
        Text(text = "Hi $name!")

    }
}

@Composable
fun ImageCard(
    painter: Painter,
    title: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp
    ) {

        Box(modifier = Modifier.height(200.dp)) {

            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(title, style = TextStyle(color = Color.White, fontSize = 16.sp))
            }
        }

    }

}


@Composable
fun ColorBox(
    modifier: Modifier = Modifier,
    updateColor: (Color) -> Unit,
) {
    Box(modifier = modifier
        .background(Color.Red)
        .clickable {
            updateColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            )

        }) {

    }
}


@Composable
fun TextFieldDemo() {
    val scaffold = rememberScaffoldState()
    var textField by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffold
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            TextField(
                value = textField,
                label = {
                    Text(text = "Enter your name")
                }, onValueChange = {
                    textField = it
                }, singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                scope.launch {
                    scaffold.snackbarHostState.showSnackbar("Hello $textField")
                }

            }, modifier = Modifier.padding(it)) {
                Text(text = "Please greet me")
            }

        }

    }
}


@Composable
fun ListDemo() {

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        for (i in 1..50) {
            Text(
                text = "Item $i",
                fontFamily = FontFamily.Monospace,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(
                modifier = Modifier
                    .height(5.dp)
                    .background(Color.Black)
            )
        }

    }
}

@Composable
fun LazyColumnListDemo() {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(50) {
            Text(
                text = "Item $it",
                fontFamily = FontFamily.Monospace,
                fontSize = 26.sp,
                color = Color.Black
            )
            Spacer(
                modifier = Modifier
                    .height(5.dp)
                    .background(Color.Black)
            )
        }
    }
}

@Composable
fun LazyColumnWithIndexItemListDemo() {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        itemsIndexed(
            listOf(
                "Kotlin",
                "Android",
                "Java",
                "Jetpack Compose",
                "Data Binding",
                "Dagger Hilt",
                "MVVM"
            )
        ) { index, string ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = 5.dp
            ) {
                Text(
                    text = "$string : $index",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 26.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(10.dp)
                )

            }
        }
    }
}

@Composable
fun ConstraintLayoutDemo() {

    val constraints = ConstraintSet {
        val greenBox = createRefFor("greenBox")
        val redBox = createRefFor("redBox")

        constrain(greenBox) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(redBox) {
            top.linkTo(parent.top)
            start.linkTo(greenBox.end)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }
        createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Spread)
    }


    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .background(Color.Green)
                .layoutId("greenBox")
        )
        Box(
            modifier = Modifier
                .background(Color.Red)
                .layoutId("redBox")
        )

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
//    val painter = painterResource(id = R.drawable.anatomy)
//    val contentDescription = "Kermit playing in the snow"
//    val title = "Kermit playing in the snow"
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth(0.5f)
//            .padding(16.dp)
//    ) {
//        ImageCard(
//            painter = painter,
//            title = title,
//            contentDescription = contentDescription
//        )
//    }

//    ListDemo()
//    LazyColumnWithIndexItemListDemo()
    ConstraintLayoutDemo()

}