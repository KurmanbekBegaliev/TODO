package com.example.todo.ui.fragment.board

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.todo.R
import com.example.todo.base.BaseFragment
import com.example.todo.databinding.FragmentOnBoardBinding
import com.example.todo.ui.App
import com.example.todo.ui.fragment.board.screens.FirstScreen
import com.example.todo.ui.fragment.board.screens.SecondScreen
import com.example.todo.ui.fragment.board.screens.ThridScreen
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardFragment : BaseFragment<FragmentOnBoardBinding>(FragmentOnBoardBinding::inflate),
    OnBtnStartClick{

    private lateinit var adapter: BoardAdapter

    override fun setupUI() {

        val fragmentList = listOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThridScreen()
        )

        adapter = BoardAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
        binding.boardPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.boardPager) {tab, position ->
            tab.setIcon(R.drawable.ellipse_2)
        }.attach()

    }

    override fun onBtnStartClick() {
        controller.navigateUp()
        App.prefs.saveBoardState()
    }


}