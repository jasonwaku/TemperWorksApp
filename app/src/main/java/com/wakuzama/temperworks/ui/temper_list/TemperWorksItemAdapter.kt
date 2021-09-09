package com.wakuzama.temperworks.ui.temper_list

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.wakuzama.temperworks.R
import com.wakuzama.temperworks.data.TemperWorksItem
import com.wakuzama.temperworks.databinding.ItemTemperWorksBinding
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class TemperWorksItemAdapter :
    PagingDataAdapter<TemperWorksItem, TemperWorksItemAdapter.PostsViewHolder>(POSTS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding =
            ItemTemperWorksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class PostsViewHolder(private val binding: ItemTemperWorksBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: TemperWorksItem) {
            binding.apply {
                Glide.with(itemView).load(post.job.links.hero_380_image).centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error).into(ivTemperMain)

                //price tag
                val currency: String? = post.earnings_per_hour?.currency
                val amount: String? = (post.earnings_per_hour?.amount)?.toDouble().toString()
                val price = "$currency $amount"
                tvPrice.text = price

                //job title
                tvJobTitle.text = post.job.category.name_translation.en_GB

                //client
                tvClient.text = post.job.project.client.name

                //time period
                val startTime: String = convertTime(post.starts_at!!)
                val endTime: String = convertTime(post.ends_at!!)
                val duration = "$startTime - $endTime"
                tvDuration.text = duration

                //location
                ivGetDirection.setOnClickListener {
                    val uri = post.job.report_at_address.links.get_directions
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                    it.context.startActivity(intent);
                }
            }
        }

        private fun convertTime(str: String): String {
            val zonedDateTime = ZonedDateTime.parse(str)
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            return zonedDateTime.format(formatter).toString()
        }

    }

    companion object {
        private val POSTS_COMPARATOR = object : DiffUtil.ItemCallback<TemperWorksItem>() {
            override fun areItemsTheSame(oldItem: TemperWorksItem, newItem: TemperWorksItem) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TemperWorksItem, newItem: TemperWorksItem) =
                oldItem.id == newItem.id
        }
    }

}

