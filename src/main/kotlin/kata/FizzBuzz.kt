package kata

/**
 * Created by paulh on 9/01/17.
 */
class FizzBuzz(count: Int) {
    val elements : MutableList<String> = mutableListOf()
    init {
        for (i in 1..count) {
            var el = ""
            if (i % 3 == 0) el += "Fizz"
            if (i % 5 == 0) el += "Buzz"
            if (el.isBlank()) el = i.toString()
            elements.add(el)
        }
    }
}