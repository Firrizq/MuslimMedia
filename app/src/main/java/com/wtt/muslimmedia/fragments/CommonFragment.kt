package com.wtt.muslimmedia.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wtt.muslimmedia.NewsViewModel
import com.wtt.muslimmedia.R
import com.wtt.muslimmedia.adapter.NewsAdapter
import com.wtt.muslimmedia.databinding.FragmentCommonBinding


class CommonFragment : Fragment() {
    private var _binding: FragmentCommonBinding? = null
    private val binding get() = _binding as FragmentCommonBinding

    private var _viewModel: NewsViewModel? = null
    private val viewModel get() = _viewModel as NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCommonBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingView.root.visibility = View.VISIBLE
        _viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        viewModel.getCommonMuslimNews()
        viewModel.commonMuslimNews.observe(viewLifecycleOwner) {
            val data = it.articles
            binding.rvCommon.apply {
                val mAdapter = NewsAdapter()
                mAdapter.setData(data)
                adapter = mAdapter
                layoutManager = LinearLayoutManager(context)
            }
            binding.loadingView.root.visibility = View.GONE
        }
    }
}