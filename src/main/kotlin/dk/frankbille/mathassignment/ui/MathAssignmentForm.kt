package dk.frankbille.mathassignment.ui

import com.github.mvysny.karibudsl.v10.VaadinDsl
import com.github.mvysny.karibudsl.v10.bind
import com.github.mvysny.karibudsl.v10.integerField
import com.github.mvysny.karibudsl.v10.textField
import com.vaadin.flow.component.HasComponents
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.data.binder.Binder
import com.vaadin.flow.data.binder.ValidationResult
import com.vaadin.flow.data.binder.ValidationResult.ok
import com.vaadin.flow.data.binder.Validator
import com.vaadin.flow.data.binder.ValueContext
import com.vaadin.flow.data.converter.StringToLongConverter
import dk.frankbille.mathassignment.model.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.reflect.KMutableProperty1

class MathAssignmentForm : FormLayout() {

    private val binder = Binder(MathAssignmentConfig::class.java)
    private var mathAssignmentConfig: MathAssignmentConfig? = null

    init {
        responsiveSteps = listOf(
            ResponsiveStep("0", 1),
            ResponsiveStep("600px", 2),
            ResponsiveStep("900px", 3),
        )

        simpleEquation(
            "addition",
            SimpleAdditionEquation.minLevel,
            SimpleAdditionEquation.maxLevel,
            MathAssignmentConfig::simpleAdditionEquationCount,
            MathAssignmentConfig::minSimpleAdditionEquationLevel,
            MathAssignmentConfig::maxSimpleAdditionEquationLevel,
        )

        simpleEquation(
            "subtraction",
            SimpleSubtractionEquation.minLevel,
            SimpleSubtractionEquation.maxLevel,
            MathAssignmentConfig::simpleSubtractionEquationCount,
            MathAssignmentConfig::minSimpleSubtractionEquationLevel,
            MathAssignmentConfig::maxSimpleSubtractionEquationLevel,
        )

        simpleEquation(
            "multiplication",
            SimpleMultiplicationEquation.minLevel,
            SimpleMultiplicationEquation.maxLevel,
            MathAssignmentConfig::simpleMultiplicationEquationCount,
            MathAssignmentConfig::minSimpleMultiplicationEquationLevel,
            MathAssignmentConfig::maxSimpleMultiplicationEquationLevel,
        )

        simpleEquation(
            "division",
            SimpleDivisionEquation.minLevel,
            SimpleDivisionEquation.maxLevel,
            MathAssignmentConfig::simpleDivisionEquationCount,
            MathAssignmentConfig::minSimpleDivisionEquationLevel,
            MathAssignmentConfig::maxSimpleDivisionEquationLevel,
        )

        textField("Seed") {
            bind(binder)
                .withNullRepresentation("")
                .withConverter(object : StringToLongConverter("Must be number") {
                    override fun getFormat(locale: Locale?): NumberFormat {
                        return DecimalFormat("###")
                    }
                })
                .bind(MathAssignmentConfig::seed)
        }
    }

    fun setMathAssignmentConfig(mathAssignmentConfig: MathAssignmentConfig?) {
        this.mathAssignmentConfig = mathAssignmentConfig
        if (mathAssignmentConfig != null) {
            binder.readBean(mathAssignmentConfig)
        } else {
            binder.refreshFields()
        }
    }

    fun getMathAssignmentConfig(): MathAssignmentConfig? {
        if (mathAssignmentConfig == null) {
            mathAssignmentConfig = MathAssignmentConfig()
        }
        return if (binder.writeBeanIfValid(mathAssignmentConfig)) {
            mathAssignmentConfig
        } else {
            null
        }
    }

    private fun (@VaadinDsl HasComponents).simpleEquation(
        equationType: String,
        minLevel: Int,
        maxLevel: Int,
        countProperty: KMutableProperty1<MathAssignmentConfig, Int>,
        minLevelProperty: KMutableProperty1<MathAssignmentConfig, Int>,
        maxLevelProperty: KMutableProperty1<MathAssignmentConfig, Int>
    ) {
        integerField("# Simple $equationType equations") {
            focus()
            bind(binder).bind(countProperty)
        }
        integerField("Simple $equationType equations min level") {
            step = 1
            isStepButtonsVisible = true
            min = minLevel
            max = maxLevel
            bind(binder)
                .withValidator(MinMaxValidator(minLevel, maxLevel))
                .bind(minLevelProperty)
        }
        integerField("Simple $equationType equations max level") {
            step = 1
            isStepButtonsVisible = true
            min = minLevel
            max = maxLevel
            bind(binder)
                .withValidator(MinMaxValidator(minLevel, maxLevel))
                .bind(maxLevelProperty)
        }
    }

    private class MinMaxValidator(private val minLevel: Int, private val maxLevel: Int) : Validator<Int> {

        override fun apply(value: Int, context: ValueContext): ValidationResult {
            if (value in minLevel..maxLevel) {
                return ok()
            } else {
                return ValidationResult.error("must be between $minLevel and $maxLevel. Was $value")
            }
        }
    }
}