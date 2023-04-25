package com.example.notes_project.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_project.ColorsAdapter
import com.example.notes_project.R
import com.example.notes_project.onItemClickListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.combine
import java.text.FieldPosition

class BottomDialog(val colors: List<Int>, val state: MutableList<Boolean>, val bodyActivity: Body): BottomSheetDialogFragment() {

    lateinit var adapter: ColorsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //adapter = ColorsAdapter(colors, state, object : onItemClickListener{
        //    override fun onItemClick(prevPos: Int, position: Int) {
        //        state[prevPos] = false
        //        state[position] = true
        //        bodyActivity.vm.setChoosedColorState(state)
        //    }
//
        //})
        val rv = view.findViewById<RecyclerView>(R.id.colors)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(bodyActivity, LinearLayoutManager.HORIZONTAL, false)
    }

    fun reinitRView(newTickVisibilities: MutableList<Boolean>){
        adapter.updateList(newTickVisibilities)
    }

}