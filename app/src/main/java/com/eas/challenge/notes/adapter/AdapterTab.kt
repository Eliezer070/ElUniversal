package com.eas.challenge.notes.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.eas.challenge.notes.fragments.NotesFragment
import com.eas.challenge.notes.fragments.FavoritesFragment


class AdapterTab(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                NotesFragment()
            }
            else -> {
                FavoritesFragment()
            }

        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Notes"
            else -> {
                return "Favorites"
            }
        }
    }
}