package dk.frankbille.mathassignment.model

import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import kotlin.random.Random

class SimpleMultiplicationEquationTests {

    private val random = mockk<Random>(relaxed = true) {
        every { nextInt(any(), any()) } returnsMany listOf(
            1, 2,
            3, 4,
            5, 6,
            7, 8,
            7, 8,
        )
    }

    @AfterEach
    fun resetMocks() = clearMocks(random)

    @Test
    fun `should generate random multiplication`() {
        val multiplication = SimpleMultiplicationEquation(random)

        assertThat(multiplication.promptLatex).isNotBlank
        assertThat(multiplication.promptLatex).isEqualTo("x \\cdot 2 = 2")
    }

    @Test
    fun `should generate multiple distinct multiplications from random`() {
        with(SimpleMultiplicationEquation(random)) {
            assertThat(promptLatex).isNotBlank
            assertThat(promptLatex).isEqualTo("x \\cdot 2 = 2")
        }
        with(SimpleMultiplicationEquation(random)) {
            assertThat(promptLatex).isNotBlank
            assertThat(promptLatex).isEqualTo("x \\cdot 4 = 12")
        }
        with(SimpleMultiplicationEquation(random)) {
            assertThat(promptLatex).isNotBlank
            assertThat(promptLatex).isEqualTo("x \\cdot 6 = 30")
        }
        with(SimpleMultiplicationEquation(random)) {
            assertThat(promptLatex).isNotBlank
            assertThat(promptLatex).isEqualTo("x \\cdot 8 = 56")
        }
    }

    @Test
    fun `should have a valid equals and hashCode`() {
        val set = setOf(
            SimpleMultiplicationEquation(random), // 1,2
            SimpleMultiplicationEquation(random), // 3,4
            SimpleMultiplicationEquation(random), // 5,6
            SimpleMultiplicationEquation(random), // 7,8
            SimpleMultiplicationEquation(random), // 7,8 - Duplicate of previous
        )

        assertThat(set).hasSize(4)
    }

}