package com.example.todo.ui.fragment.addnote

import android.os.Bundle
import android.view.View
import com.example.todo.base.BaseFragment
import com.example.todo.databinding.FragmentAddNoteBinding
import com.example.todo.model.NoteModel
import com.example.todo.ui.App

class AddNoteFragment : BaseFragment<FragmentAddNoteBinding>(FragmentAddNoteBinding::inflate) {
    override fun setupUI() {
        saveNote()
        back()

//        binding.edtAddTitle.text = arguments?.getString(NoteFragment.KEY_FOR_ADD_TITLE)
//        binding.edtAddDescription.text = arguments?.getString(NoteFragment.KEY_FOR_ADD_DES)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun saveNote() {
        binding.btnConfirmAddNote.setOnClickListener {
            val title = binding.edtAddTitle.text.toString()
            val des = binding.edtAddDescription.text.toString()

            App.db.getDao().addNote(NoteModel(title = title, description = des))
            controller.navigateUp()
        }
    }

    private fun back() {
        binding.btnCancelAddNote.setOnClickListener {
            controller.navigateUp()
        }
    }



}