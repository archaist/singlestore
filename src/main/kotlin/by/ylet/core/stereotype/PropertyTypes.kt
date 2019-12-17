package by.ylet.core.stereotype

enum class PropertyTypes {
    BLOB, STRING, NUMBER;

    override fun toString(): String = this.name.toLowerCase()

}