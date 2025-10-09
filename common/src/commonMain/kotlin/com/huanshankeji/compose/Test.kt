package com.huanshankeji.compose

import kotlin.jvm.JvmInline

@JvmInline
value class Impl(val any: Any)

fun test() {
    mutableListOf<Int>()
}
