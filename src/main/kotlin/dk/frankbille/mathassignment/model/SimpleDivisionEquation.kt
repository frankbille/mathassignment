package dk.frankbille.mathassignment.model

import kotlin.random.Random

class SimpleDivisionEquation(
    random: Random
) : Problem {

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

        other as SimpleDivisionEquation

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