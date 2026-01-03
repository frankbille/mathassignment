package dk.frankbille.mathassignment.ui

import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.html.H1
import com.vaadin.flow.router.Layout

@Layout
class MainLayout : AppLayout() {

    init {
        val title = H1("Math Assignment")
        addToNavbar(title)
    }
}