package com.sthomas.artsee.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.sthomas.artsee.R

@Composable
fun VerticalSpacer(multiplier: Int = 1) {
    Spacer(Modifier.height(dimensionResource(id = R.dimen.padding) * multiplier))
}

@Composable
fun LargeVerticalSpacer() {
    VerticalSpacer(2)
}

@Composable
fun ExtraLargeVerticalSpacer() {
    VerticalSpacer(4)
}