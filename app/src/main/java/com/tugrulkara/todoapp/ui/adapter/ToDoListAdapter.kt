package com.tugrulkara.todoapp.ui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.tugrulkara.todoapp.data.entity.Note
import com.tugrulkara.todoapp.databinding.ItemNoteBinding
import com.tugrulkara.todoapp.ui.fragment.HomeFragmentDirections
import com.tugrulkara.todoapp.ui.viewmodel.HomeViewModel


class ToDoListAdapter(var mContext: Context,
                      var noteList:List<Note>,
                      var viewModel: HomeViewModel):
    RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder>() {

    inner class ToDoListViewHolder(var binding:ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return ToDoListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        val note = noteList[position]
        val t = holder.binding
        t.textViewTitle.text = note.note_title
        t.textViewNote.text = note.note_text

        if (note.check_info){
            t.checkBox.isChecked = true
            t.cardView.setBackgroundColor(Color.GREEN)
            if (!t.textViewNote.getPaint().isStrikeThruText()) {
                t.textViewNote.setPaintFlags(t.textViewNote.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
                t.textViewTitle.setPaintFlags(t.textViewNote.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
            } else {
                t.textViewNote.setPaintFlags(t.textViewNote.getPaintFlags() and Paint.STRIKE_THRU_TEXT_FLAG.inv())
                t.textViewTitle.setPaintFlags(t.textViewNote.getPaintFlags() and Paint.STRIKE_THRU_TEXT_FLAG.inv())
            }
        }else{
            t.checkBox.isChecked = false
            t.cardView.setBackgroundColor(Color.WHITE)
        }

        t.checkBox.setOnClickListener {
            if (note.check_info){
                checkUpdate(Note(note.note_id,note.note_title,note.note_text,false))
            }else{
                checkUpdate(Note(note.note_id,note.note_title,note.note_text,true))
            }

        }

        t.imageViewDelete.setOnClickListener {
            Snackbar.make(it,"${note.note_title} delete?", Snackbar.LENGTH_SHORT)
                .setAction("YES"){
                    delete(note.note_id)
                }.show()
        }

        t.cardView.setOnClickListener {
            val navigate = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(note)
            Navigation.findNavController(it).navigate(navigate)
        }


    }

    fun delete(note_id:Int){
        viewModel.delete(note_id)
    }

    fun checkUpdate(note:Note){
        viewModel.updateCheck(note)
    }

}