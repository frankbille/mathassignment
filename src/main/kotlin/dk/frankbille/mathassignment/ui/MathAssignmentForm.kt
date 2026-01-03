package dk.frankbille.mathassignment.ui

import com.vaadin.flow.component.Composite
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.textfield.IntegerField
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.binder.Binder
import com.vaadin.flow.data.converter.StringToLongConverter
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class MathAssignmentForm : Composite<FormLayout>() {

    private val binder: Binder<MathAssignmentData> = Binder(MathAssignmentData::class.java)
    private var mathAssignmentData: MathAssignmentData? = null

    private val simpleAdditionEquationCount = IntegerField("# Simple Additions")
    private val simpleSubtractionEquationCount = IntegerField("# Simple Subtractions")
    private val simpleMultiplicationEquationCount = IntegerField("# Simple Multiplications")
    private val simpleDivisionEquationCount = IntegerField("# Simple Divisions")
    private val seed = TextField("Seed")

    init {
        content.apply {
            add(simpleAdditionEquationCount)
            add(simpleSubtractionEquationCount)
            add(simpleMultiplicationEquationCount)
            add(simpleDivisionEquationCount)
            add(seed)
        }

        binder
            .forMemberField(seed)
            .withNullRepresentation("")
            .withConverter(object : StringToLongConverter("Must be number") {
                override fun getFormat(locale: Locale?): NumberFormat {
                    return DecimalFormat("###")
                }
            })
            .bind("seed")
        binder
            .bindInstanceFields(this)
    }

    fun setMathAssignmentData(mathAssignmentData: MathAssignmentData?) {
        this.mathAssignmentData = mathAssignmentData
        if (mathAssignmentData != null) {
            binder.readBean(mathAssignmentData)
        } else {
            binder.refreshFields()
        }
    }

    fun getMathAssignmentData(): MathAssignmentData? {
        if (mathAssignmentData == null) {
            mathAssignmentData = MathAssignmentData()
        }
        return if (binder.writeBeanIfValid(mathAssignmentData)) {
            mathAssignmentData
        } else {
            null
        }
    }
}