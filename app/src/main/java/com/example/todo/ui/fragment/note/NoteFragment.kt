package com.example.todo.ui.fragment.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.base.BaseFragment
import com.example.todo.databinding.FragmentNoteBinding
import com.example.todo.ui.fragment.addnote.AddNoteFragment

class NoteFragment : BaseFragment<FragmentNoteBinding>(FragmentNoteBinding::inflate) {
    override fun setupUI() {

    }

    override fun setupObserver() {
        super.setupObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            controller.navigate(R.id.addNoteFragment)
        }
    }
}