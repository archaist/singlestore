package by.ylet.api

import by.ylet.stereotype.Node


interface NodeManager {
    fun getNodeByPath(path: String): Node?
    fun createNode(node: Node)
    fun updateNode(node: Node)
    fun getParentNode(node: Node): Node?
    fun getParentNode(path: String): Node?
    fun getChildNodes(node: Node): List<Node>
    fun getChildNodes(path: String): List<Node>
}