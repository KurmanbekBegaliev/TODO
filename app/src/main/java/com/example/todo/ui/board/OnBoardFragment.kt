package com.example.todo.ui.board

import com.example.todo.base.BaseFragment
import com.example.todo.databinding.FragmentOnBoardBinding

class OnBoardFragment : BaseFragment<FragmentOnBoardBinding>(FragmentOnBoardBinding::inflate) {

    private lateinit var adapter: BoardAdapter

    override fun setupUI() {
        adapter = BoardAdapter()
        binding.boardPager.adapter = adapter
    }

}