package com.odom.week1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.odom.week1.ui.theme.Week1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week1Theme {
                // A surface container using the 'background' color from the theme
                // xml없이
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){

    // TODO: This state should be hoisted
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    if (shouldShowOnboarding == true){
        // 초기화면
        OnboardingScreen(onContinueClicked = {shouldShowOnboarding = false})
    }else{
        // 리스트
        Greetings()
    }

}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Composable
// 리스트 10개
private fun Greetings(names: List<String> = List(10){"$it"}) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)){
        items(items = names){name ->
            Greeting(name = name)
        }
    }
//    Column(modifier = Modifier.padding(vertical = 4.dp)) {
//        for (name in names) {
//            Greeting(name = name)
//        }
//    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    Week1Theme {
        OnboardingScreen(onContinueClicked = {}) // Do nothing on click.
    }
}

@Composable
private fun Greeting(name: String) {
    // To preserve state across recompositions, remember the mutable state using remember.
    val expanded = remember{ mutableStateOf(false)}

    // 펼쳐지면
    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        // 가로로 weight 1
        Row(modifier = Modifier.padding(24.dp)) {
            // 텍스트 2줄
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)) {

                Text(text = "Hello, ")
                Text(text = name)
            }

            // 버튼
            OutlinedButton(
                onClick = { expanded.value = !expanded.value }
            ) {
                Text(if(expanded.value) "Show less" else "Show more")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    Week1Theme {
        MyApp()
    }
}