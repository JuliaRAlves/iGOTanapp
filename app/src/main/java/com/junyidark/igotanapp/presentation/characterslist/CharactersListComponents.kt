package com.junyidark.igotanapp.presentation.characterslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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

@Composable
fun CharacterListItem(
    photoUrl: String,
    name: String,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_16dp)),
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
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_12dp))
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

        Spacer(modifier = Modifier.weight(1f))

        SeeMoreIcon(onClick = onClick)
    }
}

@Preview(showBackground = true)
@Composable
private fun IronHandComponentsPreview() {
    IGOTanappTheme {
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
    IGOTanappTheme(deepRiversEnabled = true) {
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
    IGOTanappTheme(intoTheSunEnabled = true) {
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