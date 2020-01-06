package br.com.rodilon.testebancomodal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rodilon.testebancomodal.home.model.Repository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_repository_item.view.*

class RepositoryAdapter(private val recyclerList: List<Repository>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_repository_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return recyclerList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(recyclerList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(repository: Repository) = with(itemView) {
            Picasso.get()
                .load(repository.owner.avatarUrl)
                .into(repository_item_imageview)

            repository_item_name.text = repository.name
            repository_item_total_number.text = repository.starts.toString()
            repository_item_followers_number.text = repository.starts.toString()
        }
    }

}