package com.wtt.muslimmedia.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecyclerListener
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.wtt.muslimmedia.DetailActivity
import com.wtt.muslimmedia.data.ArticlesItem
import com.wtt.muslimmedia.databinding.ItemNewsBinding
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
    private var listNews = ArrayList<ArticlesItem>()

    fun setData(list: List<ArticlesItem>?) {
        if (list == null) return
        listNews.clear()
        listNews.addAll(list)
    }

    inner class MyViewHolder(val binding: ItemNewsBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = listNews.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listNews[position]

        val date = data.publishedAt?.take(10)
        val dateArray = date?.split("-")?.toTypedArray() // Sep, 27 '23

        val time = data.publishedAt?.takeLast(9)
        val timeArray = time?.split(":")?.toTypedArray()

        val calendar = Calendar.getInstance()
        dateArray?.let {
            calendar.set(Calendar.YEAR, it[0].toInt())
            calendar.set(Calendar.MONTH, it[1].toInt() -1)
            calendar.set(Calendar.DATE, it[2].toInt())
        }
        timeArray?.let {
            calendar.set(Calendar.HOUR, it[0].toInt())
            calendar.set(Calendar.MINUTE, it[1].toInt())
        }

        val dateFormat = SimpleDateFormat("MMM, dd, ''yy", Locale.getDefault()).format(calendar.time)
        val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault()).format(calendar.time)

        val publishedResult = "$dateFormat at $timeFormat"
        holder.binding.apply {
            itemTitle.text = data.title
            itemDate.text = publishedResult
            Picasso.get().load(data.urlToImage).into(itemImage)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, data)
            it.context.startActivity(intent)
        }
    }
}