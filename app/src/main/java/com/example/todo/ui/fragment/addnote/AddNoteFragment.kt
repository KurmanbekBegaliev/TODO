package com.example.todo.ui.fragment.addnote

import android.os.Bundle
import android.view.View
import com.example.todo.base.BaseFragment
import com.example.todo.databinding.FragmentAddNoteBinding
import com.example.todo.model.NoteModel
import com.example.todo.ui.App
import java.text.SimpleDateFormat
import java.util.*

class AddNoteFragment : BaseFragment<FragmentAddNoteBinding>(FragmentAddNoteBinding::inflate) {

    private lateinit var model: NoteModel

    override fun setupUI() {
        getUpdateArguments()
        getDateTime()
        saveNote()
        back()

    }



    private fun getUpdateArguments() {
        val bundle : Bundle? = arguments
        bundle?.let {
            model = it.getSerializable("key") as NoteModel
            binding.edtAddTitle.setText(model.title.toString())
            binding.edtAddDescription.setText(model.description.toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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