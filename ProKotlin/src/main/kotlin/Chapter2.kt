class Chapter2(val address: String) {
    inner class Inner() {
        // We have to refer to the outer class, as this refers to the closest
        // containing class, which would be "Inner"
        fun printAddress() = println(this@Chapter2.address)
    }
}