package com.example.todo.ui.fragment.board.screens

import android.os.Bundle
import android.view.View
import com.example.todo.R
import com.example.todo.base.BaseFragment
import com.example.todo.databinding.FragmentThridScreenBinding


class ThridScreen : BaseFragment<FragmentThridScreenBinding>(FragmentThridScreenBinding::inflate) {
    override fun setupUI() {
        binding.avItemBoard.setAnimation(R.raw.animation_end)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnItemStart.setOnClickListener {
            controller.navigate(R.id.noteFragment)
        }
    }

}