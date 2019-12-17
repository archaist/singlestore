package by.ylet.core

import by.ylet.stereotype.Node
import org.dizitart.no2.Document

internal interface TypeManager {
    fun nodeToDocument(node: Node): Document
    fun documentToNode(document: Document): Node
}