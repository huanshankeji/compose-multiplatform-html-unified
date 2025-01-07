package com.huanshankeji.compose.foundation.ext

import com.huanshankeji.compose.ui.Modifier

/*
const val MATCH_POSITION_RELATIVE_PARENT_DEPRECATED_MESSAGE =
    "This function's functionality is replaced by `imitateComposeUiLayout`."
*/


//@Deprecated(MATCH_POSITION_RELATIVE_PARENT_DEPRECATED_MESSAGE)
expect fun Modifier.matchPositionRelativeParentJsDom(): Modifier
