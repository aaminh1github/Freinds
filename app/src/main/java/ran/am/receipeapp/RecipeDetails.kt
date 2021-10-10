package ran.am.receipeapp

import com.google.gson.annotations.SerializedName

class RecipeDetails {

    var data: List<Datum>? = null

    class Datum {

        @SerializedName("pk")
        var id: Int? = null


        @SerializedName("title")
        var title: String? = null

        @SerializedName("author")
        var author: String? = null

        constructor(title: String?, author: String?, ingredients: String?, instructions: String?) {
            this.title = title
            this.author = author
            this.ingredients = ingredients
            this.instructions = instructions
        }

        @SerializedName("ingredients")
        var ingredients: String? = null

        @SerializedName("instructions")
        var instructions: String? = null
    }
}