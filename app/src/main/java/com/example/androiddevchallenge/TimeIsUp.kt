package com.example.androiddevchallenge

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlarmOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun TimeIsUp(onReset: () -> Unit = {}) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Filled.AlarmOn,
            contentDescription = null,
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.size(96.dp)
        )
        Text(text = "Time is up", style = MaterialTheme.typography.h2)
        Button(onClick = onReset) {
            Text("Reset")
        }
    }
}

@Preview(widthDp = 560, heightDp = 640)
@Composable
fun PreviewLight() {
    MyTheme(darkTheme = false) {
        Surface {
            TimeIsUp()
        }
    }
}

@Preview
@Composable
fun PreviewDark() {
    MyTheme(darkTheme = true) {
        Surface {
            TimeIsUp()
        }
    }
}