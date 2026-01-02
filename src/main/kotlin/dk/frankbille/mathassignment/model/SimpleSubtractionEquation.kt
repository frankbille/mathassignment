package dk.frankbille.mathassignment.model

import kotlin.random.Random

class SimpleSubtractionEquation(
    random: Random
) : AbstractSimpleEquation(random), Problem {

    override val b: Int get() = x - a

    override val promptLatex: String
        get() = "x - $a = $b"

    override val answerLatex: String
        get() = """
            x - $a &= $b \\
            \notag
            x - $a + $a &= $b + $a \\
            \notag
            x + \cancel{-$a + $a} &= $x \\
            \notag
            x + 0 &= $x \\
            \notag
            x &= $x
        """.trimIndent()
}