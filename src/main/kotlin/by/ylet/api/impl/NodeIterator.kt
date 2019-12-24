package by.ylet.api.impl

import by.ylet.core.TypeManager
import by.ylet.stereotype.Node
import org.dizitart.no2.Document


class NodeIterator : Iterator<Node> {

    private val itr: Iterator<Document>
    private val typeManager: TypeManager

    internal constructor(itr: Iterator<Document>, typeManager: TypeManager) {
        this.itr = itr
        this.typeManager = typeManager
    }

    override fun hasNext(): Boolean {
        return itr.hasNext()
    }

    override fun next(): Node {
        val next = itr.next()
        return typeManager.documentToNode(next)
    }
}