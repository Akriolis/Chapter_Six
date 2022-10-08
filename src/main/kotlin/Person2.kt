class Person2 (val firstName: String, val lastName: String) {
    override fun equals(other: Any?): Boolean {
        val otherPerson = other as? Person2 ?: return false

        return otherPerson.firstName == firstName &&
                otherPerson.lastName == lastName
    }
    override fun hashCode(): Int =
        firstName.hashCode() * 3 + lastName.hashCode()
}

