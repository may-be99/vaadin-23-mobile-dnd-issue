package com.vaadin.starter.custom;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.tabs.Tabs;


@Tag("drawer-menu")
public class DrawerMenu extends Component implements HasComponents {

    private final CustomTabs tabs;

    public DrawerMenu() {
        this.tabs = new CustomTabs();
        this.tabs.setOrientation(Tabs.Orientation.VERTICAL);

        add(this.tabs);
    }

    public void setTabs(CustomTab... tabs ) {
        this.tabs.add(tabs);
    }

    public CustomTabs getTabs() {
        return tabs;
    }
}
