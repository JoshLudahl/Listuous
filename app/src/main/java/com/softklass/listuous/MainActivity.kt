package com.softklass.listuous

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softklass.listuous.models.Item
import com.softklass.listuous.models.ItemList
import com.softklass.listuous.models.ListOfItemList
import com.softklass.listuous.ui.theme.Blue
import com.softklass.listuous.ui.theme.LightOrange

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScreenOutline()
        }
    }
}

@Preview
@Composable
fun ScreenOutline() {
    BoxWithConstraints(
        Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .size(width = maxWidth, height = maxHeight)
                .background(color = LightOrange)
        ) {
            MainLayout()
        }
    }
}

@Composable
fun MainLayout() {

    Column(
        Modifier.fillMaxSize()
    ) {
        Row(modifier = Modifier.align(CenterHorizontally)) {
            ListTitle()
        }
        Row {
            val list = ListOfItemList(
                lists = mutableListOf()
            )
            list.lists.add(
                ItemList(
                    "Grocery",
                    mutableListOf(
                        Item(" this is an item")
                    )
                )

            )
            list.lists.add(
                ItemList(
                    "To-Do",
                    mutableListOf(
                        Item(" this is an item")
                    )
                )

            )
            list.lists.add(
                ItemList(
                    "Errands",
                    mutableListOf(
                        Item(" this is an item")
                    )
                )
            )
            ListContainer(list)
        }
        Row {
            SimpleFilledTextFieldSample()
        }
    }
}

@Composable
fun ListTitle() {

    Text("List Title")
}

@Composable
fun ListContainer(listOfLists: ListOfItemList) {
    LazyColumn(Modifier.background(color = LightOrange)) {
        items(listOfLists.lists.size) { message ->
            ItemListItem(listOfLists.lists.elementAt(message))
        }
    }
}

@Composable
fun ItemListItem(itemsList: ItemList) {
    Row {
        Card(itemsList = itemsList)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Card(itemsList: ItemList) {
    BoxWithConstraints(
        Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .size(width = maxWidth, height = maxHeight)

        ) {
            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(5.dp)
            ) {
                Card(
                    backgroundColor = Blue,
                    elevation = 4.dp,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        Toast.makeText(
                            context,
                            "Clicked ${itemsList.listName}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(text = itemsList.listName)
                    }
                }
            }
        }
    }
}

@Composable
fun SimpleFilledTextFieldSample() {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("New Item") }
    )
}
