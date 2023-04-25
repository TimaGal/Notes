package com.example.notes_project.presentation

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator.INFINITE
import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.SpannedString
import android.text.style.BackgroundColorSpan
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_project.*
import com.example.notes_project.databinding.ActivityBodyBinding
import nl.bryanderidder.themedtogglebuttongroup.SelectAnimation
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup
import java.util.*
import java.util.regex.Pattern
import javax.inject.Inject

class Body : AppCompatActivity() {
    lateinit var binding: ActivityBodyBinding
    lateinit var bottomDialog: BtmDialog

    @Inject
    lateinit var vm: BodyActivityViewModel

    lateinit var bodyActivityViewModel: BodyActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBodyBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var appBodyComponent = DaggerAppBodyComponent.builder().bodyActivity(this).build()
        appBodyComponent.inject(this)
        //bottomDialog = BottomDialog(resources.getIntArray(R.array.text_colors).toList(), colorState, this)
        bottomDialog = BtmDialog()
        binding.edit.setColorFilter(resources.getColor(R.color.ui_back))
        binding.tick.setColorFilter(resources.getColor(R.color.ui_back))
        binding.back.setColorFilter(resources.getColor(R.color.ui_back))

        binding.edit.setOnClickListener {
            bottomDialog.show(supportFragmentManager, "")
        }

        //val strings = resources.getStringArray(R.array.font_sizes)
        //val arrayAdapter = ArrayAdapter(this, R.layout.drop_down_menu_item, strings)
        //binding.menu.setAdapter(arrayAdapter)
//
        //повторим анимации
        //ObjectAnimator.ofFloat(binding.textView, View.ROTATION_X,0f, 360f).apply {
        //    duration = 2000
        //    interpolator = AccelerateDecelerateInterpolator()
        //    repeatCount = 0//количество повторений после проигрывания первой анимации
        //    start()
        //}
//
        ////комбинация анимаций
        //val moveY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 100f, 0f)
        //val textAnim = PropertyValuesHolder.ofInt("textColor", Color.GREEN, Color.BLUE)
        //textAnim.setEvaluator(ArgbEvaluator())
        //val anim1 = ObjectAnimator.ofPropertyValuesHolder(binding.textView, moveY).apply {
        //    duration = 2000
        //    interpolator = AccelerateInterpolator()
        //    repeatCount = 0
        //}
        //binding.button2.setOnClickListener {
        //    val spanned = SpannableStringBuilder(binding.textView.text)
        //    spanned.setSpan(ForegroundColorSpan(Color.RED), binding.textView.selectionStart, binding.textView.selectionEnd, 0)
        //    //spanned.setSpan(ClickableSpan)
        //    binding.textView.setText(spanned)
        //    //задача подсветить ссылку цветом и сделать её кликабельной
        //}
        //val s1 = "You can find it on google.developers.com. Or you should explore android.ru!!! Would you like to use yandex.ru?! But angular.com is the best!"
        //binding.textView.setText(s1)
        //testStrings(s1)
        //Toast.makeText(applicationContext, "${binding.emailPhoneLayout.id}", Toast.LENGTH_SHORT).show()

    }

    fun testStrings(str: String){
        var myList = str.toString().split(" ")
        val pattern1 = Regex("[0-9A-Za-z.]{1,50}\\.ru[.!?]{0,3}")
        val pattern2 = Regex("[0-9A-Za-z.]{1,50}\\.com[.!?]{0,3}")
        var listOfLens = mutableMapOf<Int, Int>()
        var l = 0
        for(word in myList){
            if(word.matches(pattern1)){
                val substr = word.substringAfterLast('u')
                listOfLens[l] = word.length - substr.length
            }else if(word.matches(pattern2)){
                val substr = word.substringAfterLast('m')
                listOfLens[l] = word.length - substr.length
            }
            l += (word.length + 1)
        }
        applyRedColorOnLinks(listOfLens)
    }

    fun applyRedColorOnLinks(mapOfLinks: MutableMap<Int, Int>){
        //val spanned = SpannableStringBuilder(binding.textView.text)
        //for(i in mapOfLinks){
        //    spanned.setSpan(ForegroundColorSpan(Color.RED), i.key, i.key + i.value, 0)
        //    binding.textView.setText(spanned)
        //}
    }

    //функция применения font-family
    fun TextView.setFontFamily(fontStyleKey: Int){

    }
}

