package by.ylet.tool

import by.ylet.error.RepositoryException

internal object OperationUtils {
    internal inline fun <R> safeOperation(errorMessage: String, callback: () -> R): R {
        try {
            return callback()
        } catch (e: Throwable) {
            if (e !is RepositoryException) {
                throw RepositoryException(errorMessage, e)
            } else {
                throw e
            }
        }
    }
}