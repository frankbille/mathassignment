package dk.frankbille.mathassignment

import dk.frankbille.mathassignment.model.*
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class AssignmentGenerator {
    fun generate(add: Int, sub: Int, mul: Int, div: Int, seed: Long): Assignment {
        return Assignment(
            sections = listOf(
                createSection(seed, add, "Addition (plus)") { SimpleAdditionEquation(it) },
                createSection(seed, sub, "Substraction (minus)") { SimpleSubtractionEquation(it) },
                createSection(seed, mul, "Multiplikation (gange)") { SimpleMultiplicationEquation(it) },
                createSection(seed, div, "Division (dividere)") { SimpleDivisionEquation(it) },
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