package com.zibran.jetpackcomposedemo.instagramprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.zibran.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme

class InstaProfileUIActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                // A surface container using the 'background' color from the theme

                ProfileScreen()

            }
        }
    }
}

//@Composable
//fun ProfileScreen() {
//    TopBar(name = "phillp_lakner_official")
//    Spacer(modifier = Modifier.height(4.dp))
//}
//
//
//@Composable
//fun TopBar(name: String, modifier: Modifier = Modifier) {
//    Row(
//        modifier = modifier.fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceAround
//    ) {
//
//        Icon(
//            imageVector = Icons.Default.ArrowBack,
//            contentDescription = null,
//            modifier = modifier.size(24.dp),
//            tint = Color.Black
//        )
//
//        Text(
//            text = name,
//            overflow = TextOverflow.Ellipsis,
//            fontWeight = FontWeight.Bold,
//            fontSize = 20.sp
//        )
//        Icon(
//            painter = painterResource(id = R.drawable.ic_bell),
//            contentDescription = null,
//            modifier = modifier.size(24.dp),
//            tint = Color.Black
//        )
//        Icon(
//            painter = painterResource(id = R.drawable.ic_dotmenu),
//            contentDescription = null,
//            modifier = modifier.size(24.dp),
//            tint = Color.Black
//        )
//
//    }
//}
//
//@Composable
//fun ProfileSection(modifier: Modifier = Modifier) {
//
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(horizontal = 20.dp)
//    ) {
//        Row(verticalAlignment = Alignment.CenterVertically) {
//
//            RoundImage(painterResource(id = R.drawable.philipp),
//                Modifier
//                    .size(100.dp)
//                    .weight(3f))
//
//        }
//
//    }
//}
//
//@Composable
//fun RoundImage(painter: Painter, modifier: Modifier) {
//
//    Image(
//        painter = painter,
//        contentDescription = null,
//        modifier = modifier
//            .aspectRatio(1f, matchHeightConstraintsFirst = true)
//            .border(
//                1.dp, color = Color.LightGray,
//                shape = CircleShape
//            )

//            .padding(3.dp)
//            .clip(CircleShape)
//    )

//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    JetpackComposeDemoTheme {
        ProfileScreen()
    }
}