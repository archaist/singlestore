package by.ylet.stereotype

data class Node(
    val path: String?,
    val name: String?,
    val type: String?,
    val properties: Map<String, Any>?,
    val childNodes: Iterable<Node>?,
    val parent: Node?
)