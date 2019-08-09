package `in`.co.logicsoft.webview.ui.main

import `in`.co.logicsoft.salesrepresentative.util.Event
import `in`.co.logicsoft.webview.room.LsDatabase
import `in`.co.logicsoft.webview.room.web.Web
import `in`.co.logicsoft.webview.room.web.WebRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WebRepository

    val web: LiveData<Web>

    private val _webViewEvent = MutableLiveData<Event<String>>()
    val webViewEvent: LiveData<Event<String>> = _webViewEvent

    private val _tabsEvent = MutableLiveData<Event<String>>()
    val tabsEvent: LiveData<Event<String>> = _tabsEvent

    init {
        val dao = LsDatabase.getDatabase(application).messageDao()
        repository = WebRepository(dao)
        web = repository.getMessage()
    }

    fun setMessage(web: Web) {
        viewModelScope.launch {
            repository.insert(web)
        }
    }

    fun openInWebView(url: String) {
        _webViewEvent.value = Event(url)
    }

    fun openInCustomTabs(url: String) {
        _tabsEvent.value = Event(url)
    }

}