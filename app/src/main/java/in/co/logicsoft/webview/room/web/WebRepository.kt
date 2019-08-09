package `in`.co.logicsoft.webview.room.web

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class WebRepository(val dao: WebDao) {

    @WorkerThread
    suspend fun insert(web: Web) = dao.insert(web)

    fun getMessage(): LiveData<Web> = dao.getMessage()
}