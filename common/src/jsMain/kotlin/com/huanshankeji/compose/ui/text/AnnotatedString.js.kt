package com.huanshankeji.compose.ui.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.text.AnnotatedString.Builder
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

/**
 * JS DOM implementation of AnnotatedString using a nestable tree/node design.
 *
 * Each `withStyle` call creates a child [Node.StyledNode] with its style, mapping 1:1 to
 * nested HTML `<span>` tags. This avoids the unnecessary complexity of the Compose UI
 * range-based (`spanStyles: List<Range>`) design, which would require converting ranges
 * to segments and back to `<span>` tags. As a trade-off, interleaving span ranges are
 * not supported on JS DOM (they are rarely used in practice).
 *
 * See commit c368592 for the previous revision that replicated the Compose UI range-based design.
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

    internal constructor(text: String, rootNodes: List<Node>) : this(text, emptyList<Range<SpanStyle>>()) {
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
     * Uses a tree of [Node]s where `withStyle` pushes/pops node levels,
     * mapping 1:1 to nested HTML `<span>` tags in the rendered output.
     */
    actual class Builder actual constructor(capacity: Int) /*: Appendable*/ {
        private val text = StringBuilder(capacity)
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
            this.text.append(text.text)
            if (text.rootNodes.isNotEmpty()) {
                currentNodes.addAll(text.rootNodes)
            } else {
                currentNodes.add(Node.TextNode(text.text))
            }
        }

        @PublishedApi
        internal fun pushStyle(style: SpanStyle) {
            nodeStack.add(currentNodes)
            currentNodes = mutableListOf()
        }

        @PublishedApi
        internal fun popStyle(style: SpanStyle) {
            val children = currentNodes
            currentNodes = nodeStack.removeLast()
            currentNodes.add(Node.StyledNode(style, children))
        }

        actual fun toAnnotatedString(): AnnotatedString =
            AnnotatedString(text.toString(), currentNodes.toList())
    }
}

actual fun buildAnnotatedString(builder: Builder.() -> Unit): AnnotatedString =
    Builder().apply(builder).toAnnotatedString()

actual inline fun <R : Any> AnnotatedString.Builder.withStyle(
    style: SpanStyle,
    block: AnnotatedString.Builder.() -> R,
): R {
    pushStyle(style)
    return try {
        block(this)
    } finally {
        popStyle(style)
    }
}


/**
 * Renders an [AnnotatedString] as composable HTML content using nested `<span>` elements.
 */
@Composable
fun AnnotatedStringText(annotatedString: AnnotatedString) {
    if (annotatedString.rootNodes.isNotEmpty()) {
        RenderNodes(annotatedString.rootNodes)
    } else {
        Text(annotatedString.text)
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
