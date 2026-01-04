package dk.frankbille.mathassignment.model

import kotlin.random.Random
import kotlin.random.nextInt

data class MathAssignmentConfig(
    var simpleAdditionEquationCount: Int = 10,
    var minSimpleAdditionEquationLevel: Int = 1,
    var maxSimpleAdditionEquationLevel: Int = 1,

    var simpleSubtractionEquationCount: Int = 10,
    var minSimpleSubtractionEquationLevel: Int = 1,
    var maxSimpleSubtractionEquationLevel: Int = 1,

    var simpleMultiplicationEquationCount: Int = 10,
    var minSimpleMultiplicationEquationLevel: Int = 1,
    var maxSimpleMultiplicationEquationLevel: Int = 1,

    var simpleDivisionEquationCount: Int = 10,
    var minSimpleDivisionEquationLevel: Int = 1,
    var maxSimpleDivisionEquationLevel: Int = 1,

    var seed: Long = System.currentTimeMillis(),
) {

    fun randomSimpleAdditionEquationLevel(random: Random) =
        randomLevel(minSimpleAdditionEquationLevel, maxSimpleAdditionEquationLevel, random)

    fun randomSimpleSubtractionEquationLevel(random: Random) =
        randomLevel(minSimpleSubtractionEquationLevel, maxSimpleSubtractionEquationLevel, random)

    fun randomSimpleMultiplicationEquationLevel(random: Random) =
        randomLevel(minSimpleMultiplicationEquationLevel, maxSimpleMultiplicationEquationLevel, random)

    fun randomSimpleDivisionEquationLevel(random: Random) =
        randomLevel(minSimpleDivisionEquationLevel, maxSimpleDivisionEquationLevel, random)

    private fun randomLevel(minLevel: Int, maxLevel: Int, random: Random) =
        random.nextInt(minLevel..maxLevel)
}