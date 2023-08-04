package com.mecitdeniz.bitcointicker.presentation.coin_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DropDownSelector(
    selectedOption: String,
    options: List<String>,
    label: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    var selected by remember {
        mutableStateOf(selectedOption)
    }

    Row(
        modifier = modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(text = label, style = MaterialTheme.typography.labelLarge)

        Text(text = selected, style = MaterialTheme.typography.labelMedium)

        IconButton(onClick = { isExpanded = !isExpanded }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More"
            )
        }

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            if (options.isEmpty()) return@DropdownMenu
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(text = option)
                    },
                    onClick = {
                        isExpanded = false
                        selected = option
                        onOptionSelected(option)
                    }
                )
            }
        }
    }
}


@Preview
@Composable
fun DropDownSelectorPrev() {
    DropDownSelector(
        selectedOption = "30s",
        options = listOf("30s", "45s", "60s", "90s", "120s"),
        label = "Refresh interval:",
        onOptionSelected = {}
    )
}