class Chapter5 {
    // asParam is a higher order function because it accepts 
    // a function as a parameter
    fun asParam(someStr: String, fn: (String) -> String): Unit {
        val applied = fn(someStr)
        println(applied)
    }

    // asReturn is a higher order function becuase it returns 
    // a function 
    fun bar(): (String, String) -> (Boolean) = { str: String, contains: String -> str.contains(contains) }

}