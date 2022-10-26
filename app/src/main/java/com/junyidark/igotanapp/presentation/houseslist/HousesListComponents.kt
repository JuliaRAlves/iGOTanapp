package com.junyidark.igotanapp.presentation.houseslist

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.junyidark.igotanapp.R
import com.junyidark.igotanapp.presentation.core.SeeMoreIcon
import com.junyidark.igotanapp.presentation.core.Toolbar
import com.junyidark.igotanapp.presentation.theme.IGOTanappTheme

@Composable
fun HouseListItem(
    @DrawableRes coatOfArms: Int,
    name: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_16dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = coatOfArms),
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(id = R.dimen.list_item_size))
        )

        Text(
            text = name,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = dimensionResource(id = R.dimen.padding_12dp))
        )

        SeeMoreIcon(onClick = onClick)
    }
}

@Preview(showBackground = true)
@Composable
private fun IronHandComponentsPreview() {
    IGOTanappTheme {
        Column(Modifier.background(MaterialTheme.colors.secondary)) {
            Toolbar(modifier = Modifier.padding(bottom = 16.dp), onClickBack = { }, onClickMenu = { })
            HouseListItem(
                coatOfArms = R.drawable.house_stark,
                name = "House Stark",
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
            HouseListItem(
                coatOfArms = R.drawable.house_stark,
                name = "House Stark",
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
            HouseListItem(
                coatOfArms = R.drawable.house_stark,
                name = "House Stark",
                onClick = { }
            )
        }
    }
}