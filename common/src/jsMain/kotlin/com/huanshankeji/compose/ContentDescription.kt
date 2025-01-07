package com.huanshankeji.compose

import org.jetbrains.compose.web.attributes.AttrsScope

fun AttrsScope<*>.contentDescription(contentDescription: String?) =
    contentDescription?.let {
        // aria-label's semantics don't seem to meet the requirements perfectly because it labels an interactive element
        // see: https://developer.mozilla.org/en-US/docs/Web/Accessibility/ARIA/Attributes/aria-label, https://stackoverflow.com/questions/22039910/what-is-aria-label-and-how-should-i-use-it
        //attr("aria-label", it)

        title(it)
    }
