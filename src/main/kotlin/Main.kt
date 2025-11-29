import java.util.Locale

fun main(args: Array<String>) {
    while (true) {
        println("0 выход")
        println("1 задача — Подсчёт различных цифр в матрице")
        println("2 задача — Симметрия матрицы 5×5")
        println("3 задача — Шифровка и дешифровка")
        println("4 задача — Пересечение массивов")
        println("5 задача — Группировка слов по одинаковым буквам")
        println("6 задача — Уникальные элементы массива")
        print("Выберите пункт меню: ")

        when (readln()) {
            "0" -> return
            "1" -> task1()
            "2" -> task2()
            "3" -> task3()
            "4" -> task4()
            "5" -> task5()
            "6" -> task6()
            else -> println("Неверный ввод\n")
        }
    }
}

// ЗАДАЧА 1

fun task1() {
    println("\n--- Задача 1 ---")

    val rows = readInt("Введите количество строк: ")
    val cols = readInt("Введите количество столбцов: ")

    val matrix = Array(rows) { IntArray(cols) }

    println("Введите ${rows * cols} ТРЕХЗНАЧНЫХ чисел:")

    for (i in 0 until rows) {
        for (j in 0 until cols) {
            while (true) {
                print("[$i][$j] = ")
                val num = readln().toIntOrNull()
                if (num != null && num in 100..999) {
                    matrix[i][j] = num
                    break
                } else {
                    println("Ошибка: нужно трехзначное число.")
                }
            }
        }
    }

    val digits = matrix.flatMap { row ->
        row.flatMap { number -> number.toString().map { it } }
    }.toSet()

    println("\nВведённый массив:")
    matrix.forEach { row -> println(row.joinToString("  ")) }

    println("\nВ массиве использовано ${digits.size} различных цифр\n")
}

fun readInt(prompt: String): Int {
    while (true) {
        print(prompt)
        val v = readln().toIntOrNull()
        if (v != null) return v
        println("Ошибка ввода, попробуйте снова.")
    }
}

// ЗАДАЧА 2 — симметрия матрицы

fun task2() {
    println("\n--- Задача 2: симметрия матрицы 5×5 ---")

    val n = 5
    val matrix = Array(n) { IntArray(n) }

    println("Введите матрицу 5×5 (по строкам):")

    for (i in 0 until n) {
        while (true) {
            print("Строка ${i + 1}: ")
            val line = readln()
            val parts = line.split(Regex("\\s+"))
            if (parts.size != n) {
                println("Введите ровно 5 чисел.")
                continue
            }

            val nums = parts.mapNotNull { it.toIntOrNull() }
            if (nums.size != n) {
                println("Ошибка: вводите только числа.")
                continue
            }

            for (j in 0 until n) matrix[i][j] = nums[j]
            break
        }
    }

    println("\nМатрица:")
    matrix.forEach { println(it.joinToString("  ")) }

    var symmetric = true
    for (i in 0 until n) {
        for (j in i + 1 until n) {
            if (matrix[i][j] != matrix[j][i]) symmetric = false
        }
    }

    if (symmetric) println("\nМатрица симметрична.\n")
    else println("\nМатрица НЕ симметрична.\n")
}

// ЗАДАЧА 3 — Шифровка

val rusAlphabet = listOf(
    'А','Б','В','Г','Д','Е','Ё','Ж','З','И','Й','К','Л','М','Н','О',
    'П','Р','С','Т','У','Ф','Х','Ц','Ч','Ш','Щ','Ь','Ы','Ъ','Э','Ю','Я'
)

val alphabetIndices = listOf(
    21, 13, 4, 20, 22, 1, 25, 12, 24, 14, 2, 28, 9, 23, 3, 29,
    6, 16, 15, 11, 26, 5, 30, 27, 8, 18, 10, 33, 31, 32, 19, 7, 17
)

fun task3() {
    println("\n--- Задача 3: шифровка/дешифровка ---")

    print("Шифровать (1) или Дешифровать (2)? ")
    val mode = readln()

    print("Введите ключевое слово: ")
    val key = readln().uppercase(Locale.getDefault()).filter { it in rusAlphabet }

    print("Введите текст: ")
    val text = readln().uppercase(Locale.getDefault()).filter { it in rusAlphabet }

    val result = if (mode == "1") encrypt(text, key) else decrypt(text, key)

    println("\nРезультат: $result\n")
}

fun shiftIndex(c: Char): Int = alphabetIndices[rusAlphabet.indexOf(c)]

fun encrypt(text: String, key: String): String {
    val res = StringBuilder()
    var ki = 0
    for (ch in text) {
        val pos = rusAlphabet.indexOf(ch)
        val shift = shiftIndex(key[ki])
        res.append(rusAlphabet[(pos + shift) % 33])
        ki = (ki + 1) % key.length
    }
    return res.toString()
}

fun decrypt(text: String, key: String): String {
    val res = StringBuilder()
    var ki = 0
    for (ch in text) {
        val pos = rusAlphabet.indexOf(ch)
        val shift = shiftIndex(key[ki])
        res.append(rusAlphabet[(pos - shift + 33) % 33])
        ki = (ki + 1) % key.length
    }
    return res.toString()
}

// ЗАДАЧА 4 — Пересечение массивов

fun task4() {
    println("\n--- Задача 4: пересечение массивов ---")

    println("Введите первый массив через пробел:")
    val arr1 = readIntArray()

    println("Введите второй массив через пробел:")
    val arr2 = readIntArray()

    val m1 = arr1.groupingBy { it }.eachCount()
    val m2 = arr2.groupingBy { it }.eachCount()

    val result = mutableListOf<Int>()

    for ((num, c1) in m1) {
        val c2 = m2[num]
        if (c2 != null) repeat(minOf(c1, c2)) { result.add(num) }
    }

    println("Пересечение: ${result.joinToString(", ")}\n")
}

fun readIntArray(): List<Int> {
    while (true) {
        val parts = readln().trim().split(Regex("\\s+"))
        val nums = parts.mapNotNull { it.toIntOrNull() }
        if (nums.size == parts.size) return nums
        println("Ошибка: вводите только числа.")
    }
}

// ЗАДАЧА 5 — Группировка слов

fun task5() {
    println("\n--- Задача 5 ---")
    println("Введите слова через пробел:")
    val words = readln().trim().split(Regex("\\s+")).filter { it.isNotEmpty() }

    val groups = words.groupBy { it.lowercase().toCharArray().sorted().joinToString("") }

    println("\nГруппы:")
    groups.values.forEach { println(it.joinToString(", ")) }
    println()
}

// ЗАДАЧА 6 — Уникальные элементы массива

fun task6() {
    println("\n--- Задача 6: уникальные элементы массива ---")
    println("Введите массив через пробел:")

    val arr = readIntArray()
    val freq = arr.groupingBy { it }.eachCount()
    val unique = freq.filter { it.value == 1 }.keys.toList()

    println("Уникальные элементы: ${unique.joinToString(", ")}\n")
}
