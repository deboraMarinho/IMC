package com.example.debce.imc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var resultado: String
    var peso = 0.0
     var altura = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sMulher?.setOnClickListener {
            opcao()
        }

        sbIdade.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                txtIdade.text = ("Idade " + progress.toString() + " Anos")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

                txtIdade.text = ("Idade " + seekBar?.progress.toString() + " Anos")
            }
        })

        sbPeso.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                txtPeso.text = ("Peso " + progress.toString() + " Kg")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                peso = (seekBar!!.progress.toDouble())
                txtPeso.text = ("Peso " + seekBar?.progress.toString() + " Kg")
            }
        })

        sbAltura.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                txtAltura.text = ("Altura " + (progress.toDouble() / 100).toString() + " m")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

                altura = (seekBar!!.progress.toDouble())
               // txtAltura.text = "Altura:" + seekBar?.progress.toString()


                txtAltura.text = ("Altura " + (seekBar?.progress!!.toDouble() / 100).toString() + " m")
            }
        })

        radioGroup?.setOnCheckedChangeListener { group, checkedId ->
            resultado = ""
            when (checkedId) {
                R.id.rbSedentario -> resultado = "Fazer atividade Física"
                R.id.rbModerado -> resultado = "Continue assim"
                R.id.rbIntenso -> resultado = "Diminua a frequência"
            }
        }
    }


    fun opcao(){
        if (sMulher.isChecked){
            sMulher.text = "Mulher"
        } else{
            sMulher.text = "Homem"
        }
    }


    fun calcular (v: View){

        altura /= 100
        val imc = peso / (altura * altura)


        if (imc < 18.5){
            txtResposta.text = ("Abaixo do Peso.\nO seu IMC é " + String.format(Locale.US, "%.2f", imc) + "\n" + resultado.toString() )
            ratingBar.rating = 4F
        } else
            if (imc < 24.9){
                txtResposta.text = ("Saudavel.\nO seu IMC é " + String.format(Locale.US, "%.2f", imc) + "\n" + resultado.toString() )
                ratingBar.rating = 5F
            } else
                if (imc < 29.9){
                    txtResposta.text = ("Excesso de Peso.\nO seu IMC é " + String.format(Locale.US, "%.2f", imc) + "\n" + resultado.toString())
                    ratingBar.rating = 2F
                } else
                    if (imc > 30){
                        txtResposta.text = ("Obeso.\nO seu IMC é " + String.format(Locale.US, "%.2f", imc) + "\n" + resultado.toString())
                        ratingBar.rating = 1F
                    }
    }
}



