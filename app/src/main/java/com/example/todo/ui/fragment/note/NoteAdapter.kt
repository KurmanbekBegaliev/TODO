package com.example.todo.ui.fragment.note

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todo.databinding.ItemNoteBinding
import com.example.todo.model.NoteModel


class NoteAdapter : Adapter<NoteAdapter.NoteViewHolder>() {
    private var list = ArrayList<NoteModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list : ArrayList<NoteModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size


    inner class NoteViewHolder(private val binding : ItemNoteBinding) :
        ViewHolder(binding.root) {
        fun onBind(model: NoteModel) {
            binding.tvItemTitle.text = model.title
            binding.tvItemDescription.text = model.description
        }
    }
}