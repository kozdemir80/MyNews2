package com.example.mynews2.Controller
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.mynews2.R
import com.example.mynews2.databinding.SearchItemsBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
class Search_Activity:AppCompatActivity() {
 private lateinit var binding:SearchItemsBinding
 private var categories:ArrayList<String> = ArrayList()
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  setContentView(R.layout.search_items)
  binding = SearchItemsBinding.inflate(layoutInflater)
  setContentView(binding.root)
  onViewClicked()
  val preferences=getSharedPreferences("Search Items", MODE_PRIVATE)
  val editor=preferences.edit()

  //  date picker dialog for begin and endDate
  val dateRangePicker= MaterialDatePicker.Builder
   .datePicker()
   .setTitleText("Select Date")
   .build()
  val endDateRangePicker= MaterialDatePicker.Builder
   .datePicker()
   .setTitleText("Select Date")
   .build()
  binding.beginDate.hint = "Begin Date"
  binding.beginDate.setOnClickListener {
   dateRangePicker.show(supportFragmentManager, dateRangePicker.toString())
  }
  dateRangePicker.addOnPositiveButtonClickListener {
   val sdf = SimpleDateFormat("yyyyMMdd ", Locale.US)
   val date=sdf.format(it)

   binding.beginDate.text = date.toString()
   val bData=binding.beginDate.text.toString()
   editor.apply{
    putString("beginD",bData)
   }.apply()

  }
  binding.endDate.hint="End Date"
  binding.endDate.setOnClickListener {
   endDateRangePicker.show(supportFragmentManager,endDateRangePicker.toString())

  }
  endDateRangePicker.addOnPositiveButtonClickListener {
   val sdf = SimpleDateFormat("yyyyMMdd ", Locale.getDefault())
   val date = sdf.format(it)
   binding.endDate.text = date.toString()
   val eDate = binding.endDate.text.toString()
   editor.apply {
    putString("endD", eDate)
   }.apply()
  }

 }
 //checkBoxes and queryTerms for news categories
 private fun onViewClicked(){
  val preferences=getSharedPreferences("Search Items", MODE_PRIVATE)
  val editor=preferences.edit()
  binding.checkboxArts.setOnClickListener {
   it.tag = "arts"
   if (binding.checkboxArts.isChecked) {
    categories.add("arts")
   } else {
    categories.remove("arts")
   }
  }

  binding.checkboxBusiness.setOnClickListener {
   it.tag = "business"
   if (binding.checkboxBusiness.isChecked){
    categories.add("business")
   }else{
    categories.remove("business")
   }
  }
  binding.checkboxEntrepreneurs.setOnClickListener {

   it.tag = "entrepreneurs"
   if (binding.checkboxEntrepreneurs.isChecked){
    categories.add("entrepreneurs").toString()
   }else{
    categories.remove("entrepreneurs")
   }
  }
  binding.checkboxPolitics.setOnClickListener {

   it.tag = "politics"
   if (binding.checkboxPolitics.isChecked){
    categories.add("politics")
   }else{
    categories.remove("politics")
   }
  }
  binding.checkboxSports.setOnClickListener {

   it.tag = "sports"
   if (binding.checkboxSports.isChecked){
    categories.add("sports")
   }else{
    categories.remove("sports")
   }
  }
  binding.checkboxTravel.setOnClickListener {
   it.tag = "travel"
   if (binding.checkboxTravel.isChecked) {
    categories.add("travel")
   } else {
    categories.remove("travel")
   }
  }
  binding.queryTerm.addTextChangedListener (object :TextWatcher{
   override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
   }
   override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    activateButton()
    val queryTerm=binding.queryTerm.text.toString()

    editor.apply {
     putString("myQuery", queryTerm)
    }.apply()

   }
   override fun afterTextChanged(p0: Editable?) {
   }
  })
  binding.searchQueryButton.setOnClickListener {

   val intent = Intent(this, Search_Result::class.java)
   intent.putExtra("myCategories", arrayListOf(categories))
   startActivity(intent)
  }
 }
 private fun activateButton(){
  binding.searchQueryButton.isEnabled = binding.queryTerm.text.isNotEmpty()
          && binding.queryTerm.text.isNotBlank() && categories.isNotEmpty()
 }
 }