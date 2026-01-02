package dk.frankbille.mathassignment.model

import kotlin.random.Random

class SimpleMultiplicationEquation(
    random: Random
) : AbstractSimpleEquation(random), Problem {

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