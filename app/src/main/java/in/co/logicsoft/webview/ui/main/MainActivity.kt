package `in`.co.logicsoft.webview.ui.main

import `in`.co.logicsoft.webview.R
import `in`.co.logicsoft.webview.ui.webview.WebViewActivity
import `in`.co.logicsoft.webview.databinding.ActivityMainBinding
import `in`.co.logicsoft.webview.room.message.Message
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        viewModel.setMessage(Message(text = "Web Sample"))
    }

    fun openInWebView(view: View) {
        val intent = Intent(this, WebViewActivity::class.java)
        startActivity(intent)
    }

    fun openInCustomTabs(view: View) {
        val builder = CustomTabsIntent.Builder()
        val customTabIntent = builder.build()
        customTabIntent.launchUrl(this, Uri.parse("https://www.google.com/"))
    }
}
