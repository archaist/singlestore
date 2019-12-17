package by.ylet.api.impl

import by.ylet.Constants.PATH_PROPERTY_PATH
import by.ylet.core.TypeManager
import by.ylet.stereotype.Node
import org.dizitart.no2.Nitrite
import org.dizitart.no2.filters.Filters.eq

internal class BasicNodeManager(db: Nitrite, transformer: TypeManager) : AbstractNodeManager(db, transformer) {

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

    override fun saveNode(node: Node) {
        doInCollection {
            val document = node.toDocument()
            it.update(eq(PATH_PROPERTY_PATH, node.path), document)
        }
    }

}