package com.example.notes_project

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_project.presentation.Body
import com.example.notes_project.presentation.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.combine
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup
import javax.inject.Inject

class BtmDialog : BottomSheetDialogFragment() {
    lateinit var adapter: ColorsAdapter
    //lateinit var colors: List<Int>
    lateinit var state: MutableList<Boolean>

    @Inject
    lateinit var bodyActivity: Body

    lateinit var style: String

    lateinit var themedButtonGroup: ThemedToggleButtonGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //arguments?.let {
        //    // = it.getString(ARG_PARAM1)
        //    //param2 = it.getString(ARG_PARAM2)
        //    colors = it.getIntArray("colors")?.toList()!!
        //    state = it.getBooleanArray("state")?.toMutableList()!!
        //}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bottom_sheet_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        themedButtonGroup = view.findViewById(R.id.style)
        themedButtonGroup.setOnSelectListener {
            if(!(activity as Body).vm.styleState.equals(it.text)){
                (activity as Body).vm.setTextStyle(it.text)
            }
        }
        (activity as Body).vm.colorState.observe(this, {
            state = it
            reinitRView(it)
        })

        (activity as Body).vm.styleState.observe(this, {
            reinitTogleGroup(it)
        })
    }

    //companion object {
    //    @JvmStatic
    //    fun newInstance(state: BooleanArray, colors: IntArray) =
    //        BtmDialog().apply {
    //            arguments = Bundle().apply {
    //                //type here
    //                putBooleanArray("state", state)
    //                putIntArray("colors", colors)
    //            }
    //        }
    //}
    fun reinitRView(newTickVisibilities: MutableList<Boolean>){
        val spanned = SpannableStringBuilder((activity as Body).binding.emailPhone.text)
        adapter = ColorsAdapter(object : onItemClickListener{
            override fun onItemClick(prevPos: Int, position: Int) {

                //меняем цвет для выделенного текста
                val start = (activity as Body).binding.emailPhone.selectionStart
                val end = (activity as Body).binding.emailPhone.selectionEnd
                if(end - start != 0){
                    spanned.setSpan(ForegroundColorSpan(resources.getIntArray(R.array.text_colors)[position]), (activity as Body).binding.emailPhone.selectionStart, (activity as Body).binding.emailPhone.selectionEnd, 0)
                    (activity as Body).binding.emailPhone.setText(spanned)
                    (activity as Body).binding.emailPhone.setSelection(start, end)
                }
                else{
                    state[prevPos] = false
                    state[position] = true
                    (activity as Body).vm.setChoosedColorState(state)
                }

            }
        }, resources.getIntArray(R.array.text_colors).toList())
        val rv = view?.findViewById<RecyclerView>(R.id.colors)
        rv?.adapter = adapter
        rv?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        adapter.updateList(newTickVisibilities)
    }

    fun reinitTogleGroup(style: String){
        val selectedButtons = themedButtonGroup?.selectedButtons
        val allButtons = themedButtonGroup?.buttons
        val needBtn = allButtons?.filter {it.text.equals(style)}
        themedButtonGroup?.selectButton(needBtn!![0])
        //val unselectedButtons = allButtons?.filter { !it.isSelected }
    }
}