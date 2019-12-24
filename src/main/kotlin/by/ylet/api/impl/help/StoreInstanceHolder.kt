package by.ylet.api.impl.help

import org.dizitart.no2.Nitrite

internal object StoreInstanceHolder {
    @Volatile
    private var instance: Nitrite? = null

    fun isInstanceStored(): Boolean = instance != null

    fun keepInstance(instance: Nitrite) {
        synchronized(this) {
            if (StoreInstanceHolder.instance == null) {
                StoreInstanceHolder.instance = instance
            }
        }
    }

    fun getInstance() = instance
}