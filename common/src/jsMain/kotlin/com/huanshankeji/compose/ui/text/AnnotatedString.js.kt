package com.huanshankeji.compose.ui.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.util.fastJoinToString
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import com.huanshankeji.compose.InternalApi

/**
 * JS DOM implementation of AnnotatedString using a nestable tree/node design.
 *
 * Each `withStyle` call creates a child [Node.Styled] with its style, mapping 1:1 to
 * nested HTML `<span>` tags. This avoids the unnecessary complexity of the Compose UI
 * range-based (`spanStyles: List<Range>`) design, which would require converting ranges
 * to segments and back to `<span>` tags. As a trade-off, interleaving span ranges are
 * not supported on JS DOM (they are rarely used in practice).
 *
 * See commit d7d71c38ff888738e06871d1d8662c8556c508b5 for the previous revision that replicated the Compose UI range-based design.
 */
@Immutable
actual class AnnotatedString(val nodes: Nodes) {
    @InternalApi
    value class Nodes(val nodeList: List<Node>) {
        fun text(): String =
            nodeList.fastJoinToString("") { it.text() }

        @Suppress("NOTHING_TO_INLINE")
        inline operator fun plus(other: Nodes): Nodes =
            Nodes(nodeList + other.nodeList)
    }

    @InternalApi
    sealed class Node {
        abstract fun text(): String

        data class Text(val text: String) : Node() {
            override fun text(): String = text
        }

        data class Styled(val style: SpanStyle, val children: Nodes) : Node() {
            override fun text(): String =
                children.text()
        }
    }

    actual val text: String get() = nodes.text()

    override fun toString(): String =
        text

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AnnotatedString) return false
        return nodes == other.nodes
    }

    override fun hashCode(): Int = nodes.hashCode()

    @Stable
    actual operator fun plus(other: AnnotatedString): AnnotatedString =
        AnnotatedString(nodes + other.nodes)
}

/**
 * Builder for AnnotatedString.
 *
 * Uses a tree of [AnnotatedString.Node]s where `withStyle` pushes/pops node levels,
 * mapping 1:1 to nested HTML `<span>` tags in the rendered output.
 */
actual /*value*/ class AnnotatedStringBuilder(val mutableNodeList: MutableList<AnnotatedString.Node> = mutableListOf()) /*: Appendable*/ {
    /** Create an [AnnotatedStringBuilder] instance using the given [String]. */
    actual constructor(text: String) : this(mutableListOf(AnnotatedString.Node.Text(text)))

    /** Create an [AnnotatedStringBuilder] instance using the given [AnnotatedString]. */
    actual constructor(text: AnnotatedString) : this(text.nodes.nodeList.toMutableList())

    //actual val length: Int get() = text.length

    actual fun append(text: String) {
        mutableNodeList.add(AnnotatedString.Node.Text(text))
    }

    actual fun append(char: Char) =
        apply { append(char.toString()) }

    actual fun append(text: AnnotatedString) {
        mutableNodeList.addAll(text.nodes.nodeList)
    }

    fun toNodes() =
        AnnotatedString.Nodes(mutableNodeList.toList())

    actual fun toAnnotatedString(): AnnotatedString =
        AnnotatedString(toNodes())
}


actual fun buildAnnotatedString(builder: AnnotatedStringBuilder.() -> Unit): AnnotatedString =
    AnnotatedStringBuilder().apply(builder).toAnnotatedString()

actual inline fun <R : Any> AnnotatedStringBuilder.withStyle(
    style: SpanStyle,
    block: AnnotatedStringBuilder.() -> R,
): R {
    val childBuilder = AnnotatedStringBuilder()
    val result = childBuilder.block()
    mutableNodeList.add(AnnotatedString.Node.Styled(style, childBuilder.toNodes()))
    return result
}


/**
 * Renders an [AnnotatedString] as composable HTML content using nested `<span>` elements.
 */
@Composable
fun AnnotatedStringText(annotatedString: AnnotatedString) =
    RenderNodes(annotatedString.nodes)

@InternalApi
@Composable
fun RenderNodes(nodes: AnnotatedString.Nodes) {
    for (node in nodes.nodeList)
        when (node) {
            is AnnotatedString.Node.Text -> Text(node.text)
            is AnnotatedString.Node.Styled -> {
                Span({
                    style { applyStyle(node.style) }
                }) {
                    RenderNodes(node.children)
                }
            }
        }
}
