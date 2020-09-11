package com.eas.challenge.notes.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eas.challenge.R
import com.eas.challenge.base.NoteDatabase
import com.eas.challenge.notes.adapter.FavoritesAdapter
import com.eas.sdk.commons.models.Note
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.coroutines.*
import java.lang.Exception


class FavoritesFragment : Fragment() {
    lateinit var movies: Deferred<List<Note>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    fun getMoviesFromService(context: Context) {
        GlobalScope.launch(Dispatchers.Main) {
            val repo = NoteDatabase.getInstance(context)
            movies = async(Dispatchers.IO) { repo?.noteDAO()!!.getAllMovies()}

            showMovies(movies.await(), rv_favorites) // back on UI thread
        }

    }

    private fun showMovies(await: List<Note>, rvMovies: RecyclerView) {
        rvMovies.layoutManager = GridLayoutManager(context, 2) as RecyclerView.LayoutManager?
        rvMovies.adapter = FavoritesAdapter(await as ArrayList<Note>, rvMovies.context)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMoviesFromService(view.context)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        try {
            if (isVisibleToUser) {
                getMoviesFromService(context!!)
                rv_favorites.adapter!!.notifyDataSetChanged()
            }
        }catch (ex: Exception){
            ex.printStackTrace()
        }

    }


}
