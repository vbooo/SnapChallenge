package mypromotion.vboo.com.snapchallenge.activity

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import mypromotion.vboo.com.snapchallenge.R


class SearchActionActivity : AppCompatActivity() {

    private var mSearchView: SearchView? = null
    private var mQuery: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_action)

        // Verify the action and get the query
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                doMySearch(query)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)

        val inflater = menuInflater
        inflater.inflate(R.menu.searchview_in_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        mSearchView = searchItem.getActionView() as SearchView
        setupSearchView(searchItem)

        if (mQuery != null) {
            mSearchView!!.setQuery(mQuery, false)
        }

        return true
    }

    private fun setupSearchView(searchItem: MenuItem?) {

    }


    private fun doMySearch(query: String) {
        val toto = query
    }
}
