package `in`.co.logicsoft.webview.ui.main

import `in`.co.logicsoft.webview.room.LsDatabase
import `in`.co.logicsoft.webview.room.message.Message
import `in`.co.logicsoft.webview.room.message.MessageRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val message: LiveData<Message>
    private val repository: MessageRepository

    init {
        val dao = LsDatabase.getDatabase(application).messageDao()
        repository = MessageRepository(dao)
        message = repository.getMessage()
    }

    fun setMessage(message: Message) {
        viewModelScope.launch {
            repository.insert(message)
        }
    }

}