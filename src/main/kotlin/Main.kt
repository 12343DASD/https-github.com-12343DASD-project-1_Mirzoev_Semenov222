fun main() {
    println("Введите строку:")
    val input = readLine() ?: ""

// Считаем количество вхождений каждого символа
    val counts = mutableMapOf<Char, Int>()
    for (ch in input) {
        counts[ch] = (counts[ch] ?: 0) + 1
    }

// Сортируем символы по алфавиту
    val sortedKeys = counts.keys.sorted()

// Вывод результата
    for (key in sortedKeys) {
        println("$key - ${counts[key]}")
    }
}