package jp.techacademy.youichi.okami.apiapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.techacademy.youichi.okami.apiapp.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding
    var isFavorite: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // ActionBarの追加
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "クーポン詳細"

        binding.webView.loadUrl(intent.getStringExtra(KEY_URL).toString())

        // Shop情報を受取
        val id = intent.getStringExtra(KEY_ID).toString()
        val name = intent.getStringExtra(KEY_NAME).toString()
        val address = intent.getStringExtra(KEY_ADDRESS).toString()
        val imageUrl = intent.getStringExtra(KEY_IMAGEURL).toString()

        // お気に入り状態を取得
        isFavorite = FavoriteShop.findBy(id) != null
    }

    companion object {
        private const val KEY_URL = "key_url"
        private const val KEY_ID = "key_id"
        private const val KEY_NAME = "key_name"
        private const val KEY_ADDRESS = "key_address"
        private const val KEY_IMAGEURL = "key_imageUrl"
        fun start(activity: Activity, url: String, id: String, name: String, address: String, imageUrl: String) {
            val intent = Intent(activity, WebViewActivity::class.java)
            intent.putExtra(KEY_URL, url)
            intent.putExtra(KEY_ID, id)
            intent.putExtra(KEY_NAME, name)
            intent.putExtra(KEY_ADDRESS, address)
            intent.putExtra(KEY_IMAGEURL, imageUrl)
            activity.startActivity(intent)
        }
    }
}