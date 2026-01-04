package dk.frankbille.mathassignment.model

import kotlin.random.Random

class SimpleSubtractionEquation(
    random: Random,
    level: Int,
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

    private class SimpleSubtractionEquationLevel1(random: Random) : AbstractSimpleEquation(random), Problem {

        override val b: Int get() = x - a

        override val promptLatex: String
            get() = "x - $a = $b"

        override val answerLatex: String
            get() = """
            x - $a &= $b \\
            \notag
            x - $a + $a &= $b + $a \\
            \notag
            x + 0 &= $x \\
            \notag
            x &= $x
        """.trimIndent()
    }

    private class SimpleSubtractionEquationLevel2(random: Random) : AbstractSimpleEquation(random), Problem {

        override val b: Int get() = a - x

        override val promptLatex: String
            get() = "$a - x = $b"

        override val answerLatex: String
            get() = """
            $a - x &= $b \\
            \notag
            $a - x + x &= $b + x \\
            \notag
            $a &= x + $b \\
            \notag
            $a - $b &= x + $b - $b \\
            \notag
            $x &= x + 0 \\
            \notag
            x &= $x
        """.trimIndent()
    }

    companion object {
        private val levelMap = mapOf<Int, (random: Random) -> Problem>(
            1 to { SimpleSubtractionEquationLevel1(it) },
            2 to { SimpleSubtractionEquationLevel2(it) },
        )

        val minLevel = levelMap.keys.min()
        val maxLevel = levelMap.keys.max()
    }
}