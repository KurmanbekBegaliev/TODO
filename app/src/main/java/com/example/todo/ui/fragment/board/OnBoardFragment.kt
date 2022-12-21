package com.example.todo.ui.fragment.board


import com.example.todo.R
import com.example.todo.base.BaseFragment
import com.example.todo.databinding.FragmentOnBoardBinding
import com.example.todo.ui.App
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardFragment : BaseFragment<FragmentOnBoardBinding>(FragmentOnBoardBinding::inflate),
    OnboardListener{

    override fun setupUI() {
        val adapter = BoardAdapter(this)
        binding.boardPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.boardPager) {tab, position ->
            tab.setIcon(R.drawable.tab_selector)
        }.attach()

    }

    override fun onBoardClick() {
        controller.navigateUp()
        App.prefs.saveBoardState()
    }


}