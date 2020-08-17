package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    /*Creo un dado de seis caras nada más empezar el código. Si esto lo metiese dentro de la función rollDice(),
    * cada vez que toco el botón se crearía un dado nuevo que se almacenaría en memoria hasta que el JGC se lo carga.
    * Pero al fin y al cabo, es información obsoleta ocupando memoria. Mejor crear solo 1 al principio.*/
    val dice = Dice(6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Para trabajar con el botón, me tengo que traer su referencia del "id". Esa referencia me la guardo en una
        * variable. Luego, a esa variable le aplico un método que se queda a la espera de que alguien haga click o tap.
        * Cuando eso ocurre, se ejecuta la funcion de su interior, "rollDice()"*/
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { rollDice() }

        //muestro una vez el dado al principio
        val firstDiceImage: ImageView = findViewById(R.id.imageView)
        firstDiceImage.setImageResource(R.drawable.dice_1)
    }

    /*La funcion ejecuta el método roll() de la clase Dice sobre el dado creado. Ese resultado lo guardo en una variable.
    * Luego me traigo el texto donde quiero que se muestre el resultado, referenciando su id. Por último, le digo que el
    * texto de esa referencia cambie al valor de la variable, convirtiendola en un String (antes era un int)*/
    private fun rollDice() {
        val diceRoll = dice.roll()

        //traigo la referencia de la imagen del dado
        val diceImage: ImageView = findViewById(R.id.imageView)

        //Hago un "case" para saber qué imagen mostrar dependiendo del número obtenido
        val drawableResult = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        //muestro la imagen
        diceImage.setImageResource(drawableResult)
        //Añado descripción de la imagen una vez que ya se el resultado
        diceImage.contentDescription = diceRoll.toString()

        //hacemos que el método escriba el número que ha salido
        val contentAfterImage = "It rolled a $diceRoll"
        val textAfterImage: TextView = findViewById(R.id.textResult)
        textAfterImage.text = contentAfterImage.toString()


    }
}

/*Clase Dice con la que creo el dado al principio y que tiene el método roll() que vamos a utilizar después.*/
class Dice(private val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }
}