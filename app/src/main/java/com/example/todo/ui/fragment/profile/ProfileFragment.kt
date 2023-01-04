package com.example.todo.ui.fragment.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.example.todo.base.BaseFragment
import com.example.todo.databinding.FragmentProfileBinding
import java.io.IOException


class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    @SuppressLint("IntentReset")
    override fun setupUI() {

        binding.changeImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            @Suppress("DEPRECATION")
            startActivityForResult(intent, 1)
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && data != null) {
            val uri = data.data as Uri
            try {
                Toast.makeText(requireContext(), "success" , Toast.LENGTH_SHORT).show()
                binding.changeImage.setImageURI(uri)
            } catch (e : IOException) {
                Toast.makeText(requireContext(), e.message.toString() , Toast.LENGTH_SHORT).show()
                Log.d("ololo", e.message.toString())
            }
        } else {
            Toast.makeText(requireContext(), "fail" , Toast.LENGTH_SHORT).show()
        }
    }

    // Попытался сделать это через Activity Result API но никак не заработало

}