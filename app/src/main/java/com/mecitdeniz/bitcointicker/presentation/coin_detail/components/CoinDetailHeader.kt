package com.mecitdeniz.bitcointicker.presentation.coin_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mecitdeniz.bitcointicker.R
import com.mecitdeniz.bitcointicker.common.toFixedString
import com.mecitdeniz.bitcointicker.domain.model.CoinDetail
import com.mecitdeniz.bitcointicker.presentation.components.MaterialCard
import com.mecitdeniz.bitcointicker.presentation.components.MaterialCardVariant
import com.mecitdeniz.bitcointicker.presentation.ui.theme.DarkGreen

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CoinDetailHeader(
    coin: CoinDetail,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    MaterialCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp),
        variant = MaterialCardVariant.TERTIARY
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20))
                    .background(color = MaterialTheme.colorScheme.secondary)
                    .padding(
                        vertical = 3.dp,
                        horizontal = 6.dp
                    ),
            ) {
                Text(
                    text = "${context.getString(R.string.rank)} ${coin.marketCapRank.toInt()}",
                    style = MaterialTheme.typography.labelSmall
                        .copy(
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                )
            }

            Row(
                modifier = Modifier.padding(
                    top = 16.dp,
                    bottom = 4.dp
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                GlideImage(
                    model = coin.image,
                    modifier = Modifier
                        .size(45.dp)
                        .clip(shape = CircleShape)
                        .background(color = MaterialTheme.colorScheme.tertiaryContainer),
                    contentDescription = coin.name
                )

                Text(text = coin.name, style = MaterialTheme.typography.headlineSmall)

                Text(
                    text = "(${coin.symbol.uppercase()})",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                )
            }

            Row(
                modifier = Modifier.padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "\$ ${coin.currentPrice?.toFixedString(2)}",
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.width(4.dp))

                val changePercentage = coin.priceChangePercentage24H

                Icon(
                    imageVector = if (changePercentage > 0) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    tint = if (changePercentage > 0) DarkGreen else Color.Red,
                    contentDescription = null
                )

                Text(
                    text = changePercentage.toFixedString(2),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = if (changePercentage > 0) DarkGreen else Color.Red
                    )
                )
            }

            coin.hashingAlgorithm?.let { algorithm ->
                HorizontalDivider(color = MaterialTheme.colorScheme.onTertiaryContainer)
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = context.getString(R.string.hashing_algorithm),
                        style = MaterialTheme.typography.bodySmall
                    )

                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(15))
                            .background(color = MaterialTheme.colorScheme.outline)
                            .padding(
                                vertical = 2.dp,
                                horizontal = 4.dp
                            ),
                    ) {
                        Text(
                            text = algorithm,
                            style = MaterialTheme.typography.bodySmall
                                .copy(
                                    color = MaterialTheme.colorScheme.outlineVariant
                                )
                        )
                    }
                }
            }
        }
    }

}