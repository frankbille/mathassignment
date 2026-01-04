package dk.frankbille.mathassignment.model

import kotlin.random.Random

class SimpleAdditionEquation(
    random: Random,
    level: Int
) : Problem {

    private val levelProblem: Problem

    init {
        require(level in levelMap.keys) { "Level must be in ${levelMap.keys}. Was $level" }

        levelProblem = levelMap[level]!!(random)
    }

    override val promptLatex: String
        get() = levelProblem.promptLatex

    override val answerLatex: String
        get() = levelProblem.answerLatex

    private class SimpleAdditionEquationLevel1(random: Random) : AbstractSimpleEquation(random), Problem {
        override val b: Int get() = x + a

        override val promptLatex: String
            get() = "x + $a = $b"

        override val answerLatex: String
            get() = """
            x + $a &= $b \\
            \notag
            x + $a - $a &= $b - $a \\
            \notag
            x + 0 &= $x \\
            \notag
            x &= $x
        """.trimIndent()
    }

    private class SimpleAdditionEquationLevel2(random: Random) : AbstractSimpleEquation(random), Problem {
        override val b: Int get() = x + a

        override val promptLatex: String
            get() = "$a + x = $b"

        override val answerLatex: String
            get() = """
            $a + x &= $b \\
            \notag
            x + $a &= $b \\
            \notag
            x + $a - $a &= $b - $a \\
            \notag
            x + 0 &= $x \\
            \notag
            x &= $x
        """.trimIndent()
    }

    companion object {
        private val levelMap = mapOf<Int, (random: Random) -> Problem>(
            1 to { SimpleAdditionEquationLevel1(it) },
            2 to { SimpleAdditionEquationLevel2(it) },
        )

        val minLevel = levelMap.keys.min()
        val maxLevel = levelMap.keys.max()
    }
}