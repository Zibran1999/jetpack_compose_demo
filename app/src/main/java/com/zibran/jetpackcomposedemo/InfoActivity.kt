package com.zibran.jetpackcomposedemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.zibran.jetpackcomposedemo.data.TvShowList
import com.zibran.jetpackcomposedemo.model.TvShow
import com.zibran.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import com.zibran.jetpackcomposedemo.viewModel.CountViewModel

class InfoActivity : ComponentActivity() {
    companion object {
        private const val TvShowId = "tvshowid"
        fun intent(context: Context, tvShow: TvShow) =
            Intent(context, InfoActivity::class.java).apply {
                putExtra(TvShowId, tvShow)
            }
    }

    private val tvShow: TvShow by lazy {
        intent?.getSerializableExtra(TvShowId) as TvShow
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = ViewModelProvider(this)[CountViewModel::class.java]

//            var count by remember { mutableStateOf(0) } // using by keyword we delegate the object
            // var count by rememberSaveable{ mutableStateOf(0) } // remeberSaveble: it's used for to save state when change configuration in the app, configuration means rotate mobile screen, change language, etc.

            //Note we should avoid to use of rememberSaveable in complex data

            val count = viewModel.count

            ViewMoreInfo(tvShow = tvShow, count) {
                viewModel.increaseCount()
            }
        }
    }
}

@Composable
fun ViewMoreInfo(tvShow: TvShow, currentCount: Int, undatedCount: (Int) -> Unit) {
    val scrollState = rememberScrollState()

    Card(
        modifier = Modifier.padding(10.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(CornerSize(10.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = tvShow.imageId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop

            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = tvShow.name, style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = tvShow.overview, style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Original release : ${tvShow.year}", style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "IMDB : ${tvShow.rating}", style = MaterialTheme.typography.h5
            )

//            var count = remember { mutableStateOf(0) }  // remember method remember the state of the value
            var count by remember { mutableStateOf(0) } // using by keyword we delegate the object
            val context = LocalContext.current
            Button(
                onClick = {
                    count += 1
                    Toast.makeText(context, "Clicked $count   ", Toast.LENGTH_SHORT).show()
                }, modifier = Modifier.padding(5.dp)
            ) {

                Text(
                    text = "Clicked:  $count", style = MaterialTheme.typography.h5
                )
            }

            /* unidirectional data flow or state hosting
            *   currentCount = current value to display
            *   updateCount = an event that request current value to change
            * 
            * */

            Button(
                onClick = {
                    undatedCount(currentCount)
                    Toast.makeText(context, "Clicked $currentCount   ", Toast.LENGTH_SHORT).show()
                }, modifier = Modifier.padding(5.dp)
            ) {

                Text(
                    text = "Clicked:  $currentCount", style = MaterialTheme.typography.h5
                )
            }

            /* unidirectional data flow */


        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeDemoTheme {

        val tvShow = TvShowList.tvShows
//        ViewMoreInfo(tvShow = tvShow[0])
    }
}