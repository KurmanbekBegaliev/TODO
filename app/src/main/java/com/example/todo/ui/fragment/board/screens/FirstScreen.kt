package com.example.todo.ui.fragment.board.screens

import com.example.todo.R
import com.example.todo.base.BaseFragment
import com.example.todo.databinding.FragmentFirstScreenBinding


class FirstScreen : BaseFragment<FragmentFirstScreenBinding>(FragmentFirstScreenBinding::inflate) {
    override fun setupUI() {
        binding.avItemBoard.setAnimation(R.raw.animation_notes)
    }

}