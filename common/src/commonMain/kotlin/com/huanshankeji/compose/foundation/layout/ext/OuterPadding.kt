package com.huanshankeji.compose.foundation.layout.ext

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.huanshankeji.compose.foundation.background
import com.huanshankeji.compose.foundation.layout.*
import com.huanshankeji.compose.ui.Modifier

/**
 * See the KDoc of the overload with one [Dp] parameter for platform differences.
 */
@Stable
expect fun Modifier.outerPadding(start: Dp = 0.dp, top: Dp = 0.dp, end: Dp = 0.dp, bottom: Dp = 0.dp): Modifier

/**
 * See the KDoc of the overload with one [Dp] parameter for platform differences.
 */
@Stable
expect fun Modifier.outerPadding(horizontal: Dp = 0.dp, vertical: Dp = 0.dp): Modifier

/**
 * This delegates to the CSS `margin` property on JS DOM and adds the padding outside.
 * For consistency on different platforms, please use it as the **first** modifier **before** the other modifiers such as [background] and [clickable].
 *
 * However, as [CSS `box-sizing`](https://developer.mozilla.org/en-US/docs/Web/CSS/box-sizing) doesn't have a `margin-box` value, when this is used with [fillMaxSize]/[fillMaxWidth]/[fillMaxHeight] on JS DOM the visual result may be not what you expect.
 * In such a case, either replace it with [fillMaxSizeStretch]/[fillMaxWidthStretch]/[fillMaxHeightStretch] or use [innerPadding] on an outer parent instead.
 *
 * Copy and render the following code to see the problem yourself:
 *
```html
<!DOCTYPE html>
<html>

<head>
<style>
.inner-div {
width: 100%;
height: 64px;
padding: 16px;
margin: 16px;
background: white;
border: 2px solid yellow;
box-sizing: border-box;
}

.outer-div {
background: orange;
border: 2px solid red;
padding: 16px;
}
</style>
</head>

<body>

<h1>box-sizing with margin</h1>
<p>In <code>.inner-div</code>, either remove <code>margin</code> or set <code>width</code> to <code>-webkit-fill-available</code>.

<br>
<div class="outer-div">
<div class="inner-div"></div>
</div>
<div>

</body>

</html>
```
 *
 * See the KDoc of the [padding] overload with one [Dp] parameter for more information.
 * @see innerPadding
 */
@Stable
expect fun Modifier.outerPadding(all: Dp): Modifier

/**
 * See the KDoc of the overload with one [Dp] parameter for platform differences.
 */
@Stable
expect fun Modifier.outerPadding(paddingValues: PaddingValues): Modifier

/**
 * See the KDoc of the overload with one [Dp] parameter for platform differences.
 */
@Stable
expect fun Modifier.absoluteOuterPadding(left: Dp = 0.dp, top: Dp = 0.dp, right: Dp = 0.dp, bottom: Dp = 0.dp): Modifier
