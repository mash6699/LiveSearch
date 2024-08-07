package com.mash.livesearch.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mash.livesearch.R
import com.mash.livesearch.domain.model.ProductRecord
import com.mash.livesearch.domain.model.VariantColor



@Composable
fun ProductList(records: List<ProductRecord>) {
    LazyColumn {
        items(records) { record ->
            ProductItem(
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
                record = record
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    record: ProductRecord
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .padding(8.dp)
                //.sizeIn(minHeight = 100.dp)
        ) {
            Surface(
                modifier = Modifier
                    .size(82.dp)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp)),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    GlideImage(
                        model = record.lgImage ?: R.drawable.ic_information,
                        contentDescription = "product image",
                        modifier =
                        Modifier
                            .fillMaxSize()
                            .height(90.dp),
                        contentScale = ContentScale.Crop,
                    )
                }

            }
            Spacer(Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f).fillMaxWidth()) {
                Text(
                    text = record.productDisplayName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = stringResource(id = R.string.price_format, record.listPrice),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Gray,
                        textDecoration = TextDecoration.LineThrough
                    )
                )
                Text(
                    text = stringResource(id = R.string.price_format, record.promoPrice),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                )
                record.variantsColor?.let { ColorList(it) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecordPreview() {
    val colorList = listOf(
        VariantColor("Negro", "#000000"),
        VariantColor("Multicolor", "#E67E22"),
        VariantColor("Plateado", "#BBBBBB")
    )
    Box(modifier = Modifier.height(120.dp)) {
        ProductItem(
            modifier = Modifier.wrapContentHeight(),
            record = ProductRecord(
                productDisplayName = "Gorra",
                productRatingCount = 27,
                listPrice = 449.0,
                promoPrice = 359.2,
                variantsColor = colorList,
                lgImage = null,
            )
        )
    }
}