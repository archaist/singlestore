package by.ylet.configuration

import by.ylet.api.NodeManager
import by.ylet.api.impl.BasicNodeManager
import by.ylet.Constants.COLLECTION_NAME
import by.ylet.Constants.PATH_PROPERTY_PATH
import by.ylet.Constants.TYPE_PROPERTY_PATH
import by.ylet.api.impl.help.StoreInstanceHolderHolder
import by.ylet.configuration.default.DefaultStoreConfiguration.STORE_FILE_PATH
import by.ylet.configuration.default.DefaultStoreConfiguration.STORE_PASSWORD
import by.ylet.configuration.default.DefaultStoreConfiguration.STORE_USER_NAME
import by.ylet.core.DefaultTypeManager
import by.ylet.error.RepositoryException
import by.ylet.tool.OperationUtils.safeOperation
import org.dizitart.no2.IndexOptions
import org.dizitart.no2.IndexType
import org.dizitart.no2.Nitrite
import org.dizitart.no2.NitriteCollection

object NodeManagerBuilder {
    private val INDEX_CONFIG = mapOf(
        PATH_PROPERTY_PATH to IndexType.Unique,
        TYPE_PROPERTY_PATH to IndexType.NonUnique
    )

    private var path: String = STORE_FILE_PATH
    private var userName: String = STORE_USER_NAME
    private var password: String = STORE_PASSWORD


    fun storeFilePath(path: String): NodeManagerBuilder {
        this.path = path
        return this
    }

    fun credentials(userName: String, password: String): NodeManagerBuilder {
        this.userName = userName
        this.password = password
        return this
    }

    fun build(): NodeManager {
        return safeOperation("Could not create storage") {
            synchronized(this) {
                checkForExist()
                val db = createDatabase()
                initDatabase(db)
                StoreInstanceHolderHolder.keepInstance(db)
                BasicNodeManager(db, DefaultTypeManager())
            }
        }
    }

    private fun createDatabase(): Nitrite {
        return Nitrite.builder()
            .filePath(path)
            .openOrCreate(userName, password)
    }

    private fun initDatabase(db: Nitrite) {
        db.getCollection(COLLECTION_NAME).use { initIndex(it) }
    }

    private fun initIndex(collection: NitriteCollection) {
        INDEX_CONFIG.forEach { (index, indexType) ->
            if (!collection.hasIndex(index)) {
                collection.createIndex(index, IndexOptions.indexOptions(indexType, false))
            }
        }
    }

    private fun checkForExist() {
        if (StoreInstanceHolderHolder.isInstanceStored()) {
            throw RepositoryException("Instance already created")
        }
    }
}