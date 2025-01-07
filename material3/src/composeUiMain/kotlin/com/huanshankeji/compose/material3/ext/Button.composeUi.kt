package com.huanshankeji.compose.material3.ext

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.huanshankeji.compose.foundation.layout.size
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier

@Composable
private fun (@Composable (() -> Unit)).toRowScopeContent(
    icon: @Composable ((Modifier) -> Unit)?,
    isTrailingIcon: Boolean
): @Composable RowScope.() -> Unit =
    {
        // see https://m3.material.io/components/buttons/specs#34dda7d9-40df-48ce-a169-1eaffe2e1835 and https://m3.material.io/components/buttons/specs#309d928e-e9ef-41dd-89fc-9bc51f78709c

        @Composable
        fun Spacer() =
            Spacer(PlatformModifier.size(8.dp))

        if (icon === null)
            this@toRowScopeContent()
        else {
            @Composable
            fun icon() =
                icon(Modifier.size(18.dp))

            if (isTrailingIcon) {
                this@toRowScopeContent()
                Spacer()
                icon()
            } else {
                icon()
                Spacer()
                this@toRowScopeContent()
            }
        }
    }


@Composable
actual fun Button(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable ((Modifier) -> Unit)?,
    isTrailingIcon: Boolean,
    content: @Composable () -> Unit
) = androidx.compose.material3.Button(
    onClick, modifier.platformModifier, enabled, content = content.toRowScopeContent(icon, isTrailingIcon)
)

@Composable
actual fun ElevatedButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable ((Modifier) -> Unit)?,
    isTrailingIcon: Boolean,
    content: @Composable () -> Unit
) = androidx.compose.material3.ElevatedButton(
    onClick, modifier.platformModifier, enabled, content = content.toRowScopeContent(icon, isTrailingIcon)
)

@Composable
actual fun FilledTonalButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable ((Modifier) -> Unit)?,
    isTrailingIcon: Boolean,
    content: @Composable () -> Unit
) =
    androidx.compose.material3.FilledTonalButton(
        onClick, modifier.platformModifier, enabled, content = content.toRowScopeContent(icon, isTrailingIcon)
    )

@Composable
actual fun OutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable ((Modifier) -> Unit)?,
    isTrailingIcon: Boolean,
    content: @Composable () -> Unit
) =
    androidx.compose.material3.OutlinedButton(
        onClick, modifier.platformModifier, enabled, content = content.toRowScopeContent(icon, isTrailingIcon)
    )

@Composable
actual fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable ((Modifier) -> Unit)?,
    isTrailingIcon: Boolean,
    content: @Composable () -> Unit
) =
    androidx.compose.material3.TextButton(
        onClick, modifier.platformModifier, enabled, content = content.toRowScopeContent(icon, isTrailingIcon)
    )
