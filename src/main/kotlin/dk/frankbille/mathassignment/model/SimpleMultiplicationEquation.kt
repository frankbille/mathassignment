package dk.frankbille.mathassignment.model

import kotlin.random.Random

class SimpleMultiplicationEquation(
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

    private class SimpleMultiplicationEquationLevel1(random: Random) : AbstractSimpleEquation(random), Problem {
        override fun xMin() = 0
        override fun xMax() = 10
        override fun aMin() = 1
        override fun aMax() = 10

        override val b: Int get() = x * a

        override val promptLatex: String
            get() = "x \\cdot $a = $b"

        override val answerLatex: String
            get() = """
            x \cdot $a &= $b \\
            \notag
            \frac{x \cdot $a}{$a} &= \frac{$b}{$a} \\
            \notag
            x \cdot \frac{$a}{$a} &= $x \\
            \notag
            x \cdot \cancel{1} &= $x \\
            \notag
            x &= $x
        """.trimIndent()
    }

    private class SimpleMultiplicationEquationLevel2(random: Random) : AbstractSimpleEquation(random), Problem {
        override fun xMin() = -10
        override fun xMax() = 0
        override fun aMin() = -10
        override fun aMax() = 10

        override val b: Int get() = x * a

        override val promptLatex: String
            get() = "x \\cdot $aString = $b"

        override val answerLatex: String
            get() = """
            x \cdot $aString &= $b \\
            \notag
            \frac{x \cdot $aString}{$a} &= \frac{$b}{$a} \\
            \notag
            x \cdot \frac{$a}{$a} &= $x \\
            \notag
            x \cdot \cancel{1} &= $x \\
            \notag
            x &= $x
        """.trimIndent()

        private val aString: String get() = if (a < 0) {
            "($a)"
        } else {
            "$a"
        }
    }

    companion object {
        private val levelMap = mapOf<Int, (random: Random) -> Problem>(
            1 to { SimpleMultiplicationEquationLevel1(it) },
            2 to { SimpleMultiplicationEquationLevel2(it) },
        )

        val minLevel = levelMap.keys.min()
        val maxLevel = levelMap.keys.max()
    }
}