package com.zibran.jetpackcomposedemo.notesapp.ui

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.lifecycleScope
import com.zibran.jetpackcomposedemo.R
import com.zibran.jetpackcomposedemo.notesapp.data.Notes
import com.zibran.jetpackcomposedemo.notesapp.model.NoteViewModel
import com.zibran.jetpackcomposedemo.notesapp.ui.ui.theme.JetpackComposeDemoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NoteActivity : ComponentActivity() {

    @Inject
    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var result by remember { mutableStateOf(0) }
            noteViewModel.getAllNotes()

            JetpackComposeDemoTheme {

                LaunchedEffect(key1 = true) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        noteViewModel.getAllNotes()
                    }
                }

                var list by remember {
                    mutableStateOf(emptyList<Notes>())
                }
                noteViewModel.noteLiveData.observe(this) {

                    list = it.reversed()
                    Log.i("Note", it.toString())
                }
                ShowNotes(list)

            }
        }
    }


    @Composable
    fun ShowNotes(noteList: List<Notes>) {

        val scaffoldState = rememberScaffoldState()
        val context = LocalContext.current
        val dismiss = remember {
            mutableStateOf(false)
        }




        Scaffold(scaffoldState = scaffoldState, topBar = {
            TopAppBar(elevation = 6.dp) {
                Row(modifier = Modifier.padding(10.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_menu_24),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(
                            Color.White
                        ),
                        modifier = Modifier.size(30.dp)
                    )
                    Text(
                        text = "NotesApp",
                        modifier = Modifier.padding(start = 10.dp),
                        color = Color.White,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,

                        )
                }
            }
        }, floatingActionButton = {
            ExtendedFloatingActionButton(text = { Text(text = "Add") },
                modifier = Modifier.padding(10.dp),
                icon = {
                    Icon(
                        Icons.Filled.Add, contentDescription = "Add"
                    )
                },

                onClick = {
                    dismiss.value = true

                })

            if (dismiss.value) {
                AddNotes(dismiss)
            }
        }, isFloatingActionButtonDocked = true, content = {
            Box(modifier = Modifier.padding(it)) {
                if (noteList.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()

                    ) {

                        items(items = noteList) { note ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
                                    .clickable(true, onClick = {
                                        Toast
                                            .makeText(context, note.noteTitle, Toast.LENGTH_SHORT)
                                            .show()
                                    }),
                                elevation = 8.dp,

                                shape = RoundedCornerShape(
                                    6.dp
                                )

                            ) {
                                Row() {
                                    val rainbowColorsBrush = remember {
                                        Brush.sweepGradient(
                                            listOf(
                                                Color(0xFF9575CD),
                                                Color(0xFFBA68C8),
                                                Color(0xFFE57373),
                                                Color(0xFFFFB74D),
                                                Color(0xFFFFF176),
                                                Color(0xFFAED581),
                                                Color(0xFF4DD0E1),
                                                Color(0xFF9575CD)
                                            )
                                        )
                                    }
                                    Image(
                                        painter = painterResource(id = R.drawable.anatomy),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(100.dp)
                                            .clip(CircleShape)
                                            .border(
                                                BorderStroke(4.dp, rainbowColorsBrush),
                                                CircleShape
                                            )
                                            .padding(4.dp)
                                    )
                                    Column {
                                        Text(
                                            text = note.noteTitle,
                                            color = Color.Black,
                                            modifier = Modifier.padding(start = 10.dp, top = 10.dp)

                                        )
                                        Spacer(modifier = Modifier.height(5.dp))
                                        Text(
                                            text = note.noteDesc,
                                            color = Color.Gray,
                                            modifier = Modifier.padding(
                                                start = 10.dp, top = 10.dp, bottom = 10.dp
                                            ),
                                        )
                                    }
                                }

                            }
                        }

                    }
                }
            }
        })

    }

    @OptIn(ExperimentalTextApi::class)
    @Composable
    fun AddNotes(dismiss: MutableState<Boolean>) {

        val context = LocalContext.current
        var noteTitle by remember {
            mutableStateOf("")
        }
        var noteDesc by remember {
            mutableStateOf("")
        }
        val brush = remember {
            Brush.linearGradient(
                colors = listOf(
                    Color.Cyan, Color.Red, Color.Yellow, Color.Blue
                )
            )
        }

        Dialog(onDismissRequest = { }) {

            Box(
                Modifier.fillMaxWidth()
            ) {
                Card(
                    Modifier.fillMaxWidth(), shape = RoundedCornerShape(
                        6.dp
                    ), elevation = 10.dp

                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = "Add Notes",
                            color = Color.Black,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Normal,
                            modifier = Modifier
                                .padding(
                                    start = 10.dp, top = 10.dp, bottom = 10.dp
                                )
                                .fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        OutlinedTextField(
                            value = noteTitle,
                            label = { Text(text = "Enter Your Note") },
                            onValueChange = { noteTitle = it },
                            textStyle = TextStyle(brush = brush),

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                        OutlinedTextField(
                            value = noteDesc,
                            label = { Text(text = "Enter Your Desc") },
                            onValueChange = { noteDesc = it },
                            textStyle = TextStyle(brush = brush),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Button(
                                onClick = { dismiss.value = false },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)

                            ) {

                                Text(text = "Cancel")
                            }
                            Button(onClick = {
                                addNotes(noteTitle, noteDesc, dismiss, context)

                            }) {

                                Text(text = "Add Note")
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))


                    }
                }


            }

        }
    }

    private fun addNotes(
        noteTitle: String,
        noteDesc: String,
        dismiss: MutableState<Boolean>,
        context: Context,
    ) {

        if (TextUtils.isEmpty(noteTitle)) {
            Toast.makeText(context, "Please Enter a Note!", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(noteDesc)) {
            Toast.makeText(context, "Please Enter a Note Description!", Toast.LENGTH_SHORT).show()
        } else {
            dismiss.value = false

            lifecycleScope.launch(Dispatchers.IO) {
                val result = noteViewModel.insertNote(
                    Notes(
                        0,
                        noteTitle,
                        noteDesc
                    )
                ).toInt()

                if (result != -1) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        Toast.makeText(context, "Note Added Successfully", Toast.LENGTH_SHORT)
                            .show()
                    }

//                    note ViewModel.getAllNotes()
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    JetpackComposeDemoTheme {

    }
}