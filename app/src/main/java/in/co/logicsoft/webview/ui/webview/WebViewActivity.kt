package `in`.co.logicsoft.webview.ui.webview

import `in`.co.logicsoft.webview.R
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    companion object {
        private val URL_KEY = "URL_KEY"
        fun newInstance(context: Context, url: String) = Intent(context, WebViewActivity::class.java).apply {
            putExtra(URL_KEY, url)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        web_view.webViewClient = WebViewClient()

        val url = intent?.getStringExtra(URL_KEY)
        url?.let {
            web_view.loadUrl(it)
        }
    }
}
