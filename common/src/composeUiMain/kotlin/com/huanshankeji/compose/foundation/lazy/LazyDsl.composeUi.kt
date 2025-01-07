package com.huanshankeji.compose.foundation.lazy

import androidx.compose.foundation.lazy.LazyScopeMarker
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.huanshankeji.compose.foundation.layout.Arrangement
import com.huanshankeji.compose.foundation.layout.PaddingValues
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier
import androidx.compose.foundation.layout.PaddingValues as PlatformPaddingValues

@LazyScopeMarker
//@JvmDefaultWithCompatibility
actual class LazyListScope(val platformValue: androidx.compose.foundation.lazy.LazyListScope) {
    actual fun item(
        key: Any?,
        contentType: Any?,
        content: @Composable LazyItemScope.() -> Unit
    ) =
        platformValue.item(key, contentType) { LazyItemScope(this).content() }


    actual fun items(
        count: Int,
        key: ((index: Int) -> Any)?,
        contentType: (index: Int) -> Any?,
        itemContent: @Composable LazyItemScope.(index: Int) -> Unit
    ) =
        platformValue.items(count, key, contentType) { index -> LazyItemScope(this).itemContent(index) }
}

@Composable
actual fun LazyRow(
    modifier: Modifier,
    contentPadding: PaddingValues?,
    reverseLayout: Boolean,
    horizontalArrangement: Arrangement.Horizontal,
    verticalAlignment: Alignment.Vertical,
    content: LazyListScope.() -> Unit
) =
    androidx.compose.foundation.lazy.LazyRow(
        modifier.platformModifier,
        contentPadding = contentPadding?.platformValue ?: PlatformPaddingValues(0.dp),
        reverseLayout = reverseLayout,
        horizontalArrangement = horizontalArrangement.platformValue,
        verticalAlignment = verticalAlignment.platformHorizontal
    ) { LazyListScope(this).content() }

@Composable
actual fun LazyColumn(
    modifier: Modifier,
    contentPadding: PaddingValues?,
    reverseLayout: Boolean,
    verticalArrangement: Arrangement.Vertical,
    horizontalAlignment: Alignment.Horizontal,
    content: LazyListScope.() -> Unit
) =
    androidx.compose.foundation.lazy.LazyColumn(
        modifier.platformModifier,
        contentPadding = contentPadding?.platformValue ?: PlatformPaddingValues(0.dp),
        reverseLayout = reverseLayout,
        verticalArrangement = verticalArrangement.platformValue,
        horizontalAlignment = horizontalAlignment.platformHorizontal
    ) { LazyListScope(this).content() }
