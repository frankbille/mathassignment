package dk.frankbille.mathassignment

import freemarker.template.Configuration
import org.springframework.stereotype.Service
import java.io.StringWriter

@Service
class TexTemplateService(
    private val freemarker: Configuration
) {

    fun render(templateName: String, model: Map<String, Any?>): String {
        val template = freemarker.getTemplate(templateName)
        return StringWriter().use { out ->
            template.process(model, out)
            out.toString()
        }
    }
}