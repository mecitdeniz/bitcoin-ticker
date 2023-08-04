package com.mecitdeniz.bitcointicker.presentation.coin_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mecitdeniz.bitcointicker.R
import com.mecitdeniz.bitcointicker.presentation.components.MaterialCard
import com.mecitdeniz.bitcointicker.presentation.components.MaterialCardVariant

@Composable
fun CoinDetailActionCard(
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    onIconClick: () -> Unit,
    isInFavorites: Boolean = false
) {

    val context = LocalContext.current

    MaterialCard(
        variant = MaterialCardVariant.DEFAULT,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                DropDownSelector(
                    selectedOption = selectedOption,
                    options = options,
                    label = context.getString(R.string.refresh_interval),
                    modifier = Modifier.weight(1f),
                    onOptionSelected = {
                        onOptionSelected(it)
                    },
                )

                IconButton(
                    onClick = {
                        onIconClick()
                    }
                ) {
                    Icon(
                        imageVector = if (isInFavorites) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Fav",
                        tint = if (isInFavorites) Color.Red else MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}