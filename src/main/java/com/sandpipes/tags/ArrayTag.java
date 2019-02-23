package com.sandpipes.tags;

import javafx.scene.Node;

public abstract class ArrayTag extends Tag {
    public ArrayTag() {
        super();
    }
    
    public ArrayTag(String name) {
        super(name);
    }

    public abstract Node getChildIcon();
    
}
