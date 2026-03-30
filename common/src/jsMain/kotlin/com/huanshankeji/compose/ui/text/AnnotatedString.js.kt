package com.huanshankeji.compose.ui.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.text.AnnotatedString.Builder
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

/**
 * JS DOM implementation of AnnotatedString.
 *
 * Previous revisions (see commit d7d71c3) used a Compose UI-style range-based design with
 * `spanStyles: List<Range<SpanStyle>>` and `pushStyle`/`addStyle`/`pop` converting ranges to
 * flat `<span>` segments. That design was dropped because `withStyle` calls map naturally to
 * nested `<span>` elements on JS DOM, and the range→segment→span conversion was unnecessarily
 * complex. The current tree/node design directly represents the nested structure of styled text,
 * where each `withStyle` call creates a child node with its style, mapping 1:1 to nested `<span>` tags.
 *
 * This means `pushStyle`/`addStyle` are supported for API compatibility but they use the same
 * internal annotation-based bookkeeping as Compose UI. The `AnnotatedStringText` composable
 * preferentially renders from the tree representation (populated via `buildAnnotatedString`/`withStyle`)
 * but falls back to range-based rendering for `AnnotatedString` instances constructed directly with
 * `spanStyles`.
 */
@Immutable
actual class AnnotatedString actual constructor(
    actual val text: String,
    actual val spanStyles: List<Range<SpanStyle>>,
) {
    /**
     * Internal tree representation populated by [Builder] when using `withStyle`.
     * Each node is either a text leaf or a styled container with children.
     */
    internal var rootNodes: List<Node> = emptyList()
        private set

    internal constructor(text: String, spanStyles: List<Range<SpanStyle>>, rootNodes: List<Node>) : this(
        text, spanStyles
    ) {
        this.rootNodes = rootNodes
    }

    override fun toString(): String =
        text

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AnnotatedString) return false
        if (text != other.text) return false
        if (spanStyles != other.spanStyles) return false
        return true
    }

    override fun hashCode(): Int {
        var result = text.hashCode()
        result = 31 * result + spanStyles.hashCode()
        return result
    }

    @Stable
    actual operator fun plus(other: AnnotatedString): AnnotatedString =
        with(Builder()) {
            append(this@AnnotatedString)
            append(other)
            toAnnotatedString()
        }

    /** Internal node type for tree-based rendering. */
    internal sealed class Node {
        class TextNode(val text: String) : Node()
        class StyledNode(val style: SpanStyle, val children: List<Node>) : Node()
    }

    @Immutable
    actual class Range<T> actual constructor(
        actual val item: T,
        actual val start: Int,
        actual val end: Int,
    )

    /**
     * Builder for AnnotatedString.
     *
     * Internally uses two representations:
     * - A tree of [Node]s populated by `withStyle` for efficient nested `<span>` rendering.
     * - A `styleStack` + `annotations` list (mirroring Compose UI's design) for `pushStyle`/`addStyle`/`pop`
     *   to support range-based `spanStyles` in the resulting [AnnotatedString].
     *
     * When `toAnnotatedString()` is called, both the tree and the range list are included.
     * `AnnotatedStringText` preferentially renders from the tree if available.
     */
    actual class Builder actual constructor(capacity: Int) /*: Appendable*/ {
        private val text = StringBuilder(capacity)
        // Mirrors Compose UI's `styleStack`: open spans (pushed but not yet popped).
        private val styleStack = mutableListOf<MutableRange<SpanStyle>>()
        // Mirrors Compose UI's `annotations`: all completed (closed) spans.
        // `spanStyles` in previous revision replaced this; it corresponds to `annotations` in Compose UI
        // since only SpanStyle annotations are currently supported.
        private val annotations = mutableListOf<MutableRange<SpanStyle>>()
        // Tree-based representation for nested rendering.
        private val nodeStack = mutableListOf<MutableList<Node>>()
        private var currentNodes = mutableListOf<Node>()

        /** Create a [Builder] instance using the given [String]. */
        actual constructor(text: String) : this() {
            append(text)
        }

        /** Create a [Builder] instance using the given [AnnotatedString]. */
        actual constructor(text: AnnotatedString) : this() {
            append(text)
        }

        actual val length: Int get() = text.length

        actual fun append(text: String) {
            this.text.append(text)
            currentNodes.add(Node.TextNode(text))
        }

        actual fun append(char: Char) {
            text.append(char)
            currentNodes.add(Node.TextNode(char.toString()))
        }

        actual fun append(text: AnnotatedString) {
            val start = this.text.length
            this.text.append(text.text)
            // Add range-based styles offset by current position
            text.spanStyles.forEach {
                annotations.add(MutableRange(it.item, it.start + start, it.end + start))
            }
            // Add tree nodes if available, otherwise create a text node
            if (text.rootNodes.isNotEmpty()) {
                currentNodes.addAll(text.rootNodes)
            } else {
                currentNodes.add(Node.TextNode(text.text))
            }
        }

        actual fun pushStyle(style: SpanStyle): Int {
            val index = styleStack.size
            styleStack.add(MutableRange(style, text.length))
            // Push a new node level for tree representation
            nodeStack.add(currentNodes)
            currentNodes = mutableListOf()
            return index
        }

        actual fun pop() {
            check(styleStack.isNotEmpty()) { "Nothing to pop." }
            val entry = styleStack.removeLast()
            // Move completed range to annotations (matching Compose UI: pop sets end and moves to annotations)
            entry.end = text.length
            annotations.add(entry)
            // Pop tree level: wrap children in a StyledNode
            val children = currentNodes
            currentNodes = nodeStack.removeLast()
            currentNodes.add(Node.StyledNode(entry.item, children))
        }

        actual fun pop(index: Int) {
            check(index < styleStack.size) { "$index should be less than ${styleStack.size}" }
            while (styleStack.size > index) {
                pop()
            }
        }

        actual fun addStyle(style: SpanStyle, start: Int, end: Int) {
            annotations.add(MutableRange(style, start, end))
            // Note: addStyle with explicit range doesn't affect the tree representation.
            // It will be included in spanStyles for range-based rendering fallback.
        }

        actual fun toAnnotatedString(): AnnotatedString {
            // Following Compose UI: only `annotations` (completed ranges) are included,
            // not unclosed entries from `styleStack`.
            val spanStyleRanges = annotations.map { Range(it.item, it.start, it.end ?: text.length) }
            return AnnotatedString(text.toString(), spanStyleRanges, currentNodes.toList())
        }

        private class MutableRange<T>(val item: T, val start: Int, var end: Int? = null)
    }
}

