package by.ylet.configuration

import by.ylet.api.NodeManager
import by.ylet.api.impl.BasicNodeManager
import by.ylet.Constants.COLLECTION_NAME
import by.ylet.Constants.PATH_PROPERTY_NAME
import by.ylet.api.impl.help.StoreInstanceHolderHolder
import by.ylet.configuration.default.DefaultStoreConfiguration.STORE_FILE_PATH
import by.ylet.configuration.default.DefaultStoreConfiguration.STORE_PASSWORD
import by.ylet.configuration.default.DefaultStoreConfiguration.STORE_USER_NAME
import by.ylet.error.RepositoryException
import org.dizitart.no2.IndexOptions
import org.dizitart.no2.IndexType
import org.dizitart.no2.Nitrite

object NodeManagerBuilder {
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
        return synchronized(this) {
            checkForExist()
            val db = createDatabase()
            initDatabase(db)
            StoreInstanceHolderHolder.keepInstance(db)
            BasicNodeManager(db)
        }
    }

    private fun createDatabase(): Nitrite {
        return Nitrite.builder()
            .filePath(path)
            .openOrCreate(userName, password)
    }

    private fun initDatabase(db: Nitrite) {
        db.getCollection(COLLECTION_NAME).use {
            if (it.hasIndex(PATH_PROPERTY_NAME)) {
                it.createIndex(PATH_PROPERTY_NAME, IndexOptions.indexOptions(IndexType.Unique, false))
            }
        }
    }

    private fun checkForExist() {
        if (StoreInstanceHolderHolder.isInstanceStored()) {
            throw RepositoryException("Instance already created")
        }
    }
}