package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/**
 * Material Design dialog.
 *
 * @see <a href="https://m3.material.io/components/dialogs/overview">Material Design dialogs</a>
 * @see androidx.compose.material3.AlertDialog
 */
@Composable
expect fun AlertDialog(
    isOpen: Boolean, // `open` from the web component, but named `isOpen` to follow Kotlin conventions
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    dismissButton: @Composable (() -> Unit)? = null,
    icon: @Composable ((Modifier) -> Unit)? = null,
    title: @Composable ((Modifier) -> Unit)? = null,
    text: @Composable ((Modifier) -> Unit)? = null,
)

/**
 * A simple dialog without the conventional components such as title, text, and buttons, showing just a [Card] overlay.
 * The corresponding Compose UI `Dialog` component is actually not in the `material3` package but in `androidx.compose.ui.window`.
 * Currently not completely visually consistent on both kinds of platforms. On Compose UI, it doesn't have a min width; while on JS DOM, it does.
 */
@Composable
expect fun SimpleDialog(
    isOpen: Boolean, // `open` from the web component, but named `isOpen` to follow Kotlin conventions
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
)
