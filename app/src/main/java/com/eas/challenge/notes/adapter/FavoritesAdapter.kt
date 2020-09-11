package com.eas.challenge.notes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eas.challenge.R
import com.eas.challenge.base.NoteDatabase
import com.eas.sdk.commons.models.Note
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.note_item.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavoritesAdapter(val items: ArrayList<Note>, val context: Context) :
    RecyclerView.Adapter<FavoriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.favorite_item, parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder?.movieTitle?.text = items.get(position).title
        holder?.movieTitle?.text = items.get(position).title
        val picasso = Picasso.get()

        picasso.load(items.get(position).image_small_size).fit()
            .into(holder?.poster)

        holder.like.setOnClickListener(View.OnClickListener {
            deleteFavorite(holder.like.context, items.get(position))
            items.removeAt(position)

            this.notifyDataSetChanged()
        })

    }

    // Gets the number of movies in the list
    override fun getItemCount(): Int {
        return items.size
    }

    private fun deleteFavorite(context: Context, note: Note) {
        GlobalScope.launch(Dispatchers.Main) {
            val repo = NoteDatabase.getInstance(context)
            async(Dispatchers.IO) {repo!!.noteDAO().remove(note)}
        }
    }

}

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each movie to
    val movieTitle = view.tv_title
    val like = view.bn_like
    val poster = view.iv_poster
}