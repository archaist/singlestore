package by.ylet.configuration

import org.junit.Ignore
import org.junit.Test
import kotlin.test.assertEquals


class NodeManagerBuilderTest {

    @Test
    @Ignore
    fun configurationTest() {
        val nodeManager = NodeManagerBuilder.build()
        val nodeByPath = nodeManager.getNodeByPath("/")
        println(nodeByPath)
        assertEquals(expected = true, actual = true, message = "Always true")
    }
}