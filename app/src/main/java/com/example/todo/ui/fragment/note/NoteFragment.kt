package com.example.todo.ui.fragment.note


import com.example.todo.R
import com.example.todo.base.BaseFragment
import com.example.todo.databinding.FragmentNoteBinding
import com.example.todo.ui.App

class NoteFragment : BaseFragment<FragmentNoteBinding>(FragmentNoteBinding::inflate) {

    private lateinit var adapter: NoteAdapter

    override fun setupUI() {
        adapter = NoteAdapter()
        binding.rvNote.adapter = adapter
        adapter.setList(App.db.getDao().getAllNote() as ArrayList)
    }

    override fun setupObserver() {
        super.setupObserver()


        binding.floatingActionButton.setOnClickListener {
            controller.navigate(R.id.addNoteFragment)
        }
    }


}