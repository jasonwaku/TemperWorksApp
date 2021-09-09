package com.wakuzama.temperworks.ui.temper_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wakuzama.temperworks.databinding.TemperWorksListLoadStateFooterBinding

class TemperWorksItemLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<TemperWorksItemLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = TemperWorksListLoadStateFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(private val binding: TemperWorksListLoadStateFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnReload.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                pbFooter.isVisible = loadState is LoadState.Loading
                btnReload.isVisible = loadState !is LoadState.Loading
                tvFooter.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}