class Rocket() {
    var lat: Double = 0.0
    var long: Double = 0.0

    fun explode() {
        println("Boom")
    }

    fun setCourse(lat: Double, long: Double) {
        require(lat.isValid())
        require(long.isValid())
        this.lat = lat
        this.long = long
    }

    fun Double.isValid() = Math.abs(this) <= 180
}