class Address (val streetAddress: String,
               val zipCode: Int,
               val city: String,
               val country: String)

class Company (val name: String, val address: Address?)

class Person (val name: String, val company: Company?)

fun Person.countryName(): String = this.company?.address?.country ?: "Unknown"

fun printShippingLabel (person: Person){
    val address = person.company?.address
        ?: throw IllegalArgumentException ("No address")
    with (address){
        println(streetAddress)
        println("$zipCode $city $country")
    }
}
