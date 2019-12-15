package by.ylet.api.impl

import by.ylet.api.NodeManager
import by.ylet.Constants.COLLECTION_NAME
import by.ylet.Constants.PATH_PROPERTY_NAME
import by.ylet.api.impl.help.toNode
import by.ylet.stereotype.Node
import org.dizitart.no2.Nitrite
import org.dizitart.no2.filters.Filters.eq

internal class BasicNodeManager(private val db: Nitrite) : NodeManager {

    override fun getNodeByPath(path: String): Node? {
        db.getCollection(COLLECTION_NAME).use {
            return it.find(eq(PATH_PROPERTY_NAME, path))
                .firstOrDefault()
                ?.toNode()
        }
    }


}