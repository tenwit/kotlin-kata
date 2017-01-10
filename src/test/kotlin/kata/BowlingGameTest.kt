package kata

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

/**
 * A kata
 */
class BowlingGameTest {
    @TestFactory
    fun oneRollScoresItsPins(): Collection<DynamicTest> = listOf(
            ExpectedScore(arrayOf(0), 0),
            ExpectedScore(arrayOf(1), 1),
            ExpectedScore(arrayOf(2), 2),
            ExpectedScore(arrayOf(3), 3),
            ExpectedScore(arrayOf(4), 4),
            ExpectedScore(arrayOf(5), 5),
            ExpectedScore(arrayOf(6), 6),
            ExpectedScore(arrayOf(7), 7),
            ExpectedScore(arrayOf(8), 8),
            ExpectedScore(arrayOf(9), 9),
            ExpectedScore(arrayOf(10), 10)
    ).map {
        DynamicTest.dynamicTest("A roll of ${it.rolls[0]} should score ${it.score}") {
            assertThat(it.rollAll(BowlingGame()).score()).isEqualTo(it.score)
        }
    }

    @TestFactory
    fun twoPinFrameScoresItsPins(): Collection<DynamicTest> = listOf(
            ExpectedScore(arrayOf(0, 0), 0),
            ExpectedScore(arrayOf(0, 1), 1),
            ExpectedScore(arrayOf(1, 0), 1),
            ExpectedScore(arrayOf(6, 2), 8),
            ExpectedScore(arrayOf(5, 5), 10)
    ).map {
        DynamicTest.dynamicTest("A roll of ${it.rolls[0]} should score ${it.score}") {
            assertThat(it.rollAll(BowlingGame()).score()).isEqualTo(it.score)
        }
    }

    @TestFactory
    fun spareScoresOneBonusRoll(): Collection<DynamicTest> = listOf(
            ExpectedScore(arrayOf(5, 5, 2, 2), 16),
            ExpectedScore(arrayOf(5, 5, 0, 5), 15),
            ExpectedScore(arrayOf(0, 10, 5, 5), 25)
    ).map {
        DynamicTest.dynamicTest("These two frames should score ${it.score}") {
            assertThat(it.rollAll(BowlingGame()).score()).isEqualTo(it.score)
        }
    }

    @Test
    fun consecutiveBallsInDifferentFramesIsNotASpare() {
        assertThat(BowlingGame().roll(2).roll(5).roll(5).roll(2).score()).isEqualTo(14)
    }

    @TestFactory
    fun strikeScoresTwoBonusBalls(): Collection<DynamicTest> = listOf(
            ExpectedScore(arrayOf(10, 0, 0, 4, 4), 18),
            ExpectedScore(arrayOf(10, 1, 1, 4, 4), 22),
            ExpectedScore(arrayOf(4, 4, 10, 4, 4), 34),
            ExpectedScore(arrayOf(10, 0, 10, 10), 50),
            ExpectedScore(arrayOf(10, 10, 10), 60),
            ExpectedScore(arrayOf(0, 10, 10, 10), 50)
    ).map {
        DynamicTest.dynamicTest("These three frames should score ${it.score}") {
            assertThat(it.rollAll(BowlingGame()).score()).isEqualTo(it.score)
        }
    }

    @Test
    fun gutterGameScoresZero() {
        val game = BowlingGame()
        (1..20).forEach {game.roll(0)}
        assertThat(game.score()).isEqualTo(0)
    }

    @Test
    fun perfectGameScoresThreeHundred() {
        val game = BowlingGame()
        (1..12).forEach {game.roll(10)}
        assertThat(game.score()).isEqualTo(300)
    }

    data class ExpectedScore(val rolls: Array<Int>, val score: Int) {
        override fun equals(other: Any?): Boolean {
            return super.equals(other)
        }

        override fun hashCode(): Int {
            return super.hashCode()
        }

        fun rollAll(game: BowlingGame): BowlingGame {
            rolls.forEach { game.roll(it) }
            return game
        }
    }
}
