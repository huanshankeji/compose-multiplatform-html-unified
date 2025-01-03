package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import dev.petuska.kmdc.core.MDCAttrs
import dev.petuska.kmdc.core.MDCContent
import dev.petuska.kmdc.snackbar.*

private fun Boolean.actionOnNewLineToType() =
    if (this) MDCSnackbarType.Stacked else MDCSnackbarType.Default

// see https://github.com/mpetuska/kmdc/blob/master/kmdc/kmdc-snackbar/src/jsMain/kotlin/Actions.kt
@Composable
internal fun CommonSnackbar(
    actionOnNewLine: Boolean,
    open: Boolean,
    timeoutMs: Int?,
    modifier: Modifier,
    attrs: MDCAttrs<MDCSnackbarAttrsScope>?,
    mdcSnackbarContent: MDCContent<MDCSnackbarScope>?,
    actions: MDCContent<MDCSnackbarActionsScope>?,
) =
    MDCSnackbar(actionOnNewLine.actionOnNewLineToType(), open, timeoutMs, attrs = modifier.toAttrs(attrs)) {
        mdcSnackbarContent?.invoke(this)

        actions?.let {
            Actions {
                it()
            }
        }
    }

@Composable
actual fun Snackbar(
    modifier: Modifier,
    action: @Composable (() -> Unit)?,
    actionOnNewLine: Boolean,
    content: @Composable () -> Unit
) =
    CommonSnackbar(actionOnNewLine, true, null, modifier, null, {
        Label { content() }
    }, {
        action?.let { Action { it() } }
    })

/**
 * @param snackbarData [SnackbarDataCommonInterface.duration] is not used here. Use [timeoutMs] instead.
 */
@Composable
internal fun CommonSnackbar(
    actionOnNewLine: Boolean,
    open: Boolean,
    timeoutMs: Int?,
    modifier: Modifier,
    snackbarData: SnackbarDataCommonInterface?
) =
    CommonSnackbar(actionOnNewLine, open, timeoutMs, modifier, {
        snackbarData?.let { snackbarData -> onClosed { snackbarData.dismiss() } } // This event is not fired when the snackbar UI is not opened.
    }, {
        Label(snackbarData?.message ?: "") // to reduce DOM recomposition in `SnackbarHost`
    }, {
        snackbarData?.actionLabel?.let {
            Action(it, attrs = {
                onClick { snackbarData.performAction() }
            })
        }
    })

/**
 * @param snackbarData [SnackbarDataCommonInterface.duration] is not used here.
 */
@Composable
actual fun Snackbar(
    snackbarData: SnackbarData,
    modifier: Modifier,
    actionOnNewLine: Boolean
) =
    CommonSnackbar(actionOnNewLine, true, null, modifier, snackbarData.platformValue)
