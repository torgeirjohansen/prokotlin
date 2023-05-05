import java.util.*
import java.util.concurrent.ConcurrentHashMap

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

    /* Chapter 4.4
    * Kotlin allows us to take this a step further by supporting functions declared inside other functions.
    * These are called local or nested functions. Functions can even be nested multiple times.*/

    /* Chapter 4.5
    * In addition to member functions and local functions, Kotlin also supports declaring top-level functions.
    * These are functions that exist outside of any class, object, or interface and are defined directly inside
    *  a file. The name top-level comes from the fact that functions are not nested inside any structure and
    * so they are at the top of the hierarchy of classes and functions*/

    // Chapter 4.10 Function literals
    val printHello = { message: String -> println("message is: $message") }
    printHello("some message")

    // Chapter 4.13 Kotlin Standard library
    // Apply, let, repeat:
    repeat(3, { println("Hello") })

    // Chapter 4.15
    /* In functional programming, the concept of a pure function is one that holds the following two properties:
    //
    * The function should always return the same output for the same input
    * The function should not create any side effects

    The advantages of pure functions are several. The results of the functions can be cached, which is advantageous
    for slow functions. Pure functions can be easily parallelized since they don't write anything to a shared state.
    They can be tested in isolation since they depend on nothing but their input instances.*/


    /*
    * Higher order functions
    * A higher order function is simply a function that either accepts another function as a parameter,
    * returns a function as its return value, or both.
    *
    * With an imperative approach, a developer writes code that specifies the steps that the computer must take
    * to accomplish the goal. This is sometimes referred to as algorithmic programming. In contrast, a functional
    * approach involves composing the problem as a set of functions to be executed.
    *
    *
    *
    * */
    val chapter5 = Chapter5()
    chapter5.asParam("Hello") { it.reversed() }

    val f1 = chapter5.bar();
    println("f1: $f1")
    println("f1.invoke(\"1234\", \"1\"): ${f1.invoke("1234", "1")}")
    println("f1(\"1234\", \"1\"): ${f1("1234", "1")}")

    /* Chapter 5.2 closures
    * In functional programming, a closure is a function that has access to variables and parameters defined in outer scopes.
    *  It is said that they "close over" these variables, hence the name closure.
    * */

    // Extension methods
    fun Int.isOdd(): Boolean = this % 2 != 0
    println("4.isOdd: ${4.isOdd()}")

    /*
     * Chapter 5.8
     * As we have seen from earlier sections, functions are instances of objects, and, of course, each instance requires an
     * allocation in the heap. There are also method invocations required when invoking the function. Overall,
     * using functions introduces an overhead.

     * Kotlin allows us to avoid this overhead by use of the inline keyword. This keyword indicates to the compiler that the
     * function marked as inline, as well as function parameters, should be expanded and generated inline at the call site,
     * hence the name.
     * */
    fun <T : AutoCloseable, U> withResource(resource: T, fn: (T) -> U): U {
        try {
            return fn(resource)
        } finally {
            resource.close()
        }
    }

    /*
    * A common technique in functional programming is the concept of currying. Currying is the process of transforming a function
    * that accepts multiple parameters into a series of functions, each of which accept a single function
    * */

    /*
    * Memoization is a technique for speeding up function calls by caching and reusing the output instead of recomputing for a given
    * set of inputs. This technique offers a trade-off between memory and speed. The typical applications are for computationally
    * expensive functions or for recursive functions, which branch out calling the recursive function many times with the same values,
    * such as Fibonacci.
    * */
    fun <A, R> memoize(fn: (A) -> R): (A) -> R {
        val map = ConcurrentHashMap<A, R>()
        return { a ->
            map.getOrPut(a) {
                fn(a)
            }
        }
    }

    // usage:  val memquery = memoize(::query) 


    /*
    * Chapter 13
    *
    * Concurrency is a general term that means two tasks are active and making progress at the same time, whereas parallelism is a
    * stricter term that means two tasks are both executing at a particular instant. The two are often used interchangeably, but
    * true parallelism is the goal of concurrent programming.
    *
    * To avoid these issues, we must perform an interrupt on the thread. An interrupt is a way of forcing a thread that is currently
    * blocked to wake up and continue. It literally interrupts the thread. When this happens, the blocking function will throw an
    * exception InterruptedException, which must be handled. InterruptedException is your way of knowing that the thread was interrupted.
    *
    * The concept is important when deciding how to split up executions into threads. Let's say we had a thread pool of eight threads and we
    * allocated this pool to both our CPU- and I/O-bounded computations.
    *
    * If this is the case, then it is possible we could have a situation where we could have all the eight threads blocked on a slow network
    * to deliver bytes while the the Pi calculation would make no progress despite the CPU being idle.
    *
    * A common solution to this is to have two thread pools. Have one for CPU-bound operations, which might have its size limited to
    * the number of CPU cores. And have another pool for IO-bound operations, which would typically be larger since these threads would
    * often be in the blocked state, waiting on data
    * */
}