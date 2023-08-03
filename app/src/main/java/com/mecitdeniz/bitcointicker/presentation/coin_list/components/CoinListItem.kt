package com.mecitdeniz.bitcointicker.presentation.coin_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mecitdeniz.bitcointicker.common.toFixedString
import com.mecitdeniz.bitcointicker.domain.model.Coin
import com.mecitdeniz.bitcointicker.presentation.ui.theme.DarkGreen

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CoinListItem(
    modifier: Modifier = Modifier,
    coin: Coin,
    onClick: (String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
            .clickable {
                onClick(coin.id)
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = coin.marketCapRank.toInt().toString(),
                modifier = Modifier
                    .weight(0.1f)
                    .sizeIn(
                        minWidth = 25.dp, maxWidth = 35.dp,
                    ),
                maxLines = 1,
                style = MaterialTheme.typography.labelMedium,
                overflow = TextOverflow.Ellipsis
            )

            GlideImage(
                model = coin.image,
                modifier = Modifier
                    .size(35.dp)
                    .clip(shape = CircleShape),
                contentDescription = coin.name
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = coin.name,
                        maxLines = 1,
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = coin.symbol.uppercase(),
                        maxLines = 1,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.outline
                        ),
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "$ ${coin.currentPrice.toFixedString(2)}",
                        maxLines = 1,
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "${coin.priceChangePercentage24H.toFixedString(2)}%",
                        maxLines = 1,
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = if (coin.priceChangePercentage24H > 0) DarkGreen else Color.Red
                        ),
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}