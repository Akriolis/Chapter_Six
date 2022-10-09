import java.awt.LinearGradientPaint
import java.awt.event.ActionEvent
import java.io.BufferedReader
import java.io.StringReader
import javax.swing.*

/**
 * The Kotlin type system
 */

/**
 * Nullability
 *
 * Type? = Type or null
 *
 * ?. safe call operator
 * ?: Elvis operator - null-coalescing operator
 *
 * as? - safe-cast operator
 *
 * !! - not-null assertions
 *
 * let function
 *
 */

fun strLen (s: String) = s.length

fun strLen2 (s: String?) = s?.length

fun strLen3 (s: String?): Int =
    if (s != null) s.length else 0

fun strLen4 (s: String?): Int = s?.length ?: 0

fun foo (s: String?) = s ?: "Unknown"

fun ignoreNulls (s: String?){
    val sNotNull: String = s!!
    println(sNotNull.length)
}

class CopyRowAction (val list: JList<String>): AbstractAction(){
    override fun isEnabled(): Boolean =
        list.selectedValue != null

    override fun actionPerformed(e: ActionEvent?) {
        val value = list.selectedValue!!
    }
}

fun sendEmailTo(email: String){
    println("Sending email to $email")
}

/**
 * Late-initialized properties
 * lateinit modifier
 */

/**
 * Extensions for nullable types
 */

fun verifyUserInput(input: String?){
    if (input.isNullOrBlank()){
        println("Please fill in the required fields")
    }
}

/* fun String?.isNullOrBlank(): Boolean =
    this == null || this.isBlank() */

/**
 * Nullability of type parameters
 */

fun <T> printHashCode(t: T){
    println(t?.hashCode())
}

fun <T> singletonList(item: T): List<T>{
    val myList = mutableListOf<T>()
    myList.add(item)
    return myList
}

fun <T: Any> printHashCode2(t: T){
    println(t.hashCode())
}

/**
 * Nullability and Java
 */

// Kotlin compiler can denote platform types with the next notation -
// String! Int! Char! etc.
// it's just pointing out that nullability of the type is unknown
// (this type can be nullable or not-nullable)

/**
 * Primitive and other basic types
 *
 * nullable primitive types
 *
 */

fun showProgress(progress: Int){
    val percent = progress.coerceIn(0,100)
    println("We're ${percent}% done")
}

/**
 * Number conversion
 */

/**
 * Any and Any? - the root types
 *
 * Any type is the supertype of all non-nullable types in Kotlin
 */

/**
 * Unit type have a single value
 */

/**
 * The Nothing type
 * tells that the function will never terminate normally
 * doesn't have any values
 */

fun fail(message: String): Nothing{
    throw IllegalStateException(message)
}

/**
 * Nullability and collections
 */

fun readNumbers (reader: BufferedReader): List<Int?>{
    val result = ArrayList<Int?>()
    for (line in reader.lineSequence()){
        try {
            val number = line.toInt()
            result.add(number)
        }
        catch (e: NumberFormatException){
            result.add(null)
        }
    }
    return result
}

// List<Int?> - values are nullable within the list
// List<Int>? - entire list is nullable (collection itself)
// List<Int?>? - nullable list if nullable numbers

fun addValidNumbers (numbers: List<Int?>){
    var sumOfValidNumbers = 0
    var invalidNumbers = 0
    for (number in numbers){
        if (number != null){
            sumOfValidNumbers += number
        } else{
            invalidNumbers++
        }
    }
    println("Sum of valid numbers: $sumOfValidNumbers")
    println("Invalid numbers: $invalidNumbers")
}

fun addValidNumbers2(numbers: List<Int?>){
    val validNumbers = numbers.filterNotNull()
    println("Sum of valid numbers: ${validNumbers.sum()}")
    println("Invalid numbers: ${numbers.size - validNumbers.size}")
}

/**
 * Read-only and mutable collections
 */

// MutableCollection extends Collection and adds methods to modify a collection's contents

// as a general rule, you should use read-only interfaces and variables everywhere in your code

fun <T> copyElements (source: Collection<T>,
                      target: MutableCollection<T>){
    for (item in source){
        target.add(item)
    }
}

fun printInUppercase(list: List<String>){
    println(CollectionUtils.uppercaseAll(list))
    println(list.first())
}

/**
 * Arrays of objects and primitive types
 *
 * The arrayOf creates an array containing the elements specified as arguments to this function
 *
 * The arraysOfNull creates an array of given size containing bull elements
 *
 * The Array constructor takes the size of the array and a lambda,
 * and initializes each array element by calling the lambda
 *
 */

fun main(){
    val x = strLen4("Pokemon")

    println(strLen4(null))

    val ceo = Employee("Da Boss", null)
    val developer = Employee ("Bob Smith", ceo)

    println(managerName(developer))
    println(managerName(ceo))

    val address = Address("BigStreet 23", 1111, "Pittsburgh", "USA")
    val ltd = Company("Acme LTD", address)
    val person = Person("Dmitry", ltd)
    println(person.countryName())

    println(foo("Yeah"))

    printShippingLabel(person)
    //printShippingLabel(Person("Alexey", null))

    val p1 = Person2("Dmitry", "Jemerov")
    val p2 = Person2("Dmitry", "Jemerov")
    println(p1 == p2)
    println(p1.equals(42))

    ignoreNulls("Yeap!")

    var email: String? = "yole@example.com"

    email?.let { sendEmailTo(it) }

    email = null
    email?.let {sendEmailTo(it)}

    verifyUserInput("   ")
    verifyUserInput(null)

    val y = printHashCode(null)

    showProgress(2000)

    println(Person3("Sam",35).isOlderThan(Person3("Amy",42)))

    println(Person3("Sam").isOlderThan(Person3("Jane",20)))

    val i = 1L
    val l = i.toLong()

    // fail("Whoops!")

    val reader = BufferedReader(StringReader("1\nabc\n42"))
    val numbers = readNumbers(reader)
    addValidNumbers(numbers)

    val source: Collection<Int> = arrayListOf(3,5,7)
    val target: MutableCollection<Int> = arrayListOf(1)
    copyElements(source, target)
    println(target)

    val x1: List<Int> = listOf()
    val x11: List<Int> = mutableListOf()
    val x111: List<Int> = arrayListOf()

    val x2: Set<Int> = setOf()
    val x22: Set<Int> = mutableSetOf()
    val x222: Set<Int> = hashSetOf()
    val x2222: Set<Int> = linkedSetOf()
    val x22222: Set<Int> = sortedSetOf()

    val x3: Map<Int, String> = mapOf()
    val x33: Map<Int, String> = mutableMapOf()
    val x333: Map<Int, String> = hashMapOf()
    val x3333: Map<Int, String> = linkedMapOf()
    val x33333: Map<Int, String> = sortedMapOf()

    val list = listOf("a", "b", "c")
    // printInUppercase(list)

    /*for (i in args.indices){
        println("Argument $i is: ${args[i]}")
    }*/

    val letters = Array<String> (26) {i -> ('a' + i).toString()}
    println(letters.joinToString(" "))

    val letters2 = Array<String>(26) { ('a' + it).toString() }
    println(letters2.joinToString(""))

    val strings = listOf("a", "b", "c")
    println("%s/%s/%s".format(*strings.toTypedArray()))

    val fiveZeros = IntArray(5)
    val fiveZerosToo = intArrayOf(0,0,0,0,0)

    val squares = IntArray(5) {i -> (i+1) * (i+1)}
    println(squares.joinToString())




}