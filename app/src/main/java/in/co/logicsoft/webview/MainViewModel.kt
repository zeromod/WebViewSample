package `in`.co.logicsoft.webview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val message = MutableLiveData<String>()

    init {
        message.value = "LiveModel initiated"
    }

    fun setMessage(string: String) {
        message.value = string
    }

}