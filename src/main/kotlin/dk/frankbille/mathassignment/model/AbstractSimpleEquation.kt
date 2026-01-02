package dk.frankbille.mathassignment.model

import kotlin.random.Random

abstract class AbstractSimpleEquation(random: Random) {

    protected open fun xMin() = 0
    protected open fun xMax() = 100
    protected open fun aMin() = 0
    protected open fun aMax() = 100

    protected val x = random.nextInt(xMin(), xMax() + 1)
    protected val a = random.nextInt(aMin(), aMax() + 1)
    protected abstract val b: Int

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AbstractSimpleEquation

        if (x != other.x) return false
        if (a != other.a) return false
        if (b != other.b) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + a
        result = 31 * result + b
        return result
    }


}