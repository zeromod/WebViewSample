package `in`.co.logicsoft.webview.ui.main

import `in`.co.logicsoft.salesrepresentative.util.EventObserver
import `in`.co.logicsoft.webview.R
import `in`.co.logicsoft.webview.ui.webview.WebViewActivity
import `in`.co.logicsoft.webview.databinding.ActivityMainBinding
import `in`.co.logicsoft.webview.room.web.Web
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        viewModel.setMessage(Web(url = "https://www.google.com/"))

        subscribeUI()
    }

    private fun subscribeUI() {
        viewModel.webViewEvent.observe(this, EventObserver {
            val intent = WebViewActivity.newInstance(applicationContext, it)
            startActivity(intent)
        })

        viewModel.tabsEvent.observe(this, EventObserver {
            val builder = CustomTabsIntent.Builder()
            val customTabIntent = builder.build()
            customTabIntent.launchUrl(this, Uri.parse(it))
        })
    }
}
