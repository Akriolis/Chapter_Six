class Person3 (val name: String, val age: Int? = null) {
    fun isOlderThan (other: Person3): Boolean?{
        if (age == null || other.age == null)
            return null
        return age > other.age
    }
}