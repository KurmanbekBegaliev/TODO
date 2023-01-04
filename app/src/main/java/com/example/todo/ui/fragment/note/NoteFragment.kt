package com.example.todo.ui.fragment.note

import android.app.AlertDialog
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.base.BaseFragment
import com.example.todo.databinding.FragmentNoteBinding
import com.example.todo.model.NoteModel
import com.example.todo.ui.App

class NoteFragment : BaseFragment<FragmentNoteBinding>(FragmentNoteBinding::inflate),
    NoteAdapter.NoteListener, PopupMenu.OnMenuItemClickListener {

    private lateinit var adapter: NoteAdapter


    override fun setupUI() {
        adapter = NoteAdapter(this)
        adapter.setList(App.db.getDao().getAllNote() as ArrayList<NoteModel>)
        binding.rvNote.adapter = adapter
    }

    override fun setupObserver() {
        super.setupObserver()
        binding.floatingActionButton.setOnClickListener {
            val mod = NoteModel(title = "", description = "")
            controller.navigate(NoteFragmentDirections.actionNoteFragmentToAddNoteFragment(mod))
        }

        binding.btnSort.setOnClickListener {
            showMenu(binding.btnSort)
        }
        deleteNote()
    }

    private fun showMenu(v: View) {
        PopupMenu(requireActivity(), v).apply {
            setOnMenuItemClickListener(this@NoteFragment)
            inflate(R.menu.sort_menu)
            show()
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sort_id_desc-> {
                adapter.sortList(App.db.getDao().getAllNote() as ArrayList<NoteModel>)
                true
            }
            R.id.sort_id -> {
                adapter.sortList(App.db.getDao().getAllNoteDesc() as ArrayList<NoteModel>)
                true
            }
            R.id.sort_title -> {
                adapter.sortList(App.db.getDao().getAllNoteSortedAlp() as ArrayList<NoteModel>)
                true
            }
            R.id.sort_title_desc -> {
                adapter.sortList(App.db.getDao().getAllNoteSortedAlpDesc() as ArrayList<NoteModel>)
                true
            }
            else -> false
        }
    }


    private fun deleteNote() {
        val simpleCallBack = object : ItemTouchHelper
        .SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                showAlert(viewHolder.adapterPosition)
            }

        }
        val itemTouchHelper = ItemTouchHelper(simpleCallBack)
        itemTouchHelper.attachToRecyclerView(binding.rvNote)
    }


    private fun showAlert(direction : Int) {
        val builder = AlertDialog.Builder(context)
        builder
            .setCancelable(true)
            .setTitle("Удаление")
            .setMessage("Вы действительно хотите удалить")
            .setPositiveButton("Да") { _, _ ->
                adapter.deleteNote(direction)
            }
            .setNegativeButton("Нет") { dialog, _ ->
                adapter.notifyItemChanged(direction)
                dialog.cancel()
            }
        builder.create().show()
    }

    override fun upDateNote(model : NoteModel) {
        controller.navigate(NoteFragmentDirections.actionNoteFragmentToAddNoteFragment(model))
    }


}