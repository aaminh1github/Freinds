package ran.am.receipeapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btSave = findViewById<View>(R.id.btSave) as Button

        val etTitle = findViewById<View>(R.id.etTitle) as EditText
        val etAuthor = findViewById<View>(R.id.etAuthor) as EditText
        val etTnge = findViewById<View>(R.id.etIngr) as EditText
        val etIns = findViewById<View>(R.id.etIns) as EditText

        btSave.setOnClickListener {
            var fill = RecipeDetails.Datum(etTitle.text.toString(), etAuthor.text.toString(),
                etTnge.text.toString(),    etIns.text.toString())

            addReceipe(fill, onResult = {
                etTitle.setText("")
                etAuthor.setText("")
                etTnge.setText("")
                etIns.setText("")
                Toast.makeText(applicationContext, "thank you for shearing!", Toast.LENGTH_SHORT).show()
            })
        }
    }

    fun addReceipe(userData: RecipeDetails.Datum, onResult: (RecipeDetails?) -> Unit) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        if (apiInterface != null) {
            apiInterface.addRecipie(userData).enqueue(object : Callback<RecipeDetails> {
                override fun onResponse(
                    call: Call<RecipeDetails>,
                    response: Response<RecipeDetails>
                ) {
                    onResult(response.body())
                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<RecipeDetails>, t: Throwable) {
                    onResult(null)
                    Toast.makeText(applicationContext, "Oops!", Toast.LENGTH_SHORT).show()
                    progressDialog.dismiss()

                }
            })
        }
    }

    fun viewreceipe(view: android.view.View) {
        intent = Intent(applicationContext, ViewRecipes::class.java)
        startActivity(intent)
    }
}