package com.mecitdeniz.bitcointicker.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mecitdeniz.bitcointicker.R

@Composable
fun AppLogo(
    modifier: Modifier = Modifier,
    size: Dp = 100.dp
) {
    val context = LocalContext.current
    Image(
        painter = painterResource(id = R.drawable.baseline_currency_bitcoin_24),
        contentDescription = context.getString(R.string.app_logo),
        modifier = modifier
            .size(size)
            .clip(shape = RoundedCornerShape(20))
            .background(color = MaterialTheme.colorScheme.tertiaryContainer)
    )
}

@Preview
@Composable
fun AppLogoPreview() {
    AppLogo()
}