package dk.frankbille.mathassignment.model

import kotlin.random.Random

class SimpleDivisionEquation(
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

    private class SimpleDivisionEquationLevel1(random: Random) : Problem {
        private val a = random.nextInt(1, 13)
        private val b = random.nextInt(2, 13)
        private val x: Int get() = a * b

        override val promptLatex: String
            get() = "\\frac{x}{$a} = $b"

        override val answerLatex: String
            get() = """
            \frac{x}{$a} &= $b \\
            \notag
            \frac{x}{$a} \cdot $a &= $b \cdot $a \\
            \notag
            \frac{x \cdot $a}{$a} &= $x \\
            \notag
            x \cdot \frac{$a}{$a} &= $x \\
            \notag
            x \cdot \cancel{1} &= $x \\
            \notag
            x &= $x
        """.trimIndent()

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as SimpleDivisionEquationLevel1

            if (x != other.x) return false
            if (b != other.b) return false
            if (a != other.a) return false

            return true
        }

        override fun hashCode(): Int {
            var result = x
            result = 31 * result + b
            result = 31 * result + a
            return result
        }
    }

    private class SimpleDivisionEquationLevel2(random: Random) : Problem {
        private val b = random.nextInt(1, 13)
        private val x = random.nextInt(2, 13)
        private val a: Int get() = b * x

        override val promptLatex: String
            get() = "\\frac{$a}{x} = $b"

        override val answerLatex: String
            get() = """
            \frac{$a}{x} &= $b \\
            \notag
            \frac{$a}{x} \cdot x &= $b \cdot x \\
            \notag
            $a \cdot 1 &= $b \cdot x \\
            \notag
            \frac{$a}{$b} &= \frac{$b \cdot x}{$b} \\
            \notag
            $x &= 1 \cdot x \\
            \notag
            x &= $x
        """.trimIndent()

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as SimpleDivisionEquationLevel2

            if (x != other.x) return false
            if (b != other.b) return false
            if (a != other.a) return false

            return true
        }

        override fun hashCode(): Int {
            var result = x
            result = 31 * result + b
            result = 31 * result + a
            return result
        }
    }

    override fun equals(other: Any?) = levelProblem == other
    override fun hashCode() = levelProblem.hashCode()

    companion object {
        private val levelMap = mapOf<Int, (random: Random) -> Problem>(
            1 to { SimpleDivisionEquationLevel1(it) },
            2 to { SimpleDivisionEquationLevel2(it) },
        )

        val minLevel = levelMap.keys.min()
        val maxLevel = levelMap.keys.max()
    }

}