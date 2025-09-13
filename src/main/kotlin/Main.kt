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