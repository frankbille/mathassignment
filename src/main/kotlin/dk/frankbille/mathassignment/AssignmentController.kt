package dk.frankbille.mathassignment

import org.springframework.http.HttpHeaders.CONTENT_DISPOSITION
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.nio.charset.StandardCharsets.UTF_8

@RestController
class AssignmentController(
    private val texTemplateService: TexTemplateService,
    private val generator: AssignmentGenerator // your own service
) {

    @GetMapping("/assignments/tex")
    fun assignmentTex(
        @RequestParam add: Int,
        @RequestParam sub: Int,
        @RequestParam mul: Int,
        @RequestParam div: Int,
        @RequestParam seed: Long = System.currentTimeMillis(),
    ): ResponseEntity<ByteArray> {
        val assignment = generator.generate(add, sub, mul, div, seed)

        val tex = texTemplateService.render(
            templateName = "assignment.tex.ftl",
            model = mapOf(
                "seed" to seed,
                "sections" to assignment.sections,
            )
        )

        val bytes = tex.toByteArray(UTF_8)

        return ok()
            .header(CONTENT_DISPOSITION, """attachment; filename="assignment.tex"""")
            .contentType(MediaType.parseMediaType("application/x-tex; charset=utf-8"))
            .body(bytes)
    }
}

