package com.example.testproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testproject.databinding.ActivityRegisterBinding


class registerActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var binding: ActivityRegisterBinding

    private var countries = arrayOf("Nepal", "China", "USA", "Korea", "Cabada");
    val cities = arrayOf("Kathmandu", "illam", "chitawan", "pokhara", "janakpur")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // autocomplete adapter setup
        val autoCompleteAdapter = ArrayAdapter(
            this@registerActivity, android.R.layout.simple_dropdown_item_1line, cities
        )
        binding.autocomplete.setAdapter(autoCompleteAdapter)
        binding.autocomplete.threshold = 1;


        //  spinner setup adapter
        val adapter = ArrayAdapter(
            this@registerActivity, android.R.layout.simple_spinner_dropdown_item, countries
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.dropdown.onItemSelectedListener = this;
        binding.dropdown.adapter = adapter;


        // event listeners
        binding.submitBtn.setOnClickListener {

            if (binding.checkBox.isChecked) {

                // intent to move to the destination file
                val intent = Intent(this@registerActivity, Destination::class.java)

                // selected option.
                val selectedOption: Int = binding.radioGrp.checkedRadioButtonId

                val fullName: String = binding.fname.text.toString()
                val email: String = binding.eml.text.toString()
                val cities: String = binding.autocomplete.text.toString()
                val country: String = binding.dropdown.selectedItem.toString()
                val selectedRadioButton: RadioButton = binding.radioGrp.findViewById(selectedOption)
                val genderText: String = selectedRadioButton.text.toString()

                intent.putExtra("fullName", fullName)
                intent.putExtra("email", email)
                intent.putExtra("gender", genderText)
                intent.putExtra("city", cities)
                intent.putExtra("country", country)
                startActivity(intent);
                finish()
            } else {
                binding.checkBox.error = "Accept the TOS to continue."
            }

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }

    override fun onItemSelected(
        p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long
    ) {
//        displayText.text = p0?.getItemAtPosition(p2).toString();
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}