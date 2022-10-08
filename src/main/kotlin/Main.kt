import java.awt.event.ActionEvent
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

    


}