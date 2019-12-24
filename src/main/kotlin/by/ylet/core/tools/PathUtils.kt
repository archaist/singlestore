package by.ylet.core.tools

import by.ylet.Constants.NODE_PATH_SEPARATOR

internal object PathUtils {
    fun normalizePath(path: String): String = path.trim().removePrefix(NODE_PATH_SEPARATOR)
        .removeSuffix(NODE_PATH_SEPARATOR)
}