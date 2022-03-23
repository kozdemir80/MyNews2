package com.example.mynews2.View.Fragments





import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log


import android.view.View


import androidx.appcompat.app.AppCompatActivity
import com.example.mynews2.Controller.Search_Result


import com.example.mynews2.R


import com.example.mynews2.databinding.SearchItemsBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*



 class Search_Activity:AppCompatActivity(), View.OnClickListener {

    private lateinit var binding:SearchItemsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_items)
        binding = SearchItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)



       val preferences=getSharedPreferences("myPreferences", MODE_PRIVATE)
        val editor=preferences.edit()

        binding.queryTerm.addTextChangedListener (object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.searchQueryButton.isEnabled=true
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

        binding.searchQueryButton.setOnClickListener {
            val intent=Intent(this,Search_Result::class.java)
            startActivity(intent)
        }



    }

     override fun onClick(p0: View?) {
         val preferences=getSharedPreferences("myPreferences", MODE_PRIVATE)
         val editor=preferences.edit()
         binding.checkboxArts.let {
             it.setOnClickListener(this)
             it.tag = "arts"
             if (binding.checkboxArts.isChecked){
                 binding.checkboxArts.setChecked(true)
                 binding.searchQueryButton.isEnabled=true

                 editor.putBoolean("mArts",true)
                 editor.apply()
             }else{
                 binding.checkboxArts.setChecked(false)
                 editor.putBoolean("mArts",false)
                 editor.apply()
             }

         }
         binding.checkboxBusiness.let {
             it.setOnClickListener(this)
             it.tag = "business"
             if (binding.checkboxBusiness.isChecked){
                 binding.checkboxBusiness.setChecked(true)
                 binding.searchQueryButton.isEnabled=true
                 editor.putBoolean("mBussiness",true)
                 editor.apply()
             }else{
                 binding.checkboxBusiness.setChecked(false)
                 editor.putBoolean("mBussiness",false)
                 editor.apply()
             }
         }
         binding.checkboxEntrepreneurs.let {
             it.setOnClickListener(this)
             it.tag = "entrepreneurs"
             if (binding.checkboxEntrepreneurs.isChecked){
                 binding.checkboxEntrepreneurs.setChecked(true)
                 binding.searchQueryButton.isEnabled=true
                 editor.putBoolean("mEntre",true)
                 editor.apply()
             }else{
                 binding.checkboxEntrepreneurs.setChecked(false)
                 editor.putBoolean("mEntre",false)
                 editor.apply()
             }
         }
         binding.checkboxPolitics.let {
             it.setOnClickListener(this)
             it.tag = "politics"
             if (binding.checkboxPolitics.isChecked){
                 binding.checkboxPolitics.setChecked(true)
                 binding.searchQueryButton.isEnabled=true
                 editor.putBoolean("mPolitics",true)
                 editor.apply()
             }else{
                 binding.checkboxPolitics.setChecked(false)
                 editor.putBoolean("mPolitics",false)
                 editor.apply()
             }
         }
         binding.checkboxSports.let {
             it.setOnClickListener(this)
             it.tag = "sports"
             if (binding.checkboxSports.isChecked){
                 binding.checkboxSports.setChecked(true)
                 binding.searchQueryButton.isEnabled=true
                 editor.putBoolean("mSports",true)
                 editor.apply()
             }else{
                 binding.checkboxSports.setChecked(false)
                 editor.putBoolean("mSports",false)
                 editor.apply()
             }
         }
         binding.checkboxTravel.let {
             it.setOnClickListener(this)
             it.tag = "travel"
             if (binding.checkboxTravel.isChecked){
                 binding.checkboxTravel.setChecked(true)
                 binding.searchQueryButton.isEnabled=true
                 editor.putBoolean("mTravel",true)
                 editor.apply()
             }else{
                 binding.checkboxBusiness.setChecked(false)
                 editor.putBoolean("mTravel",false)
                 editor.apply()

             }
         }
     }


 }






