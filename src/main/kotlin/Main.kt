fun main(args: Array<String>) {
    while (true){
        println("0 выход")
        println("1 задача")
        println("2 задача")
        println("3 задача")
        println("4 задача")
        println("5 задача")
        println("6 задача")
        println("Выберите пунк меню: ")

        val input = readln()

        when(input)
        {
            "0" -> return
            "1" -> task1()
            "2" -> task2()
            "3" -> task3()
            "4" -> task4()
            "5" -> task5()
            "6" -> task6()
            else -> println("Неверный ввод")
        }
    }
}

fun task1()
{
    fun main() {
        println("Введите строку:")
        val input = readLine() ?: ""

        if (input.isEmpty()) {
            println("Пустая строка")
            return
        }

        val sb = StringBuilder()
        var count = 1

        for (i in 1 until input.length) {
            if (input[i] == input[i - 1]) {
                count++
            } else {
                sb.append(input[i - 1])
                if (count > 1) sb.append(count)
                count = 1
            }
        }

        sb.append(input.last())
        if (count > 1) sb.append(count)

        println("Результат: $sb")
    }
}

fun task2()
{
    fun main() {
        println("Введите строку:")
        val input = readLine() ?: ""
        val counts = mutableMapOf<Char, Int>()
        for (ch in input) {
            counts[ch] = (counts[ch] ?: 0) + 1
        }

        val sortedKeys = counts.keys.sorted()
        for (key in sortedKeys) {
            println("$key - ${counts[key]}")
        }
    }
}

fun task3()
{
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
}

fun task4()
{
    fun main() {
        println("Введите выражение (ЧИСЛО1 ЧИСЛО2 ОПЕРАЦИЯ):")
        val input = readLine() ?: ""
        val parts = input.split(" ")

        if (parts.size == 3) {
            val num1 = parts[0].toDoubleOrNull()
            val num2 = parts[1].toDoubleOrNull()
            val op = parts[2]

            if (num1 != null && num2 != null) {
                val result = when (op) {
                    "+" -> num1 + num2
                    "-" -> num1 - num2
                    "*" -> num1 * num2
                    "/" -> if (num2 != 0.0) num1 / num2 else "Деление на ноль!"
                    else -> "Неизвестная операция!"
                }
                println("Результат: $result")
            } else {
                println("Ошибка: введены некорректные числа!")
            }
        } else {
            println("Ошибка формата ввода!")
        }
    }
}

fun task5()
{
    fun main() {
        println("Введите число n:")
        val n = readLine()?.toIntOrNull() ?: return
        println("Введите основание степени x:")
        val x = readLine()?.toIntOrNull() ?: return

        var y = 0
        var found = false
        for (i in 0..30) {
            if (x.toDouble().pow(i).toInt() == n) {
                y = i
                found = true
                break
            }
        }

        if (found) {
            println("Существует показатель: y = $y")
        } else {
            println("Целочисленный показатель не существует")
        }
    }
}

fun task6()
{
    fun main() {
        println("Введите первую цифру:")
        val a = readLine()?.toIntOrNull()
        println("Введите вторую цифру:")
        val b = readLine()?.toIntOrNull()

        if (a == null || b == null || a !in 0..9 || b !in 0..9 || a == b) {
            println("Ошибка ввода!")
            return
        }

        val result = when {
            a % 2 == 1 -> "$b$a"
            b % 2 == 1 -> "$a$b"
            else -> "Создать нечетное число невозможно"
        }

        println(result)
    }
}