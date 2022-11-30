package com.junyidark.igotanapp.presentation.core

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import coil.compose.rememberAsyncImagePainter
import com.junyidark.igotanapp.R

@Composable
fun Toolbar(
    onClickBack: () -> Unit,
    onClickMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = formattedAppTitle(
                    firstPart = R.string.toolbar_app_name_pt1,
                    secondPart = R.string.toolbar_app_name_pt2,
                    lastPart = R.string.toolbar_app_name_pt3,
                    firstPartStyle = MaterialTheme.typography.body1,
                    secondPartStyle = MaterialTheme.typography.body1,
                    lastPartStyle = MaterialTheme.typography.body1
                )
            )
        },
        backgroundColor = MaterialTheme.colors.secondary,
        navigationIcon = {
            AppBarIcon(icon = Icons.Filled.ArrowBack, onClick = { onClickBack() })
        },
        actions = {
            AppBarIcon(icon = Icons.Filled.Menu, onClick = { onClickMenu() })
        },
        modifier = modifier
    )
}

@Composable
fun formattedAppTitle(
    @StringRes firstPart: Int,
    @StringRes secondPart: Int,
    @StringRes lastPart: Int,
    firstPartStyle: TextStyle,
    secondPartStyle: TextStyle,
    lastPartStyle: TextStyle
): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = ParagraphStyle()) {
            withStyle(
                style = firstPartStyle.toSpanStyle()
                    .copy(color = MaterialTheme.colors.onSecondary)
            ) {
                append(stringResource(id = firstPart))
            }

            withStyle(
                style = secondPartStyle.toSpanStyle()
                    .copy(color = MaterialTheme.colors.primary)
            ) {
                append(stringResource(id = secondPart))
            }

            withStyle(
                style = lastPartStyle.toSpanStyle()
                    .copy(color = MaterialTheme.colors.onSecondary)
            ) {
                append(stringResource(id = lastPart))
            }
        }
    }
}

@Composable
private fun AppBarIcon(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Icon(imageVector = icon, contentDescription = null)
    }
}

@Composable
fun SeeMoreIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_2dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable { onClick() }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_see_more),
            tint = MaterialTheme.colors.primary,
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.see_more),
            style = MaterialTheme.typography.overline,
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
fun PhotoAndName(
    photoUrl: String,
    name: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_16dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = photoUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .size(dimensionResource(id = R.dimen.details_photo_size))
        )

        Text(
            text = name,
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
fun Section(
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_4dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onSecondary
        )

        Divider(
            thickness = dimensionResource(id = R.dimen.divider_thickness),
            color = MaterialTheme.colors.onPrimary
        )
    }
}