package kata

/**
 * A kata
 */
class Osherove(private var input: String) {
    fun add(): Int {
        val separator = getDefaultSeparator()
        val negatives = mutableListOf<Int>()
        val result = input.splitToSequence(separator, '\n').sumBy { n ->
            try {
                val ret = n.toInt()
                if (ret < 0) negatives.add(ret)
                ret
            } catch (e: NumberFormatException) {
                0
            }
        }

        if (negatives.isNotEmpty()) {
            throw IllegalArgumentException(negatives.joinToString(", ", "Negatives not allowed: "))
        }
        return result
    }

    private fun getDefaultSeparator(): Char {
        if (input.length < 2 || input[1] != '\n' || (input[0] >= '0' && input[0] <= '9')) return ','
        val separator = input[0]
        input = input.substring(2)
        return separator
    }
}
