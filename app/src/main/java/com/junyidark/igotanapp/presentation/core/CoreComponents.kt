package com.junyidark.igotanapp.presentation.core

import androidx.annotation.StringRes
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.junyidark.igotanapp.R
import com.junyidark.igotanapp.presentation.theme.IGOTanappTheme
import com.junyidark.igotanapp.presentation.theme.Theme

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
                    firstPart = stringResource(id = R.string.toolbar_app_name_pt1),
                    secondPart = stringResource(id = R.string.toolbar_app_name_pt2),
                    lastPart = stringResource(id = R.string.toolbar_app_name_pt3),
                    firstPartStyle = MaterialTheme.typography.body1,
                    secondPartStyle = MaterialTheme.typography.body1,
                    lastPartStyle = MaterialTheme.typography.body1,
                    colors = MaterialTheme.colors
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

fun formattedAppTitle(
    firstPart: String,
    secondPart: String,
    lastPart: String,
    firstPartStyle: TextStyle,
    secondPartStyle: TextStyle,
    lastPartStyle: TextStyle,
    colors: Colors
): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = ParagraphStyle()) {
            withStyle(
                style = firstPartStyle.toSpanStyle()
                    .copy(color = colors.onSecondary)
            ) {
                append(firstPart)
            }

            withStyle(
                style = secondPartStyle.toSpanStyle()
                    .copy(color = colors.primary)
            ) {
                append(secondPart)
            }

            withStyle(
                style = lastPartStyle.toSpanStyle()
                    .copy(color = colors.onSecondary)
            ) {
                append(lastPart)
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
    modifier: Modifier = Modifier,
    photoUrl: String? = null,
    drawableRes: Int? = null,
    name: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_16dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        val painter = if (drawableRes != null) painterResource(id = drawableRes)
        else rememberAsyncImagePainter(model = photoUrl)

        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .size(dimensionResource(id = R.dimen.details_photo_size))
        )

        Text(
            text = name,
            textAlign = TextAlign.Center,
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

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoadingAnimation()
    }
}

@Composable
private fun LoadingAnimation(
    indicatorSize: Dp = dimensionResource(id = R.dimen.loading_indicator_size),
    circleColors: List<Color> = listOf(
        MaterialTheme.colors.secondary,
        MaterialTheme.colors.background,
        MaterialTheme.colors.primary,
        MaterialTheme.colors.onSecondary,
        MaterialTheme.colors.primary,
        MaterialTheme.colors.background,
        MaterialTheme.colors.secondary
    ),
    animationDuration: Int = 360
) {

    val infiniteTransition = rememberInfiniteTransition()

    val rotateAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration,
                easing = LinearEasing
            )
        )
    )

    CircularProgressIndicator(
        modifier = Modifier
            .size(size = indicatorSize)
            .rotate(degrees = rotateAnimation)
            .border(
                width = dimensionResource(id = R.dimen.loading_circle_width),
                brush = Brush.sweepGradient(circleColors),
                shape = CircleShape
            ),
        progress = 1f,
        strokeWidth = 1.dp,
        color = MaterialTheme.colors.background // Set background color
    )
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.error),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ice_king),
            contentDescription = null,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_8dp))
        )
        Text(
            text = stringResource(id = R.string.error_message),
            color = MaterialTheme.colors.onError,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_16dp))
        )
    }
}