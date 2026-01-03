package dk.frankbille.mathassignment.ui

data class MathAssignmentData(
    var simpleAdditionEquationCount: Int = 10,
    var simpleSubtractionEquationCount: Int = 10,
    var simpleMultiplicationEquationCount: Int = 10,
    var simpleDivisionEquationCount: Int = 10,
    var seed: Long = System.currentTimeMillis(),
)
