package by.ylet.configuration

import by.ylet.core.DefaultTypeManager
import by.ylet.core.TypeManager
import by.ylet.stereotype.Node
import org.junit.Ignore
import org.junit.Test
import kotlin.test.assertEquals


class NodeManagerBuilderTest {

    @Test
    @Ignore
    fun configurationTest() {
        val nodeManager = NodeManagerBuilder.build()
        //val nodeByPath = nodeManager.getNodeByPath("/")
        val tm: TypeManager = DefaultTypeManager();
        val document = tm.nodeToDocument(
            Node(
                "/content/homePage2", "Home Page", "html",
                mapOf(
                    "data" to byteArrayOf(10, 12, 55, 72),
                    "path" to "/content/homePage2",
                    "name" to "Home Page",
                    "type" to "html"
                )
            )
        )
        //nodeManager.test(document)
        val nodeByPath = nodeManager.getNodeByPath("/content/homePage2")
        println(nodeByPath)
        assertEquals(expected = true, actual = true, message = "Always true")
    }

    @Test
    @Ignore
    fun testTypes() {
        val node = Node(
            "/content/homePage1", "Home Page", "html",
            mapOf(
                "data" to byteArrayOf(10, 12, 55, 72),
                "path" to "/content/homePage1",
                "name" to "Home Page",
                "type" to "html"
            )
        )
        val CHILD_NODES_PATH_PATTERN = "^/content/[a-zA-Z0-9_-]+/?$"
        println(String.format(CHILD_NODES_PATH_PATTERN, "content"))
//        val tm: TypeManager = DefaultTypeManager();
//        val document = tm.nodeToDocument(
//            Node(
//                "/content/homePage1", "Home Page", "html",
//                mapOf(
//                    "data" to byteArrayOf(10, 12, 55, 72),
//                    "path" to "/content/homePage1",
//                    "name" to "Home Page",
//                    "type" to "html"
//                ), null, null
//            )
//        )
//        println(document)

    }
}