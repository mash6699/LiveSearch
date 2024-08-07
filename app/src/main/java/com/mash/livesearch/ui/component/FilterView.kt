package com.mash.livesearch.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mash.livesearch.R
import com.mash.livesearch.domain.model.Filter

@Composable
fun FilterList( sortOptions: List<Filter>, onSelectionChange: (String?) -> Unit) {
    if (!sortOptions.isNullOrEmpty()) {
        var selectedFilter by remember { mutableStateOf<String?>(null) }
        Column(modifier = Modifier.padding(15.dp)) {
            Text (text = stringResource(id = R.string.filters))
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items = sortOptions) {
                    FilterItem(
                        filter = it,
                        isSelected = it.label == selectedFilter,
                        onSelectionChange = { },
                        onFilterSelected = {
                            selectedFilter = it.label
                            onSelectionChange(selectedFilter.orEmpty())
                                           },
                        onDeselect = {
                            selectedFilter = null
                            onSelectionChange(null)
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun FilterItem(
    modifier: Modifier = Modifier,
    filter: Filter,
    isSelected: Boolean = false,
    onSelectionChange: (Filter) -> Unit,
    onFilterSelected: () -> Unit,
    onDeselect:() -> Unit
) {
    //var isSelected by rememberSaveable { mutableStateOf(false) }
    Surface(
        modifier = Modifier,
        shadowElevation = if (isSelected) 0.dp else 2.dp,
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) Color.Gray else Color.White,
        border = BorderStroke(0.3.dp, if (isSelected) Color.Black else Color.Gray)
    ) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = isSelected,
                    onValueChange = {
                       // onSelectionChange(filter)
                       // isSelected =! isSelected
                        onFilterSelected()
                    }
                )
                .padding(horizontal = 10.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = filter.label,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold
            )
            if (isSelected) {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    modifier = Modifier.size(16.dp).clickable { onDeselect() /*isSelected = false*/ },
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
    }
}