actual fun buildAnnotatedString(builder: Builder.() -> Unit): AnnotatedString =
    Builder().apply(builder).toAnnotatedString()


/**
 * Renders an [AnnotatedString] as composable HTML content.
 *
 * Preferentially uses the tree-based representation (populated via `buildAnnotatedString`/`withStyle`)
 * for efficient nested `<span>` rendering. Falls back to range-based rendering for `AnnotatedString`
 * instances constructed directly with `spanStyles`.
 */
@Composable
fun AnnotatedStringText(annotatedString: AnnotatedString) {
    if (annotatedString.rootNodes.isNotEmpty()) {
        // Tree-based rendering: each withStyle call maps to a nested <span>
        RenderNodes(annotatedString.rootNodes)
    } else if (annotatedString.spanStyles.isEmpty()) {
        Text(annotatedString.text)
    } else {
        // Fallback: range-based rendering for AnnotatedString constructed with explicit spanStyles
        RenderRanges(annotatedString)
    }
}

@Composable
private fun RenderNodes(nodes: List<AnnotatedString.Node>) {
    for (node in nodes) {
        when (node) {
            is AnnotatedString.Node.TextNode -> Text(node.text)
            is AnnotatedString.Node.StyledNode -> {
                Span({
                    style { applyStyle(node.style) }
                }) {
                    RenderNodes(node.children)
                }
            }
        }
    }
}

/** Fallback range-based rendering for AnnotatedString constructed with explicit spanStyles. */
@Composable
private fun RenderRanges(annotatedString: AnnotatedString) {
    val text = annotatedString.text
    val breakpoints = mutableSetOf(0, text.length)
    for (range in annotatedString.spanStyles) {
        breakpoints.add(range.start)
        breakpoints.add(range.end)
    }
    val breakpointList = breakpoints.sorted()

    val ranges = annotatedString.spanStyles
    val rangesByStart = ranges.groupBy { it.start }
    val rangesByEnd = ranges.groupBy { it.end }
    val activeStyles = mutableListOf<AnnotatedString.Range<SpanStyle>>()

    for (i in 0 until breakpointList.size - 1) {
        val start = breakpointList[i]
        val end = breakpointList[i + 1]
        if (start >= end) continue

        rangesByEnd[start]?.let { toRemove -> activeStyles.removeAll(toRemove) }
        rangesByStart[start]?.let { toAdd -> activeStyles.addAll(toAdd) }

        val segment = text.substring(start, end)

        if (activeStyles.isEmpty()) {
            Text(segment)
        } else {
            Span({
                style {
                    for (range in activeStyles.sortedWith(compareBy<AnnotatedString.Range<SpanStyle>> { it.start }.thenByDescending { it.end })) {
                        applyStyle(range.item)
                    }
                }
            }) {
                Text(segment)
            }
        }
    }
}
