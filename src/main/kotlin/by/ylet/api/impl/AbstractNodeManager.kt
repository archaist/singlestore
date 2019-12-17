package by.ylet.api.impl

import by.ylet.Constants
import by.ylet.api.NodeManager
import by.ylet.core.TypeManager
import by.ylet.stereotype.Node
import org.dizitart.no2.Document
import org.dizitart.no2.Nitrite
import org.dizitart.no2.NitriteCollection

internal abstract class AbstractNodeManager(private val db: Nitrite, private val transformer: TypeManager) : NodeManager {
    protected fun Document.toNode() = transformer.documentToNode(this)

    protected fun Node.toDocument() = transformer.nodeToDocument(this)

    protected fun <R> getFromCollection(action: (collection: NitriteCollection) -> R): R =
        db.getCollection(Constants.COLLECTION_NAME).use { return action(it) }

    protected fun doInCollection(action: (collection: NitriteCollection) -> Unit) =
        db.getCollection(Constants.COLLECTION_NAME).use { action(it) }
}