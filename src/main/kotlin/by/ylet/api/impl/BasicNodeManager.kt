package by.ylet.api.impl

import by.ylet.Constants.PATH_PROPERTY_PATH
import by.ylet.core.TypeManager
import by.ylet.core.tools.PathUtils.normalizePath
import by.ylet.stereotype.Node
import org.dizitart.no2.Nitrite
import org.dizitart.no2.filters.Filters.*

internal class BasicNodeManager(db: Nitrite, transformer: TypeManager) : AbstractNodeManager(db, transformer) {

    companion object {
        const val CHILD_NODES_PATH_PATTERN = "^/%s/[a-zA-Z0-9_-]+/?$"
    }

    override fun getNodeByPath(path: String): Node? {
        return getFromCollection {
            it.find(eq(PATH_PROPERTY_PATH, path))
                .firstOrDefault()
                ?.toNode()
        }
    }

    override fun createNode(node: Node) {
        doInCollection {
            val document = node.toDocument()
            it.insert(document)
        }
    }

    override fun updateNode(node: Node) {
        doInCollection {
            val document = node.toDocument()
            it.update(eq(PATH_PROPERTY_PATH, node.path), document)
        }
    }

    override fun getParentNode(node: Node): Node? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getParentNode(path: String): Node? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getChildNodes(node: Node): List<Node> {
        requireNotNull(node.path)
        return getChildNodes(node.path)
    }

    override fun getChildNodes(path: String): List<Node> {
        val normalizedPath = normalizePath(path)
        return getFromCollection {
            val result = mutableListOf<Node>()
            val iterator = it.find(
                regex(PATH_PROPERTY_PATH, String.format(CHILD_NODES_PATH_PATTERN, normalizedPath))
            ).iterator()
            while (iterator.hasNext()) {
                val nextDocument = iterator.next()
                result.add(nextDocument.toNode())
            }
            result
        }
    }
}