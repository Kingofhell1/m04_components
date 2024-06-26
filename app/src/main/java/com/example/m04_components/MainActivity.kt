package com.example.m04_components

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.example.m04_components.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var isNameExist = false
    private var isPhoneExist = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val points = Random.nextInt(101)
        binding.progrBar.progress = points
        binding.txtProgrBarPercent.text = "$points/100"

        val txtInputName = binding.txtInputName
        val txtInputPhone = binding.txtInputPhone
        val switcher = binding.switchNotifications
        val chbxAutorization = binding.chbxNotifyAuthorization
        val chbxNews = binding.chbxNotifyNews
        val btnSave = binding.btnSave

        txtInputName.doOnTextChanged { text, _, _, _ ->
            if (text != null) {
                if (text.length > 40) {
                    txtInputName.setText(text.toString().substring(0, 40))
                    txtInputName.setSelection(40)
                }
                if (text.isNotEmpty())
                    isNameExist = true
                else
                    isNameExist = false
            } else isNameExist = false
            checkForm()
        }


        txtInputPhone.doOnTextChanged { text, _, _, _ ->
            if(text != null){
                if (text.isNotEmpty())
                    isPhoneExist = true
                else
                    isPhoneExist = false
            } else isPhoneExist = false
            checkForm()
        }

        switcher.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                chbxAutorization.isEnabled = true
                chbxNews.isEnabled = true
            }
            else {
                chbxAutorization.isEnabled = false
                chbxNews.isEnabled = false
            }
        }

        btnSave.setOnClickListener {
            Toast.makeText(this, getString(R.string.save), Toast.LENGTH_LONG).show()
        }


    }

    private fun checkForm() {
        if (isNameExist && isPhoneExist)
            findViewById<Button>(R.id.btnSave).isEnabled = true
        else
            findViewById<Button>(R.id.btnSave).isEnabled = false
    }

}






