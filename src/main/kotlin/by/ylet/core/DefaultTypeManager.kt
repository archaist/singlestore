package by.ylet.core

import by.ylet.Constants.NAME_PROPERTY_NAME
import by.ylet.Constants.PATH_PROPERTY_NAME
import by.ylet.Constants.TYPE_PROPERTY_NAME
import by.ylet.Constants.VALUE_PROPERTY_NAME
import by.ylet.core.stereotype.PropertyTypes
import by.ylet.error.RepositoryException
import by.ylet.stereotype.Node
import org.dizitart.no2.Document
import org.dizitart.no2.Document.createDocument


internal class DefaultTypeManager : TypeManager {

    override fun nodeToDocument(node: Node): Document {
        val document = Document()
        node.properties?.forEach { name, value ->
            val type = resolveType(value, name)
            document[name] = createDocument(TYPE_PROPERTY_NAME, type.toString()).put(VALUE_PROPERTY_NAME, value)
        }
        return document
    }

    override fun documentToNode(document: Document): Node {
        val properties = mutableMapOf<String, Any>()
        var path: String? = null
        var type: String? = null
        var name: String? = null
        document.forEach { propName, value ->
            if (value is Document) {
                val propertyValue = value[VALUE_PROPERTY_NAME]
                properties[propName] = propertyValue
                when (propName) {
                    PATH_PROPERTY_NAME -> path = propertyValue as String
                    TYPE_PROPERTY_NAME -> type = propertyValue as String
                    NAME_PROPERTY_NAME -> name = propertyValue as String
                }
            }
        }
        return Node(path, name, type, properties, null, null)
    }

    private fun resolveType(value: Any, name: String): PropertyTypes {
        return when (value) {
            is Number -> PropertyTypes.NUMBER
            is String -> PropertyTypes.STRING
            is ByteArray -> PropertyTypes.BLOB
            else -> throw RepositoryException("Cannot resolve property type $name")
        }
    }
}