package `in`.co.logicsoft.webview.room.message

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class MessageRepository(val dao: MessageDao) {

    @WorkerThread
    suspend fun insert(message: Message) = dao.insert(message)

    fun getMessage(): LiveData<Message> = dao.getMessage()
}