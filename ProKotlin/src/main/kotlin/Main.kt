import java.util.*

fun main(args: Array<String>) {
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    // Chapter 1
    val range = 1..10

    // Chapter 2
    val chapter2 = Chapter2("Some string")
    chapter2.Inner().printAddress();

    /* control flow as expressions
     In Kotlin, the if...else and try..catch control flow blocks are expressions. 
     This means the result can be directly assigned to a value, returned from a function, 
     or passed as an argument to another function:
    * */
    val today = if (Date().year == 2016) true else false

    /* Null syntax
    Kotlin requires that a variable that can assigned to null be declared with a ?: */
    var str: Any? = null

    // Smart cast: the compiler remembers the type check and implicitly casts for us:
    if (str is String) {
        // The compiler knows that we can only be inside this block if str is indeed of type String
        // so the cast is done for us
        str = "some string";
        println(str.length)
    }

    if (today) {
        str = "initialized some times"
    }

    // safe cast operator: will try to cast, and if not, it will NOT throw ClassCastException,
    // but return null
    var mightBeNull: String? = str as? String;

    // When expressions
    fun isSingleDigit(x: Int): Boolean {
        return when (x) {
            in -9..9 -> true
            else -> false
        }
    }

    /* Type hierarchy
    * In Kotlin, the uppermost type is called Any. This is analogous to Java's object type.
    * The Any type defines the well-known toString, hashCode, and equals methods.
    * It also defines the extension methods apply, let, and to, among others.
    *
    * The Unit type is the equivalent of void in Java. Having a Unit type is common in a
    * functional programming language, and the distinction between void and Unit is subtle.
    * Void is not a type, but a special edge case that is used to indicate to the compiler
    * that a function returns no value. Unit is a proper type, with a singleton instance,
    * also referred to as Unit or (). When a function is defined as a returning Unit, then
    * it will return the singleton unit instance
    * */

    /* Chapter 3
    *  Section 6 Abstract or interface
    * Is-a versus Can-Do: Any type can inherit from one parent class only and multiple interfaces.
    * If for the derived class B you can't say B Is-an A (A is the base type), don't use an interface
    * but rather an interface. Interfaces imply a Can-Do relationship. If the Can-do functionality is
    *  applicable to different object types, go with an interface implementation. For example, for both
    * FileOutputStream and ByteOutputpuStream (and any of the other sibling implementations available),
    * you can say they have an Is-a relationship with java.io.OutputStream. Hence you will see that
    * OutputStream is an abstract class providing common implementations to all objects that represent
    * a writable stream. However, Autocloseable, which represents an object holding a resource that can
    * be released when the close method is invoked, provides a Can-do functionality and thus it makes sense
    * to have it as an interface.*/

    /* Section 8  In Kotlin, you would have to tag the function as being opened to redefine it. To do so, you need to
    add the open keyword as a prefix to the method definition, and when you redefine the method, you
    specifically have to mark it using the override keyword*/

}