package com.sandpipes;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Icon {
    public static final Node editIcon = new ImageView(new Image(NBTBrowser.class.getResourceAsStream("/icons/selection-input-24.png"), 15, 15, false, false));
}
