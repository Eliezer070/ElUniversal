package com.eas.challenge.notes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.eas.challenge.R
import com.eas.challenge.base.NoteDatabase
import com.eas.challenge.notes.fragments.Details
import com.eas.sdk.commons.models.Note
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.note_item.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class NotesAdapter(val items: ArrayList<Note>, val context: Context) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.noteTitle?.text = items.get(position).title
        holder?.noteTitle?.text = items.get(position).title
        holder?.section?.text = items.get(position).main_section
        holder?.date?.text = items.get(position).pubdate
        val picasso = Picasso.get()
        if (!items.get(position).image_small_size.isEmpty()) {
            picasso.load(items.get(position).image_small_size).fit()
                .into(holder?.poster)
        }

        holder.like.setOnClickListener(View.OnClickListener {
            //saveFavorite(holder.like.context, items.get(position))
            holder?.like.setBackgroundResource(R.drawable.ic_share_ticket)
            val myIntent = Intent(Intent.ACTION_SEND)
            myIntent.type = "text/html"
            val shareBody = items.get(position).body_html
            val shareSub = items.get(position).author
            myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub)
            myIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            context.startActivity(
                Intent.createChooser(
                    myIntent,
                    "Share using"
                )
            )
        })

        holder.noteTitle.setOnClickListener(View.OnClickListener {
            Details.showDialog(context, items.get(position))
        })

    }

    // Gets the number of movies in the list
    override fun getItemCount(): Int {
        return items.size
    }

    private fun saveFavorite(context: Context, note: Note) {
        GlobalScope.launch(Dispatchers.Main) {
            val repo = NoteDatabase.getInstance(context)
            async(Dispatchers.IO) {repo!!.noteDAO().insert(note)}
        }
    }
        
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each movie to
    val noteTitle = view.tv_title
    val section = view.tv_section
    val like = view.bn_like
    val poster = view.iv_poster
    val date = view.tv_date
}
