package com.example.todo.ui.fragment.addnote

import com.example.todo.base.BaseFragment
import com.example.todo.databinding.FragmentAddNoteBinding
import com.example.todo.model.NoteModel
import com.example.todo.ui.App
import java.text.SimpleDateFormat
import java.util.*

class AddNoteFragment : BaseFragment<FragmentAddNoteBinding>(FragmentAddNoteBinding::inflate) {

    private var model: NoteModel? = null

    override fun setupUI() {
        getDateTime()
        saveNote()
        back()

    }

    override fun setupObserver() {
        super.setupObserver()
        getUpdateArguments()
    }



    private fun getUpdateArguments() {
        model = AddNoteFragmentArgs.fromBundle(requireArguments()).argModel as NoteModel
        model?.let {
            binding.edtAddTitle.setText(it.title.toString())
            binding.edtAddDescription.setText(it.description.toString())
        }
    }


    private fun saveNote() {
        binding.btnConfirmAddNote.setOnClickListener {
            val title = binding.edtAddTitle.text.toString()
            val des = binding.edtAddDescription.text.toString()
            val dateTime = binding.tvAddTime.text.toString()

            App.db.getDao().addNote(NoteModel(title = title, description = des, dateTime = dateTime))
            controller.navigateUp()
        }
    }

    private fun back() {
        binding.btnCancelAddNote.setOnClickListener {
            controller.navigateUp()
        }
    }


    private fun getDateTime() {
        val sdf = SimpleDateFormat("dd.M.yyyy  hh:mm", Locale.ROOT)
        val currentDate = sdf.format(Date())
        binding.tvAddTime.text = currentDate.toString()
    }



}