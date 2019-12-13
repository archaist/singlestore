package by.ylet.api.impl.help

import org.dizitart.no2.Nitrite

internal object StoreInstanceHolderHolder {
    @Volatile
    private var instance: Nitrite? = null

    fun isInstanceStored(): Boolean = instance != null

    fun keepInstance(instance: Nitrite) {
        synchronized(this) {
            if (StoreInstanceHolderHolder.instance == null) {
                StoreInstanceHolderHolder.instance = instance
            }
        }
    }

    fun getInstance() = instance
}