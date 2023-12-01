package com.bitcodetech.recyclerview4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerPosts: RecyclerView
    private val posts: ArrayList<Post> = ArrayList<Post>();
    private val advertisements = ArrayList<Advertisement>()
    private lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
        initData()
        initAdapter()
        initListeners()
    }

    private fun initViews() {
        setContentView(R.layout.activity_main)
        recyclerPosts = findViewById(R.id.recyclerPosts)

        recyclerPosts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //recyclerPosts.layoutManager = GridLayoutManager(this, 3)
        //recyclerPosts.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun initData() {
        for (i in 0..20) {
            posts.add(Post(i, R.mipmap.ic_launcher, "Post $i"))
        }

        for (i in 0..20) {
            advertisements.add(
                Advertisement(i, R.drawable.bitcode, "Advertisement $i")
            )
        }
    }

    private fun initAdapter() {
        postsAdapter = PostsAdapter(posts, advertisements)
        recyclerPosts.adapter = postsAdapter
    }

    private fun initListeners() {
        postsAdapter.onPostClickListener =
            object : PostsAdapter.OnPostClickListener {
                override fun onPostClick(position: Int, post: Post, postsAdapter: PostsAdapter) {
                    post.title.toast(this@MainActivity)
                }
            }

        postsAdapter.onAdvertisementClickListener =
            object : PostsAdapter.OnAdvertisementClickListener {
                override fun onAdvertisementClick(
                    position: Int,
                    advertisement: Advertisement,
                    postsAdapter: PostsAdapter
                ) {
                    advertisement.text.toast(this@MainActivity)
                }
            }
    }
}