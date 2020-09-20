package com.example.materialtutorial.navigation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materialtutorial.R
import com.example.materialtutorial.navigation.model.ItemDTO
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.item_listview.view.*

class ListFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_list, container, false)

        view.layout_RecyclerView.adapter = ListAdapter()
        view.layout_RecyclerView.layoutManager = LinearLayoutManager(activity)

        return view
    }

    inner class ListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        var items : MutableList<ItemDTO> = mutableListOf()

        init {
            for (i in 1..10){
                items.add(ItemDTO(i))
            }

            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_listview, parent, false)
            return ItemViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val viewHolder = (holder as ItemViewHolder).itemView
            viewHolder.item_list_text.text = "item = ${items[position].itemID}"
        }

        override fun getItemCount(): Int = items.size

        inner class ItemViewHolder(view : View) : RecyclerView.ViewHolder(view)
    }

}