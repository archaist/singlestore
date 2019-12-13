package by.ylet.api.impl.help

import by.ylet.stereotype.Node
import org.dizitart.no2.Document

//TODO: implement converter Document to Node
internal fun Document.toNode() = Node(null, null, null, null, null)
//TODO: implement converter Node to Document
internal fun Node.toDocument() = Document()