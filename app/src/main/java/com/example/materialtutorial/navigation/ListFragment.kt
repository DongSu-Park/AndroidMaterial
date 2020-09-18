package com.example.materialtutorial.navigation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.materialtutorial.R
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_list, container, false)

        val mCollapsingToolbarLayout = view.findViewById<View>(R.id.layout_CollapsingToolbar)

        return view
    }
}