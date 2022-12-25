package com.example.todo.ui.fragment.note


import android.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.base.BaseFragment
import com.example.todo.databinding.FragmentNoteBinding
import com.example.todo.model.NoteModel
import com.example.todo.ui.App

class NoteFragment : BaseFragment<FragmentNoteBinding>(FragmentNoteBinding::inflate),
    NoteAdapter.NoteListener {

    private lateinit var adapter: NoteAdapter

    companion object {
        const val KEY_FOR_ADD_TITLE = "TITLE"
        const val KEY_FOR_ADD_DES = "DESCRIPTION"
    }

    override fun setupUI() {
        adapter = NoteAdapter(this)
        adapter.setList(App.db.getDao().getAllNote() as ArrayList<NoteModel>)
        binding.rvNote.adapter = adapter
    }

    override fun setupObserver() {
        super.setupObserver()


        binding.floatingActionButton.setOnClickListener {
            controller.navigate(R.id.addNoteFragment)
        }
        deleteNote()
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

    override fun upDateNote(title : String, des : String) {
//        App.db.getDao().upDateNote(model)
        controller.navigate(R.id.addNoteFragment,
            bundleOf(
                KEY_FOR_ADD_TITLE to title,
                KEY_FOR_ADD_DES to des
            ))
    }


}