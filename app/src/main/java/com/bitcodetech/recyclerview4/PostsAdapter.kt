package com.bitcodetech.recyclerview4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class PostsAdapter(
    private val posts : ArrayList<Post>,
    private val advertisements : ArrayList<Advertisement>
) : RecyclerView.Adapter<ViewHolder>() {

    val VIEW_TYPE_ADVERTISEMENT = 1
    val VIEW_TYPE_POST = 2

    interface OnPostClickListener {
        fun onPostClick(position : Int, post : Post, postsAdapter: PostsAdapter)
    }
    var onPostClickListener : OnPostClickListener? = null

    interface OnAdvertisementClickListener {
        fun onAdvertisementClick(position: Int, advertisement : Advertisement, postsAdapter: PostsAdapter)
    }
    var onAdvertisementClickListener : OnAdvertisementClickListener? = null


    inner class PostViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val imgPost : ImageView
        val txtPostTitle : TextView

        init {
            imgPost = view.findViewById(R.id.imgPost)
            txtPostTitle = view.findViewById(R.id.txtPostTitle)

            itemView.setOnClickListener {
                if(onPostClickListener != null) {
                    onPostClickListener!!.onPostClick(
                        adapterPosition, posts[adapterPosition/2], this@PostsAdapter
                    )
                }
            }
        }
    }

    inner class AdvertisementViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val imgAdvertisement : ImageView
        val txtAdvertisementText : TextView

        init {
            imgAdvertisement = view.findViewById(R.id.imgAdvertisement)
            txtAdvertisementText = view.findViewById(R.id.txtAdvertisementText)

            itemView.setOnClickListener {
                if(onAdvertisementClickListener != null) {
                    onAdvertisementClickListener!!.onAdvertisementClick(
                        adapterPosition, advertisements[adapterPosition/2], this@PostsAdapter
                    )
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position % 2 == 0) {
            return VIEW_TYPE_POST
        }
        return VIEW_TYPE_ADVERTISEMENT
    }

    override fun getItemCount() = posts.size + advertisements.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        if(viewType == VIEW_TYPE_ADVERTISEMENT) {
            return AdvertisementViewHolder(
                layoutInflater.inflate(R.layout.advertisement_view, null)
            )
        }

        return PostViewHolder(
            layoutInflater.inflate(R.layout.post_view, null)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(holder is PostViewHolder) {
            val post = posts[position/2]
            holder.imgPost.setImageResource(post.imageId)
            holder.txtPostTitle.setText(post.title)
        }

        if(holder is AdvertisementViewHolder) {
            val advertisement = advertisements[position/2]
            holder.imgAdvertisement.setImageResource(advertisement.imageId)
            holder.txtAdvertisementText.text = advertisement.text
        }

    }

}