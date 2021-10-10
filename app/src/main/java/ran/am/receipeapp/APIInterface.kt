package ran.am.receipeapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET("/recipes/")
    fun getRecipies(): Call<List<RecipeDetails.Datum>>


    @Headers("Content-Type: application/json")
    @POST("/recipes/")
    fun addRecipie(@Body userData: RecipeDetails.Datum): Call<RecipeDetails>


}