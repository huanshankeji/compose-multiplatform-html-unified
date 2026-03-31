package com.huanshankeji.compose.material.demo

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huanshankeji.compose.foundation.layout.ext.innerPadding
import com.huanshankeji.compose.foundation.layout.ext.outerPadding
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color
import com.huanshankeji.compose.ui.text.AnnotatedString
import com.huanshankeji.compose.ui.text.SpanStyle
import com.huanshankeji.compose.ui.text.buildAnnotatedString
import com.huanshankeji.compose.ui.text.font.FontStyle
import com.huanshankeji.compose.ui.text.font.FontWeight
import com.huanshankeji.compose.ui.text.style.TextDecoration
import com.huanshankeji.compose.ui.text.withStyle

internal enum class Selection {
    A, B, C;

    fun displayText(): String = buildString {
        append(name)
        repeat(7) { append(name.lowercase()) }
    }
}

val listSize = 160.dp

fun Modifier.outerContentPadding() = outerPadding(16.dp)
fun Modifier.innerContentPadding() = innerPadding(16.dp)
val contentPaddingModifier = Modifier.outerContentPadding()

val demoAnnotatedString: AnnotatedString = buildAnnotatedString {
    append("Normal ")
    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
        append("bold")
    }
    append(" ")
    withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
        append("italic")
    }
    append(" ")
    withStyle(SpanStyle(color = Color.Red)) {
        append("red")
    }
    append(" ")
    withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
        append("underlined")
    }
    append(" ")
    withStyle(SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Blue)) {
        append("big blue bold")
    }
}
