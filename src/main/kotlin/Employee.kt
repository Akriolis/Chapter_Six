class Employee (val name: String, val manager: Employee?) {
}

fun managerName (employee: Employee): String? = employee.manager?.name

