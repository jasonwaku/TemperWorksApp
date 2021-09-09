package com.wakuzama.temperworks.ui.temper_list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.wakuzama.temperworks.R
import com.wakuzama.temperworks.databinding.FragmentTemperListBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class TemperListFragment: Fragment(R.layout.fragment_temper_list) {

    private val viewModel by viewModels<TemperListViewModel>()
    private var _binding: FragmentTemperListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTemperListBinding.bind(view)

        val adapter = TemperWorksItemAdapter()
        val srRefresher = binding.srRefresher
        val current = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(current.toString(), formatter)
        val formatted = "${date.dayOfWeek} ${date.dayOfMonth} ${date.month}"

        srRefresher.setOnRefreshListener {
            adapter.retry()
            adapter.notifyDataSetChanged()
            srRefresher.isRefreshing = false
        }

        binding.apply {
            tvDate.text = formatted

            rvTemperList.setHasFixedSize(true)
            rvTemperList.itemAnimator = null
            rvTemperList.adapter = adapter.withLoadStateHeaderAndFooter(
                header = TemperWorksItemLoadStateAdapter{adapter.retry()},
                footer = TemperWorksItemLoadStateAdapter{adapter.retry()},
            )
            btnTryAgain.setOnClickListener{
                adapter.retry()
            }
            btnLoginIn.setOnClickListener{
                val action = TemperListFragmentDirections.actionTemperListFragmentToLoginFragment()
                findNavController().navigate(action)
            }
            btnSignUp.setOnClickListener {
                val action = TemperListFragmentDirections.actionTemperListFragmentToSignUpFragment()
                findNavController().navigate(action)
            }
            fabFilter.setOnClickListener {
                Toast.makeText(it.context, "Coming Soon!", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.posts.observe(viewLifecycleOwner){
            // passing values to RV adapter
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
            srRefresher.isRefreshing = false
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                pbTemperList.isVisible = loadState.source.refresh is LoadState.Loading
                rvTemperList.isVisible = loadState.source.refresh is LoadState.NotLoading
                btnTryAgain.isVisible = loadState.source.refresh is LoadState.Error
                tvFailMsg.isVisible = loadState.source.refresh is LoadState.Error

                //is empty view
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    rvTemperList.isVisible = false
                    tvNoResults.isVisible = true
                } else {
                    tvNoResults.isVisible = false
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}