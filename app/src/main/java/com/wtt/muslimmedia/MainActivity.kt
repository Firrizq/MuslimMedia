package com.wtt.muslimmedia

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.google.android.material.tabs.TabLayoutMediator
import com.wtt.muslimmedia.adapter.SectionPageAdapter
import com.wtt.muslimmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)

        // set adapter ViewPager2 using SectionPageAdapter class
        binding.vpContainer.adapter = SectionPageAdapter(this)
        // array for set title tab item in TabLayout
        val listFragment = arrayOf("Common", "About Quran", "Al Jazeera", "Warning for muslims")

        // set TabLayout and ViewPager2 so, can bind each other
        TabLayoutMediator(binding.tabLayout, binding.vpContainer) { tab, position ->
            tab.text = listFragment[position]
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchView = menu?.findItem(R.id.option_search)?.actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_search -> onSearchRequested()
            else -> super.onOptionsItemSelected(item)
        }
    }
}