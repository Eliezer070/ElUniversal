package com.eas.challenge.notes.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eas.challenge.R
import com.eas.challenge.notes.adapter.NotesAdapter
import com.eas.sdk.commons.models.Note
import com.eas.sdk.historical.service.HistoricalService
import kotlinx.android.synthetic.main.fragment_movies.*


class NotesFragment : Fragment() {

    lateinit var notes: ArrayList<Note>
    var isLoading = false
    var page = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    fun getNotesFromService(rvNotes: RecyclerView) {
        val service = HistoricalService()
        service.requestHistorical(page) {
            if (it != null) {
                notes = it
                rvNotes.layoutManager =
                    GridLayoutManager(context, 2) as RecyclerView.LayoutManager?
                rvNotes.adapter = NotesAdapter(notes, rvNotes.context)
            } else {
                Log.d("Response", it.toString())
            }
        }

    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNotesFromService(rv_movies as RecyclerView)
        initScrollListener();
    }

    private fun initScrollListener() {

        rv_movies.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(
                recyclerView: RecyclerView,
                newState: Int
            ) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)  && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    loadMore()
                }
            }
        })
       /* rv_movies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!isLoading) {
                    loadMore()
                    isLoading = true
                }
            }
        })*/


    }

    private fun loadMore() {
        page = page + 10
        val service = HistoricalService()
        service.requestHistorical(page) {
            if (it != null) {
                if(it != null) {
                    var position = notes.size
                    notes.addAll(it)
                    rv_movies.adapter!!.notifyItemRangeInserted(position, notes.size-1)
                    isLoading = false
                }
            } else {
                Log.d("Response", it.toString())
            }
        }

    }
}
