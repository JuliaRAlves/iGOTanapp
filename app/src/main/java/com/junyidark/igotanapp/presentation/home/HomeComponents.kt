package com.junyidark.igotanapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.junyidark.igotanapp.R
import com.junyidark.igotanapp.presentation.core.formattedAppTitle
import com.junyidark.igotanapp.presentation.theme.IGOTanappTheme
import com.junyidark.igotanapp.presentation.theme.Shapes

@Composable
fun Title() {
    Text(
        formattedAppTitle(
            firstPart = R.string.home_app_name_pt1,
            secondPart = R.string.home_app_name_pt2,
            lastPart = R.string.home_app_name_pt3,
            firstPartStyle = MaterialTheme.typography.h2,
            secondPartStyle = MaterialTheme.typography.h1,
            lastPartStyle = MaterialTheme.typography.h2
        )
    )
}

@Composable
fun SearchBar(onTextChange: (String) -> Unit) {
    Column {
        var textState by remember { mutableStateOf("") }

        Text(
            text = stringResource(id = R.string.home_search_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = R.dimen.padding_8dp)),
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.onSecondary,
            style = MaterialTheme.typography.body1
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
                onTextChange(it)
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

@Composable
fun SearchButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.padding_24dp)),
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
        onClick = onClick
    ) {
        Text(
            text = stringResource(id = R.string.home_search_button_text),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_12dp))
        )
    }
}

@Composable
fun CallToPage(page: String, onClick: (Int) -> Unit) {
    Row(
        modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_8dp)),
    ) {
        Text(
            text = stringResource(id = R.string.home_call_to_page),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSecondary
        )
        ClickableText(
            text = AnnotatedString(page),
            style = MaterialTheme.typography.body2.copy(
                textDecoration = TextDecoration.Underline,
                color = MaterialTheme.colors.onSecondary
            ),
            onClick = onClick
        )
    }
}

@Composable
fun Copyright(onClick: (Int) -> Unit) {
    ClickableText(
        text = formattedCopyright(),
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    )
}

@Composable
fun formattedCopyright(): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = ParagraphStyle().copy(textAlign = TextAlign.Center)) {
            withStyle(
                style = MaterialTheme.typography.overline.toSpanStyle()
                    .copy(color = MaterialTheme.colors.onSecondary)
            ) {
                append(stringResource(id = R.string.home_copyright_pt1))
            }

            withStyle(
                style = MaterialTheme.typography.overline.toSpanStyle()
                    .copy(color = MaterialTheme.colors.primary)
            ) {
                append(stringResource(id = R.string.home_copyright_author))
            }

            withStyle(
                style = MaterialTheme.typography.overline.toSpanStyle()
                    .copy(color = MaterialTheme.colors.onSecondary)
            ) {
                append(stringResource(id = R.string.home_copyright_pt2))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IronHandComponentsPreview() {
    IGOTanappTheme {
        Column(Modifier.background(MaterialTheme.colors.background)) {
            Title()
            Spacer(modifier = Modifier.heightIn(12.dp))
            SearchBar {}
            Spacer(modifier = Modifier.heightIn(12.dp))
            SearchButton {}
            Spacer(modifier = Modifier.heightIn(12.dp))
            CallToPage(page = "the houses") {}
            Spacer(modifier = Modifier.heightIn(12.dp))
            Copyright {}
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DeepRiversComponentsPreview() {
    IGOTanappTheme(deepRiversEnabled = true) {
        Column(Modifier.background(MaterialTheme.colors.background)) {
            Title()
            Spacer(modifier = Modifier.heightIn(12.dp))
            SearchBar {}
            Spacer(modifier = Modifier.heightIn(12.dp))
            SearchButton {}
            Spacer(modifier = Modifier.heightIn(12.dp))
            CallToPage(page = "the houses") {}
            Spacer(modifier = Modifier.heightIn(12.dp))
            Copyright {}
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IntoTheSunComponentsPreview() {
    IGOTanappTheme(intoTheSunEnabled = true) {
        Column(Modifier.background(MaterialTheme.colors.background)) {
            Title()
            Spacer(modifier = Modifier.heightIn(20.dp))
            SearchBar {}
            Spacer(modifier = Modifier.heightIn(20.dp))
            SearchButton {}
            Spacer(modifier = Modifier.heightIn(12.dp))
            CallToPage(page = "the houses") {}
            Spacer(modifier = Modifier.heightIn(12.dp))
            Copyright {}
        }
    }
}