package kata

/**
 * A kata.
 */
class BowlingGame {
    val rolls = mutableListOf<Roll>()
    var currentRoll = 1
    var currentFrame = 1

    fun roll(pins: Int): BowlingGame {
        rolls.add(Roll(pins, currentRoll++, currentFrame))
        val rollsForFrame = rollsForFrame(currentFrame)
        if (rollsForFrame.count() == 2 ||
                (rollsForFrame.count() == 1 && rollsForFrame[0].pins == 10)) ++currentFrame
        return this
    }

    private fun rollsForFrame(frame: Int) = rolls.filter { it.frame == frame }

    fun score(): Int {
        return (1..10).sumBy { scoreFrame(it) }
    }

    fun scoreFrame(frame: Int): Int {
        val rollsInFrame = rollsForFrame(frame)
        val scoreForFrame = rollsInFrame.sumBy(Roll::pins)
        if (scoreForFrame < 10) return scoreForFrame
        val lastIndexInFrame = rollsInFrame.last().index
        var numberOfBonusBalls = 3 - rollsInFrame.count()

        // Account for the possibility that not all balls have been rolled yet
        if (rolls.count() < lastIndexInFrame + 1) return scoreForFrame
        if (rolls.count() < lastIndexInFrame + numberOfBonusBalls) numberOfBonusBalls = 1
        val bonusRange = lastIndexInFrame.rangeTo(lastIndexInFrame + numberOfBonusBalls - 1)
        val bonus = rolls.slice(bonusRange).sumBy(Roll::pins)
        return scoreForFrame + bonus
    }
}

data class Roll(val pins: Int, val index: Int, val frame: Int)