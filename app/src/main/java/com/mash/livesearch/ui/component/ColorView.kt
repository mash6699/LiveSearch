package com.mash.livesearch.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.mash.livesearch.domain.model.VariantColor


@Composable
fun ColorList(
    variantsColors: List<VariantColor>
) {
    //if (variantsColors?.isNotEmpty() == true) {
        LazyRow(
            //contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp),
            //horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(items = variantsColors) { color ->
                color.colorHex.takeIf { !it.isNullOrBlank() }?.let {
                    ColorItem(colorHex = it)
                }

            }
        }
    //}
}

@Composable
fun ColorItem(
    modifier: Modifier = Modifier,
    colorHex: String
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .size(15.dp),
        shadowElevation = 2.dp,
        shape = CircleShape,
        color = Color(colorHex.toColorInt()),
        border = BorderStroke(0.4.dp, Color.Gray)
    ) { }
}