package com.mecitdeniz.bitcointicker.presentation.coin_detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mecitdeniz.bitcointicker.presentation.components.MaterialCard
import com.mecitdeniz.bitcointicker.presentation.components.MaterialCardVariant

@Composable
fun CoinDetailDescriptionCard(
    description: String,
    modifier: Modifier = Modifier
) {
    if (description.isEmpty()) return
    MaterialCard(
        variant = MaterialCardVariant.SECONDARY,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = description
        )
    }
}