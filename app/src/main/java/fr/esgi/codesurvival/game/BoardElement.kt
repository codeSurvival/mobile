package fr.esgi.codesurvival.game

enum class BoardElement(val value: String){
    GRASS("vvv"), //R.drawable.bushes
    PIT("___"), // R.drawable.pit
    FRUIT("vov"), // R.drawable.berries_bushes
    END("///") // wall
}
