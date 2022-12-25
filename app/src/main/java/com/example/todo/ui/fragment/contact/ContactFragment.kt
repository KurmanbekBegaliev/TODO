package com.example.todo.ui.fragment.contact

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.todo.base.BaseFragment
import com.example.todo.databinding.FragmentContactBinding
import com.example.todo.model.ContactsModel

class ContactFragment : BaseFragment<FragmentContactBinding>(FragmentContactBinding::inflate),
    ContactsAdapter.ClickListener {

    private var arrayContacts = arrayListOf<ContactsModel>()
    private lateinit var adapter: ContactsAdapter

    override fun setupUI() {
        initContacts()

        adapter = ContactsAdapter(this, arrayContacts)
        binding.rvContacts.adapter = adapter
    }




    @SuppressLint("Range")
    private fun initContacts() {
        if (checkPermission(READ_CONTACTS)) {
            val cursor = requireContext().contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null
            )

            cursor?.let {
                while (it.moveToNext()) {
                    val fullName = it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    val number = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                    val newModel = ContactsModel()
                    newModel.name = fullName
                    newModel.number = number
                    arrayContacts.add(newModel)
                }
            }
            cursor?.close()

        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ContextCompat.checkSelfPermission(requireContext(), READ_CONTACTS)
            == PackageManager.PERMISSION_GRANTED) {
            initContacts()
        }
    }

    private fun checkPermission(permission: String) : Boolean {
        return if (ContextCompat.checkSelfPermission(
                requireContext(),
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission),
                PERMISSION_REQUEST
            )
            false
        } else true
    }

    companion object {
        const val READ_CONTACTS = Manifest.permission.READ_CONTACTS
        const val PERMISSION_REQUEST = 200
    }

    override fun callListener(position: Int) {
        val number = arrayContacts[position].number.toString()
        val intent = Intent(Intent.ACTION_DIAL)
        intent.setData(Uri.parse("tel:$number"))
        try {
            startActivity(intent)
        } catch (e : Exception) {
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun waListener(position: Int) {
        val number = arrayContacts[position].number.toString()
        val intent = Intent(Intent.ACTION_VIEW)
//        intent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
        intent.data = Uri.parse("http://api.whatsapp.com/send?phone=$number")
        intent.setPackage("com.whatsapp")

        try {
            startActivity(intent)
        } catch (e : Exception) {
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
            Log.e("ololo", e.message.toString())
        }
    }

}