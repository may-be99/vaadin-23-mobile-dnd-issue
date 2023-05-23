package com.vaadin.starter.custom;

import com.vaadin.flow.component.tabs.Tabs;

public class CustomTabs extends Tabs {


    private int tabTargetId;
    private int parentId;

    public CustomTabs() {
        super();
    }

    public void setDraggable() {
        getChildren().forEach(child -> {
            if (child instanceof CustomTab) {
                ((CustomTab) child).setDragActive();
            }
        });
    }

    public void setTabTargetId(int tabTargetId) {
        this.tabTargetId = tabTargetId;
    }
    public int getTabTargetId() {
        return tabTargetId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getParentId() {
        return parentId;
    }

    public void moveDragSource(CustomTab dragSource, CustomTab dropSource) {
        replace(dragSource, dropSource);
        setSelectedTab(dragSource);
    }
}
