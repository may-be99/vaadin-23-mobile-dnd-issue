package com.vaadin.starter.skeleton;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.custom.CustomTab;
import com.vaadin.starter.custom.DrawerMenu;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends HorizontalLayout {

    public MainView() {
        VerticalLayout greetings = new VerticalLayout();
        // Use TextField for standard text input
        TextField textField = new TextField("Your name");

        // Button click listeners can be defined as lambda expressions
        Button button = new Button("Say hello",
                e -> Notification.show(new GreetService().greet(textField.getValue())));

        // Theme variants give you predefined extra styles for components.
        // Example: Primary button is more prominent look.
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.
        button.addClickShortcut(Key.ENTER);

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("centered-content");

        greetings.add(textField, button);

        VerticalLayout drawer = new VerticalLayout();
        drawer.addClassName("drawer");

        DrawerMenu drawerMenu = new DrawerMenu();
        CustomTab[] menuItems = new CustomTab[5];
        for (int i = 0; i < 5; i++) {
            CustomTab item = new CustomTab();
            item.addClassName("tab");
            Span text = new Span("Tab " + i);
            Anchor anchor = new Anchor();
            anchor.add(text);
            item.add(anchor);
            menuItems[i] = item;
        }
        drawerMenu.setTabs(menuItems);
        drawerMenu.getTabs().setDraggable();
        drawerMenu.getChildren().forEach(child -> {
            if (child instanceof CustomTab) {
                ((CustomTab) child).setDragActive();
            }
        });

        drawer.add(drawerMenu);

        add(drawer, greetings);
        expand(greetings);
    }
}
