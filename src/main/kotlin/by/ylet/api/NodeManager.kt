package by.ylet.api

import by.ylet.stereotype.Node


interface NodeManager {
    fun getNodeByPath(path: String): Node?
}