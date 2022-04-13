package com.example.mynews2.View.Fragments





import android.content.Intent


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher




import androidx.appcompat.app.AppCompatActivity
import com.example.mynews2.viewModelTests.Search_Result


import com.example.mynews2.R


import com.example.mynews2.databinding.SearchItemsBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Search_Activity:AppCompatActivity() {

    private lateinit var binding:SearchItemsBinding

    var categories:ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_items)
        binding = SearchItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)





        val preferences=getSharedPreferences("Search Items", MODE_PRIVATE)
        val editor=preferences.edit()

        binding.queryTerm.addTextChangedListener (object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

              binding.searchQueryButton.setEnabled(false)
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                val queryTerm=binding.queryTerm.getText().toString()

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


            binding.beginDate.setText(date.toString())
            val bData=binding.beginDate.getText().toString()
            editor.apply{
                putString("beginD",bData)
            }.apply()

        }
        binding.endDate.hint="End Date"
        binding.endDate.setOnClickListener {
            endDateRangePicker.show(supportFragmentManager,endDateRangePicker.toString())

        }
        endDateRangePicker.addOnPositiveButtonClickListener() {
            val sdf = SimpleDateFormat("yyyyMMdd ", Locale.getDefault())
            val date = sdf.format(it)
            binding.endDate.setText(date.toString())
            val eDate = binding.endDate.getText().toString()
            editor.apply {
                putString("endD", eDate)
            }.apply()
        }

        binding.checkboxArts.setOnClickListener {

            it.tag = "arts"
            if (binding.checkboxArts.isChecked) {
                binding.checkboxArts.setChecked(true)
                binding.searchQueryButton.setEnabled(true)
                categories.add("arts")


            } else {
                binding.checkboxArts.setChecked(false)
                binding.searchQueryButton.setEnabled(false)
                categories.remove("arts")
            }
        }

        binding.checkboxBusiness.setOnClickListener {
            it.tag = "business"
            if (binding.checkboxBusiness.isChecked){
                binding.checkboxBusiness.setChecked(true)
                binding.searchQueryButton.setEnabled(true)
                categories.add("business")
            }else{
                binding.checkboxBusiness.setChecked(false)
                binding.searchQueryButton.setEnabled(false)
                categories.remove("business")
            }
        }
        binding.checkboxEntrepreneurs.setOnClickListener {

            it.tag = "entrepreneurs"
            if (binding.checkboxEntrepreneurs.isChecked){
                binding.checkboxEntrepreneurs.setChecked(true)
                binding.searchQueryButton.setEnabled(true)
                categories.add("entrepreneurs")
            }else{
                binding.checkboxEntrepreneurs.setChecked(false)
                binding.searchQueryButton.setEnabled(false)
                categories.remove("entrepreneurs")
            }
        }
        binding.checkboxPolitics.setOnClickListener {

            it.tag = "politics"
            if (binding.checkboxPolitics.isChecked){
                binding.checkboxPolitics.setChecked(true)
                binding.searchQueryButton.setEnabled(true)
                categories.add("politics")
            }else{
                binding.checkboxPolitics.setChecked(false)
                binding.searchQueryButton.setEnabled(false)
                categories.remove("politics")
            }
        }
        binding.checkboxSports.setOnClickListener {

            it.tag = "sports"
            if (binding.checkboxSports.isChecked){
                binding.checkboxSports.setChecked(true)
                binding.searchQueryButton.setEnabled(true)
                categories.add("sports")
            }else{
                binding.checkboxSports.setChecked(false)
                binding.searchQueryButton.setEnabled(false)
                categories.remove("sports")
            }
        }
        binding.checkboxTravel.setOnClickListener {

            it.tag = "travel"
            if (binding.checkboxTravel.isChecked) {
                binding.checkboxTravel.setChecked(true)
                binding.searchQueryButton.setEnabled(true)

                categories.add("travel")
            } else {
                binding.checkboxBusiness.setChecked(false)
                binding.searchQueryButton.setEnabled(false)
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









