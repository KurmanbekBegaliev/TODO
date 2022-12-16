package com.example.todo.ui.fragment.board.screens

import com.example.todo.R
import com.example.todo.base.BaseFragment
import com.example.todo.databinding.FragmentSecondScreenBinding


class SecondScreen : BaseFragment<FragmentSecondScreenBinding>(FragmentSecondScreenBinding::inflate) {
    override fun setupUI() {
        binding.avItemBoard.setAnimation(R.raw.animation_contacts)
    }

}