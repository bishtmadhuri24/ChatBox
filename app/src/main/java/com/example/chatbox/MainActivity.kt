package com.example.chatbox

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatbox.ui.theme.ChatBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatBoxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //MessageCard(message = ChatBox())

                    //MessageCard(message = ChatBox("Madhuri","hi there..."))
                }
                Scaffold(topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "ChatBox")
                        }
                    )
                }) {
                    Conversation(MessageData.chatdata)
                }
            }
        }
    }
}

data class ChatBox(val name:String,val msg:String)
@Composable
fun MessageCard(message:ChatBox) {

    Row( modifier =Modifier.padding(all = 4.dp)) {
        Image(painter = painterResource(id = R.drawable.ic_baseline_person_outline_24), contentDescription ="profile picture",
        modifier = Modifier
            .size(30.dp)
            .clip(CircleShape)
            .border(2.0.dp, color = MaterialTheme.colors.primary, CircleShape))
        Spacer(modifier = Modifier.width(4.dp))
        var isExpanded by remember { mutableStateOf(false) }

        Column(modifier = Modifier.clickable { isExpanded=!isExpanded }) {
            Text(text = message.name,
                color = MaterialTheme.colors.secondary)

            Spacer(modifier = Modifier.width(4.dp))

            Text(text = message.msg,
            style = MaterialTheme.typography.body2,
                maxLines = if (isExpanded) Int.MAX_VALUE else 1
            )

        }

    }

}

@Preview(showBackground = true)
@Preview(showBackground = true,name = "dark light",
    uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    ChatBoxTheme {
        MessageCard(message = ChatBox("Madhuri","hi there..."))
        Conversation(MessageData.chatdata)
    }
}

@Composable
fun Conversation(chat:List<ChatBox>){

    LazyColumn(){
        items(chat){data-> MessageCard(data) }

    }
}