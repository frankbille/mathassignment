package dk.frankbille.mathassignment

import dk.frankbille.mathassignment.model.*
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class AssignmentGenerator {
    fun generate(mathAssignmentConfig: MathAssignmentConfig) = with(mathAssignmentConfig) {
        Assignment(
            sections = listOf(
                createSection(seed, simpleAdditionEquationCount, "Addition (plus)") { SimpleAdditionEquation(it, randomSimpleAdditionEquationLevel(it)) },
                createSection(seed, simpleSubtractionEquationCount, "Substraction (minus)") { SimpleSubtractionEquation(it, randomSimpleSubtractionEquationLevel(it)) },
                createSection(seed, simpleMultiplicationEquationCount, "Multiplikation (gange)") { SimpleMultiplicationEquation(it, randomSimpleMultiplicationEquationLevel(it)) },
                createSection(seed, simpleDivisionEquationCount, "Division (dividere)") { SimpleDivisionEquation(it, randomSimpleDivisionEquationLevel(it)) },
            )
        )
    }

    private fun createSection(seed: Long, count: Int, heading: String, problemGenerator: (random: Random) -> Problem) =
        Section(
            heading = heading,
            problems = createProblems(seed, count, problemGenerator)
        )

    private fun createProblems(seed: Long, count: Int, problemGenerator: (random: Random) -> Problem): List<Problem> {
        val random = Random(seed)
        val problems = mutableSetOf<Problem>()
        do {
            problems.add(problemGenerator(random))
        } while (problems.size < count)
        return problems.toList()
    }

}