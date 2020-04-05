package com.liverpool.app

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import com.liverpool.app.model.ProductsResponse
import com.liverpool.app.service.APIService
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val TAG: String = "MainActivity"

    val numberOfPage: Int = 1
    val numberOfItems: Int = 10
    val baseURL: String = "https://shoppapp.liverpool.com.mx/appclienteservices/services/v3/plp/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handleIntent(this.intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return true
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val criteria = intent.getStringExtra(SearchManager.QUERY)
            SearchRecentSuggestions(
                this,
                MySuggestionProvider.AUTHORITY,
                MySuggestionProvider.MODE
            ).saveRecentQuery(criteria, null)
            //-- searchByName(criteria)
        }
    }

    private fun searchByName(criteria: String) {
        doAsync {
            val call = getRetrofit().create(APIService::class.java)
                .getProductsByName("?force-plp=true&search-string=$criteria&page-number=${numberOfPage.toString()}&number-of-items-per-page=${numberOfItems.toString()}")
                .execute()
            val products = call.body() as ProductsResponse
            uiThread {
                if (products.status.status == "OK") {
                    Log.d(TAG, ">> Everything it's Ok")
                } else {
                    Log.d(TAG, ">> An Error has occurred while consulting the api")
                    showErrorDialog();
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun showErrorDialog() {
        alert("Ha ocurrido un error, inténtelo nuevamente.") {
            yesButton { }
        }.show()
    }


}
