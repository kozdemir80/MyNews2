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
        val preferences=getSharedPreferences("Search Items", MODE_PRIVATE)
        val editor=preferences.edit()

        binding.queryTerm.addTextChangedListener (object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                binding.searchQueryButton.isEnabled = false
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                val queryTerm=binding.queryTerm.text.toString()

                editor.apply {
                    putString("myQuery",queryTerm)
                }.apply()

            }
            override fun afterTextChanged(p0: Editable?) {

            }
        })
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

        binding.checkboxArts.setOnClickListener {

            it.tag = "arts"
            if (binding.checkboxArts.isChecked) {
                binding.checkboxArts.isChecked = true
                binding.searchQueryButton.isEnabled = true
                categories.add("arts")


            } else {
                binding.checkboxArts.isChecked = false
                binding.searchQueryButton.isEnabled = false
                categories.remove("arts")
            }
        }

        binding.checkboxBusiness.setOnClickListener {
            it.tag = "business"
            if (binding.checkboxBusiness.isChecked){
                binding.checkboxBusiness.isChecked = true
                binding.searchQueryButton.isEnabled = true
                categories.add("business")
            }else{
                binding.checkboxBusiness.isChecked = false
                binding.searchQueryButton.isEnabled = false
                categories.remove("business")
            }
        }
        binding.checkboxEntrepreneurs.setOnClickListener {

            it.tag = "entrepreneurs"
            if (binding.checkboxEntrepreneurs.isChecked){
                binding.checkboxEntrepreneurs.isChecked = true
                binding.searchQueryButton.isEnabled = true
                categories.add("entrepreneurs")
            }else{
                binding.checkboxEntrepreneurs.isChecked = false
                binding.searchQueryButton.isEnabled = false
                categories.remove("entrepreneurs")
            }
        }
        binding.checkboxPolitics.setOnClickListener {

            it.tag = "politics"
            if (binding.checkboxPolitics.isChecked){
                binding.checkboxPolitics.isChecked = true
                binding.searchQueryButton.isEnabled = true
                categories.add("politics")
            }else{
                binding.checkboxPolitics.isChecked = false
                binding.searchQueryButton.isEnabled = false
                categories.remove("politics")
            }
        }
        binding.checkboxSports.setOnClickListener {

            it.tag = "sports"
            if (binding.checkboxSports.isChecked){
                binding.checkboxSports.isChecked = true
                binding.searchQueryButton.isEnabled = true
                categories.add("sports")
            }else{
                binding.checkboxSports.isChecked = false
                binding.searchQueryButton.isEnabled = false
                categories.remove("sports")
            }
        }
        binding.checkboxTravel.setOnClickListener {

            it.tag = "travel"
            if (binding.checkboxTravel.isChecked) {
                binding.checkboxTravel.isChecked = true
                binding.searchQueryButton.isEnabled = true

                categories.add("travel")
            } else {
                binding.checkboxBusiness.isChecked = false
                binding.searchQueryButton.isEnabled = false
                categories.remove("travel")

            }
        }

       binding.searchQueryButton.setOnClickListener {

            val intent=Intent(this,Search_Result::class.java)
                 intent.putExtra("myCategories",categories.toString())
            startActivity(intent)
        }
    }
 }









