package com.vaadin.starter.custom;

import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.dnd.DropEffect;
import com.vaadin.flow.component.dnd.DropTarget;
import com.vaadin.flow.component.tabs.Tab;


public class CustomTab extends Tab implements DragSource<CustomTab>, DropTarget<CustomTab> {
    private CustomTab dragSource;
    private int tabId;
    private int sourceId;


    public CustomTab() {
        super();
    }


    public void setDragActive() {
        setDraggable(true);
        setActive(true);


        addDragStartListener(event -> getParent().ifPresent(tabs -> {
            setDropEffect(DropEffect.MOVE);
            ((CustomTabs) tabs).setTabTargetId(this.getElement().getNode().getId());
            ((CustomTabs) tabs).setParentId(this.getParent().get().getElement().getNode().getId());
        }));


        getElement().addEventListener("dragenter", e -> handleDragAndDrop());

        getElement().addEventListener("touchstart", e -> getParent().ifPresent(tabs -> {
            setDropEffect(DropEffect.MOVE);

            ((CustomTabs) tabs).setTabTargetId(this.getElement().getNode().getId());
            ((CustomTabs) tabs).setParentId(this.getParent().get().getElement().getNode().getId());
        }));

        addDragEndListener(event -> getParent().ifPresent(tabs -> ((CustomTabs) tabs).setParentId(0)));

    }

    private void handleDragAndDrop() {
        if (getParent().isPresent()) {
            CustomTabs tabs = (CustomTabs) getParent().get();
            int parentId = this.getParent().get().getElement().getNode().getId();
            int lastParentId = tabs.getParentId();

            if (this.isDraggable()) {
                if (parentId == lastParentId) {
                    tabs.getChildren().forEach(tab -> {
                        tabId = tab.getElement().getNode().getId();
                        sourceId = tabs.getTabTargetId();

                        if (sourceId == tabId) {
                            dragSource = (CustomTab) tab;
                        }
                    });
                    tabs.moveDragSource(dragSource, this);
                }

                if (parentId != lastParentId) {
                    setDropEffect(DropEffect.NONE);
                }
            }
        }
    }

}
