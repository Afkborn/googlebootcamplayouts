package com.bilgehankalay.layoutsbootcamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.bilgehankalay.layoutsbootcamp.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.hesaplaButton.setOnClickListener {
            bahsisHesapla()
        }
    }


    fun bahsisHesapla(){
        val hizmetBedeliString = binding.hizmetBedeliEditText.text.toString()

        val tutar = hizmetBedeliString.toDoubleOrNull()

        if (tutar == null){
            Toast.makeText(this,"Hizmet bedeli girin!",Toast.LENGTH_LONG).show()
            binding.bahsisTutariTextView.text = ""
            return
        }
        val secilenRadioButton = binding.bahsisSecenekleriRadioGroup.checkedRadioButtonId

        val bahsisYuzdesi = when(secilenRadioButton) {
            R.id.yuzdeYirmiRadioButton -> 0.20
            R.id.yuzdeOnSekizRadioButton -> 0.18
            else -> 0.15
        }
        var bahsis = bahsisYuzdesi * tutar

        val yukariYuvarla = binding.bahsisYuvarlaSwitch.isChecked
        if (yukariYuvarla){
            bahsis = kotlin.math.ceil(bahsis)

        }
        val formatlananBahsis = NumberFormat.getCurrencyInstance(Locale("tr","TR")).format(bahsis)

        binding.bahsisTutariTextView.text = getString(R.string.bahsis_tutari,formatlananBahsis)


    }
}