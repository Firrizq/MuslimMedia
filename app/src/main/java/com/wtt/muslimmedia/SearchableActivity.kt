package com.wtt.muslimmedia

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wtt.muslimmedia.adapter.NewsAdapter
import com.wtt.muslimmedia.databinding.ActivitySearchableBinding

class SearchableActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private var _binding: ActivitySearchableBinding? = null
    private val binding get() = _binding as ActivitySearchableBinding

    private var _searchViewModel: NewsViewModel? = null
    private val searchViewModel get() = _searchViewModel as NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _searchViewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        handleIntent(intent)

        binding.searchNews.setOnQueryTextListener(this)

        // Verify the action and get the query

        searchViewModel.searchNews.observe(this) {
            binding.rvSearchResult.apply {
                val mAdapter = NewsAdapter()
                mAdapter.setData(it.articles)
                adapter = mAdapter
                layoutManager = LinearLayoutManager(this@SearchableActivity)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            // Kotlin Scope
            // Takes value from the code code that's implementing
            // And then gives it to the lambda expression's body
            intent.getStringExtra(SearchManager.QUERY)?.also {query ->
                doMySearch(query)
            }
        }
    }

    private fun doMySearch(query: String) {
        searchViewModel.searchNews(query)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchNews.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            clearFocus()
            queryHint = "Search for News"
            setQuery("", false)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return when (!newText.isNullOrBlank()) {
            true -> {
                searchViewModel.searchNews(newText)
                true
            }
            else -> false
        }
    }
}