package com.junyidark.igotanapp.presentation.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.junyidark.igotanapp.R
import com.junyidark.igotanapp.presentation.theme.IGOTanappTheme
import com.junyidark.igotanapp.presentation.theme.Shapes

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IGOTanappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun Title() {
    IGOTanappTheme {
        Text(formattedTitle())
    }
}

@Preview(showBackground = false)
@Composable
fun SearchBar() {
    IGOTanappTheme {
        Column {
            var textState by remember { mutableStateOf("") }

            Text(
                text = stringResource(id = R.string.home_search_label),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = R.dimen.padding_8dp)),
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.onSecondary
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = textState,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.onSecondary,
                    cursorColor = MaterialTheme.colors.onPrimary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    textState = it
                },
                shape = Shapes.large,
                singleLine = true,
                trailingIcon = {
                    if (textState.isNotEmpty()) {
                        IconButton(onClick = { textState = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun formattedTitle(): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = ParagraphStyle()) {
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colors.onSecondary,
                    fontSize = 36.sp
                )
            ) {
                append("I ")
            }

            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colors.primary,
                    fontSize = 48.sp
                )
            ) {
                append("GOT\n")
            }

            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colors.onSecondary,
                    fontSize = 36.sp
                )
            ) {
                append("an app")
            }
        }
    }
}