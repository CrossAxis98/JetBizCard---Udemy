package com.example.jetbizcardsam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetbizcardsam.ui.theme.JetBizCardSamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBizCardSamTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard() {
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Card(modifier = Modifier
            .padding(12.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(corner = CornerSize(12.dp) ),
        elevation = 4.dp) {
            Column(modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
                CreateProfileImage()
                Divider()
                ProvideProgrammerInfo()
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    buttonClickedState.value = !buttonClickedState.value
                }) {
                    Text(
                        text = "Portfolio",
                        style = MaterialTheme.typography.button
                    )
                }
                if (buttonClickedState.value) {
                    val listOfProjects = listOf(
                        "Project1",
                        "Project2",
                        "Project3"
                    )
                    PortfolioContent(listOfProjects)
                } else {
                    Box{}
                }
            }
        }
    }
}

@Composable
private fun PortfolioContent(listOfProjects: List<String>) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        elevation = 4.dp,
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        LazyColumn {
            items(listOfProjects) {
                ProjectItem(it)
            }
        }
    }
}

@Composable
private fun ProjectItem(it: String) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CreateProfileImage(Modifier.size(100.dp))
            Column(
                Modifier
                    .fillMaxHeight()
                    .padding(5.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = it)
                Text(text = "A great project")
            }
        }
    }
}

@Composable
private fun ProvideProgrammerInfo() {
    Column(modifier = Modifier.padding(4.dp)) {
        Text(
            text = "Robert K.",
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(3.dp)
        )
        Text(
            text = "Android Compose Programmer",
            modifier = Modifier.padding(3.dp)
        )
        Text(
            text = "@therobsonCompoose",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(3.dp)
        )
    }
}

@Composable
private fun CreateProfileImage(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(4.dp),
        shape = CircleShape,
        elevation = 4.dp
    ) {
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = "Profile image"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetBizCardSamTheme {
        CreateBizCard()
    }
}