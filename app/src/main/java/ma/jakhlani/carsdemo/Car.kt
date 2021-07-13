package ma.jakhlani.carsdemo


/***
 *
 * No thing special here a simple data class presents a data structure of our object car
 * we use it to deserialize data received from our api.
 */

data class Car(
    var model:String,
    var id: Int,
    var brand: String,
    var constractionYear: String,
    var isUsed: Boolean,
    var imageUrl: String
)
