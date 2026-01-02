package dk.frankbille.mathassignment

import dk.frankbille.mathassignment.model.*
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class AssignmentGenerator {
    fun generate(add: Int, sub: Int, mul: Int, div: Int, seed: Long): Assignment {
        val random = Random(seed)

        return Assignment(
            sections = listOf(
                Section(
                    heading = "Addition (plus)",
                    problems = createProblems(add) { SimpleAdditionEquation(random) }
                ),
                Section(
                    heading = "Substraction (minus)",
                    problems = createProblems(sub) { SimpleSubtractionEquation(random) }
                ),
                Section(
                    heading = "Multiplikation (gange)",
                    problems = createProblems(mul) { SimpleMultiplicationEquation(random) }
                ),
                Section(
                    heading = "Division (dividere)",
                    problems = createProblems(div) { SimpleDivisionEquation(random) }
                ),
            )
        )
    }

    private fun createProblems(count: Int, problemGenerator: () -> Problem): List<Problem> {
        val problems = mutableSetOf<Problem>()
        do {
            problems.add(problemGenerator())
        } while (problems.size < count)
        return problems.toList()
    }

}