package com.example.todo.ui.board


import android.os.Bundle
import android.view.View
import android.view.View.*
import androidx.viewpager2.widget.ViewPager2
import com.example.todo.R
import com.example.todo.base.BaseFragment
import com.example.todo.databinding.FragmentOnBoardBinding

class OnBoardFragment : BaseFragment<FragmentOnBoardBinding>(FragmentOnBoardBinding::inflate) {

    private lateinit var adapter: BoardAdapter

    override fun setupUI() {
        adapter = BoardAdapter()
        binding.boardPager.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStart.setOnClickListener {
            controller.navigate(R.id.noteFragment)
        }

        binding.boardPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                changeSet()
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                changeSet()
            }
        })
    }

    private fun changeSet() {
        when (binding.boardPager.currentItem) {
            0->{
                binding.inOne.setBackgroundColor(requireContext().getColor(R.color.main_color))
                binding.inTwo.setBackgroundColor(requireContext().getColor(R.color.gray))
                binding.inThree.setBackgroundColor(requireContext().getColor(R.color.gray))
                binding.btnStart.visibility = GONE
            }

            1-> {
                binding.inOne.setBackgroundColor(requireContext().getColor(R.color.gray))
                binding.inTwo.setBackgroundColor(requireContext().getColor(R.color.main_color))
                binding.inThree.setBackgroundColor(requireContext().getColor(R.color.gray))
                binding.btnStart.visibility = GONE
            }

            2-> {
                binding.inOne.setBackgroundColor(requireContext().getColor(R.color.gray))
                binding.inTwo.setBackgroundColor(requireContext().getColor(R.color.gray))
                binding.inThree.setBackgroundColor(requireContext().getColor(R.color.main_color))
                binding.btnStart.visibility = VISIBLE
            }
        }
    }

}