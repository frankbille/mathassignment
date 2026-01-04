package dk.frankbille.mathassignment.ui

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment.END
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import com.vaadin.flow.server.streams.DownloadHandler
import com.vaadin.flow.server.streams.DownloadResponse
import dk.frankbille.mathassignment.AssignmentGenerator
import dk.frankbille.mathassignment.TexTemplateService
import dk.frankbille.mathassignment.model.MathAssignmentConfig
import org.springframework.beans.factory.annotation.Autowired
import java.io.ByteArrayInputStream


@Route
@PageTitle("Math Assignment")
class MainView : KComposite() {

    @Autowired
    private lateinit var assignmentGenerator: AssignmentGenerator

    @Autowired
    private lateinit var texTemplateService: TexTemplateService

    @Suppress("unused")
    private val root = ui {
        verticalLayout {
            val hiddenDownload = anchor {
                element.setAttribute("download", true)
            }
            val hiddenWrapper = div {
                style.set("display", "none")
                add(hiddenDownload)
            }
            add(hiddenWrapper)

            val mathAssignmentForm = MathAssignmentForm()
            mathAssignmentForm.setMathAssignmentConfig(MathAssignmentConfig())
            add(mathAssignmentForm)

            verticalLayout {
                alignItems = END

                button("Create") {
                    onClick {
                        with(mathAssignmentForm.getMathAssignmentConfig()!!) {
                            val assignment = assignmentGenerator.generate(this)

                            val tex = texTemplateService.render(
                                templateName = "assignment.tex.ftl",
                                model = mapOf(
                                    "seed" to seed,
                                    "sections" to assignment.sections,
                                )
                            )

                            val handler = DownloadHandler.fromInputStream {
                                val byteArray = tex.toByteArray()
                                DownloadResponse(
                                    ByteArrayInputStream(byteArray),
                                    "assignment-$seed.tex",
                                    "text/plain;charset=utf-8",
                                    byteArray.size.toLong()
                                )
                            }

                            hiddenDownload.setHref(handler)

                            // trigger browser download
                            hiddenDownload.getElement().callJsFunction("click")
                        }
                    }
                }
            }
        }
    }
}