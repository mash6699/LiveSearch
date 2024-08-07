package com.mash.livesearch.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import com.mash.livesearch.R

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .semantics { testTag = "circularLoader" },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun ErrorView(modifier: Modifier = Modifier, text: String) {
    BaseImageView(
        modifier = modifier,
        painterRes = painterResource(id = R.drawable.ic_error),
        stringRes = text,
        tag = "errorView"
    )
}

@Composable
fun WelcomeView(modifier: Modifier = Modifier) {
    BaseImageView(
        modifier = modifier,
        painterRes = painterResource(id = R.drawable.ic_cart),
        stringRes = stringResource(id = R.string.enter_term),
        tag = "welcomeView"
    )
}

@Composable
fun EmptyView(modifier: Modifier = Modifier) {
    BaseImageView(
        modifier = modifier,
        painterRes = painterResource(id = R.drawable.ic_not_found),
        stringRes = stringResource(id = R.string.not_found),
        tag = "emptyView"
    )
}

@Composable
fun BaseImageView(
    modifier: Modifier = Modifier,
    painterRes: Painter,
    stringRes: String,
    tag: String
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .semantics { testTag = tag }
    ) {
        OutlinedCard {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    modifier = modifier.size(250.dp),
                    painter = painterRes,
                    contentDescription = "Image $tag",
                    contentScale = ContentScale.Crop,
                )
                Text(text = stringRes,  style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}