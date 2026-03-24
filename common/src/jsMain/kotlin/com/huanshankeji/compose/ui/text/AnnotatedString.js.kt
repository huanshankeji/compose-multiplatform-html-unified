package com.huanshankeji.compose.ui.text

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun AnnotatedStringText(annotatedString: AnnotatedString) {
    if (annotatedString.spanStyles.isEmpty()) {
        Text(annotatedString.text)
    } else {
        val text = annotatedString.text
        // Collect all breakpoints
        val breakpoints = mutableSetOf(0, text.length)
        for (range in annotatedString.spanStyles) {
            breakpoints.add(range.start)
            breakpoints.add(range.end)
        }
        val breakpointList = breakpoints.sorted()
        for (i in 0 until breakpointList.size - 1) {
            val start = breakpointList[i]
            val end = breakpointList[i + 1]
            if (start >= end) continue

            val segment = text.substring(start, end)
            // Since segments are split at style range boundaries, checking full containment is correct here.
            val applicableStyles = annotatedString.spanStyles.filter { it.start <= start && it.end >= end }

            if (applicableStyles.isEmpty()) {
                Text(segment)
            } else {
                Span({
                    style {
                        for (range in applicableStyles) {
                            applyStyle(range.item)
                        }
                    }
                }) {
                    Text(segment)
                }
            }
        }
    }
}
