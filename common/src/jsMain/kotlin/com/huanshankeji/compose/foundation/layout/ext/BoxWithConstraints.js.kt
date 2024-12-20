package com.huanshankeji.compose.foundation.layout.ext

import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.huanshankeji.compose.foundation.ExperimentalFoundationApi
import com.huanshankeji.compose.foundation.layout.Box
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier
import com.varabyte.kobweb.browser.dom.observers.ResizeObserver
import com.varabyte.kobweb.compose.foundation.layout.BoxScope
import com.varabyte.kobweb.compose.ui.attrsModifier

@ExperimentalFoundationApi
@Composable
actual fun BoxWithConstraints(
    modifier: Modifier,
    contentAlignment: Alignment,
    content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    // `DpClientSize.unspecified` instead of null can be used by default to prevent the content from not rendering when `clientSize` is not set
    var clientSize by remember { mutableStateOf<ClientSize?>(null) }
    // `DivBox` doesn't work here either, so it should not be Kobweb's problem.
    Box(
        Modifier.fillMaxSizeStretch()
            .platformModify {
                attrsModifier {
                    ref {
                        //console.log("Initial client size: ${it.clientWidth}, ${it.clientHeight}")
                        //clientSize = ClientSize(it.clientWidth, it.clientHeight)
                        val resizeObserver = ResizeObserver { entries, _ ->
                            val element = entries.single().target

                            /*
                            console.log("width: ${element.clientWidth}, height: ${element.clientHeight}")
                            console.log(entries.first().contentBoxSize.first())
                            console.log(entries.first().borderBoxSize.first())
                            console.log(entries.first().devicePixelContentBoxSize.first()) // If there is zoom this one is different from the 2 above.
                            */

                            with(element) {
                                //console.log("width: $clientWidth, height: $clientHeight")
                                clientSize = ClientSize(clientWidth, clientHeight)
                            }
                        }
                        resizeObserver.observe(it)

                        onDispose {
                            //resizeObserver.unobserve(it)
                            resizeObserver.disconnect()
                            clientSize = null
                        }
                    }
                }
            }
            .then(modifier),
        contentAlignment
    ) {
        clientSize?.let {
            // TODO extra conversions might be needed in some cases when converting to `dp`
            BoxWithConstraintsScopeImpl(platformBoxScope, it.clientWidth.dp, it.clientHeight.dp).content()
        }
    }
}

@Stable
class BoxWithConstraintsScopeImpl(
    override val platformBoxScope: BoxScope,
    override val maxWidth: Dp,
    override val maxHeight: Dp
) : BoxWithConstraintsScope

private class ClientSize(val clientWidth: Int, val clientHeight: Int)

// removed if not used
/**
 * Made in [Dp] so [Dp.Unspecified] can be used.
 */
private class DpClientSize(val clientWidth: Dp, val clientHeight: Dp) {
    companion object {
        val unspecified = DpClientSize(Dp.Unspecified, Dp.Unspecified)
    }
}
