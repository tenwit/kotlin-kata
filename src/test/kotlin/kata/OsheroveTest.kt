package kata

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

/**
 * Created by paulh on 9/01/17.
 */
class OsheroveTest {
    @Test
    fun emptyStringEqualsZero() {
        assertThat(Osherove("").add()).isEqualTo(0)
    }

    @Test
    fun singleNumberEqualsNumber() {
        assertThat(Osherove("7").add()).isEqualTo(7)
        assertThat(Osherove("819").add()).isEqualTo(819)
    }

    @Test
    fun twoNumbersAreAdded() {
        assertThat(Osherove("3,8").add()).isEqualTo(11)
        assertThat(Osherove("819,980").add()).isEqualTo(1799)
    }

    class ResultMap(val input: String, val result: Int)

    @TestFactory
    fun multipleNumbersAreAdded() : Collection<DynamicTest> = listOf(
            ResultMap("1", 1),
            ResultMap("1,1", 2),
            ResultMap("1,1,1", 3),
            ResultMap("1,1,3,4", 9),
            ResultMap("1,141,2,98,33", 275)
    ).map {
        DynamicTest.dynamicTest("${it.input} should add to ${it.result}") {
            assertThat(Osherove(it.input).add()).isEqualTo(it.result)
        }
    }

    @TestFactory
    fun separatorsAreNewlinesOrCommas() : Collection<DynamicTest> = listOf(
            ResultMap("1", 1),
            ResultMap("1\n1", 2),
            ResultMap("1\n1,1,", 3),
            ResultMap("1,1\n3,4\n", 9),
            ResultMap("1\n141\n2\n98\n33\n,,\n,\n\n", 275)
    ).map {
        DynamicTest.dynamicTest("${it.input} should add to ${it.result}") {
            assertThat(Osherove(it.input).add()).isEqualTo(it.result)
        }
    }

    @Test
    fun firstLineMaySpecifySeparator() {
        assertThat(Osherove(";\n5;8").add()).isEqualTo(13)
    }

    @TestFactory
    fun cannotAddNegatives() : Collection<DynamicTest> = listOf(
            ResultMap("-1", 1),
            ResultMap("-11\n-1", 2),
            ResultMap("-41\n1,-65,", 2),
            ResultMap("1,1\n3,-4\n", 1),
            ResultMap("-1\n-141\n2\n-98\n33\n,,\n,\n\n", 3)
    ).map {
        DynamicTest.dynamicTest("${it.input} should complain about ${it.result} negative numbers") {
            assertThatThrownBy {
                Osherove(it.input).add()
            }.hasMessageMatching("^[^-]+(-\\p{Digit}+, ){${it.result - 1}}-\\p{Digit}+[^-]*$")
        }
    }
}
