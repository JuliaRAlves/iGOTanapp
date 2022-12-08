package com.junyidark.igotanapp.presentation.characterslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.junyidark.igotanapp.R
import com.junyidark.igotanapp.presentation.core.SeeMoreIcon
import com.junyidark.igotanapp.presentation.core.Toolbar
import com.junyidark.igotanapp.presentation.theme.IGOTanappTheme
import com.junyidark.igotanapp.presentation.theme.Theme

@Composable
fun CharacterListItem(
    photoUrl: String,
    name: String,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val defaultPadding = dimensionResource(id = R.dimen.padding_16dp)
    Column(
        verticalArrangement = Arrangement.spacedBy(defaultPadding),
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = defaultPadding,
                start = defaultPadding,
                end = defaultPadding,
                bottom = 0.dp
            )
    ) {
        Row(
            verticalAlignment = CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = photoUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .size(dimensionResource(id = R.dimen.list_item_size))
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_12dp))
                    .weight(1f)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSecondary,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_4dp))
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.overline,
                    color = MaterialTheme.colors.onSecondary
                )
            }

            SeeMoreIcon(onClick = onClick)
        }

        Divider(
            thickness = dimensionResource(id = R.dimen.divider_thickness),
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IronHandComponentsPreview() {
    IGOTanappTheme(theme = Theme.IRON_HAND) {
        Column(Modifier.background(MaterialTheme.colors.secondary)) {
            Toolbar(modifier = Modifier.padding(bottom = 16.dp), onClickBack = { }, onClickMenu = { })
            CharacterListItem(
                photoUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fthenexus.one%2Fgame-of-thrones-todos-que-sabiam-que-jon-snow-era-um-targaryen-antes-dele%2F&psig=AOvVaw1UmiIGwmrWLmZnyvLYpJVc&ust=1666791581081000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCIDp_8W_-_oCFQAAAAAdAAAAABAE",
                name = "Jon Snow",
                title = "King of the North",
                onClick = { }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DeepRiversComponentsPreview() {
    IGOTanappTheme(theme = Theme.DEEP_RIVERS) {
        Column(Modifier.background(MaterialTheme.colors.secondary)) {
            Toolbar(modifier = Modifier.padding(bottom = 16.dp), onClickBack = { }, onClickMenu = { })
            CharacterListItem(
                photoUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fthenexus.one%2Fgame-of-thrones-todos-que-sabiam-que-jon-snow-era-um-targaryen-antes-dele%2F&psig=AOvVaw1UmiIGwmrWLmZnyvLYpJVc&ust=1666791581081000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCIDp_8W_-_oCFQAAAAAdAAAAABAE",
                name = "Jon Snow",
                title = "King of the North",
                onClick = { }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IntoTheSunComponentsPreview() {
    IGOTanappTheme(theme = Theme.INTO_THE_SUN) {
        Column(Modifier.background(MaterialTheme.colors.secondary)) {
            Toolbar(modifier = Modifier.padding(bottom = 16.dp), onClickBack = { }, onClickMenu = { })
            CharacterListItem(
                photoUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fthenexus.one%2Fgame-of-thrones-todos-que-sabiam-que-jon-snow-era-um-targaryen-antes-dele%2F&psig=AOvVaw1UmiIGwmrWLmZnyvLYpJVc&ust=1666791581081000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCIDp_8W_-_oCFQAAAAAdAAAAABAE",
                name = "Jon Snow",
                title = "King of the North",
                onClick = { }
            )
        }
    }
}