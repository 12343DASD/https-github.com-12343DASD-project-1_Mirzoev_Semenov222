fun main() {
    println("Введите натуральное число:")
    val number = readLine()?.toIntOrNull()

    if (number != null && number >= 0) {
        val binary = number.toString(2)
        println("В двоичной системе: $binary")
    } else {
        println("Ошибка ввода!")
    }
}