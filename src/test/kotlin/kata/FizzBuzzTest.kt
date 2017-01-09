package kata

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Created by paulh on 9/01/17.
 */
class FizzBuzzTest {
    @Test
    fun firstTwoResultsAreOneAndTwo() {
        assertThat(FizzBuzz(2).elements).contains("1", "2")
    }

    @Test
    fun everyThirdResultContainsFizz() {
        val everyThird = FizzBuzz(100).elements.filterIndexed { i, s -> (1 + i) % 3 == 0 }
        assertThat(everyThird).isNotEmpty()
        everyThird.forEach { third -> assertThat(third).contains("Fizz") }
    }

    @Test
    fun everyFifthResultContainsBuzz() {
        val everyFifth = FizzBuzz(100).elements.filterIndexed { i, s -> (1 + i) % 5 == 0 }
        assertThat(everyFifth).isNotEmpty()
        everyFifth.forEach { fifth -> assertThat(fifth).contains("Buzz") }
    }

    @Test
    fun everyFifteenthResultIsFizzBuzz() {
        val everyFifteenth = FizzBuzz(100).elements.filterIndexed { i, s -> (1 + i) % 15 == 0 }
        assertThat(everyFifteenth).isNotEmpty()
        everyFifteenth.forEach { fifteenth -> assertThat(fifteenth).isEqualTo("FizzBuzz") }
    }

    @Test
    fun ifItsNotFizzOrBuzzOrFizzBuzzItsItsOwnIndex() {
        FizzBuzz(100).elements.forEachIndexed { i, el -> if (!el.contains('z')) assertThat(el).isEqualTo((i + 1).toString()) }
    }
}
