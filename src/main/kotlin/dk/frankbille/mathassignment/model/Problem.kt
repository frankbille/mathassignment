package dk.frankbille.mathassignment.model

sealed interface Problem {
    val promptLatex: String
    val answerLatex: String
}
