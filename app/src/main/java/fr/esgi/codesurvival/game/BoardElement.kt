package fr.esgi.codesurvival.game
/*
enum class BoardElement(val value: String){
    GRASS("vvv"), //R.drawable.bushes
    PIT("___"), // R.drawable.pit
    FRUIT("vov"), // R.drawable.berries_bushes
    END("///") // wall

}
*/
data class BoardElement(val position: Position,var contain: List<String>, var type: Type){

    enum class Type(val value: String){
        GRASS("vvv"), //R.drawable.bushes
        PIT("___"), // R.drawable.pit
        FRUIT("vov"), // R.drawable.berries_bushes
        END("///") // wall
    }

}



/*
{ "coordinates": { "x": 2, "y": 16 },
          "containt": [
            "MEAL"
          ],
          "type": "GRASS" },
 */