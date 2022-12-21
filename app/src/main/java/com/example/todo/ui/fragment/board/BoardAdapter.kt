package com.example.todo.ui.fragment.board

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VISIBLE
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todo.R
import com.example.todo.databinding.ItemBoardBinding


class BoardAdapter(private val ob : OnboardListener) : RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {

    private val titleList = listOf("Notes", "Contacts", "End")
    private val descriptionList = listOf("Add new Notes", "Vew all Contacts", "The End")
    private val animationList = listOf(
        R.raw.animation_notes,
        R.raw.animation_contacts,
        R.raw.animation_end
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        return BoardViewHolder(ItemBoardBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.onBind(position)
        holder.binding.btnItemStart.setOnClickListener {
            ob.onBoardClick()
        }


        if (position == titleList.size - 1) {
            holder.binding.btnItemStart.visibility = VISIBLE
        } else {
            holder.binding.btnItemStart.visibility = GONE
        }
    }

    override fun getItemCount() = titleList.size

    inner class BoardViewHolder(val binding : ItemBoardBinding) : ViewHolder(binding.root){
        fun onBind(position: Int) {
            binding.avItemBoard.setAnimation(animationList[position])
            binding.tvItemTitleBoard.text = titleList[position]
            binding.tvItemDescriptionBoard.text = descriptionList[position]
        }

    }

}

interface OnboardListener {
    fun onBoardClick()
}