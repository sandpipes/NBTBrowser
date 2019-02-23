 package com.sandpipes.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.sandpipes.NBTBrowser;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DoubleTag extends Tag {

    private final Node icon = new ImageView(new Image(NBTBrowser.class.getResourceAsStream("/icons/document-d-24.png"), 15, 15, false, false));
    public double data;
    
    public DoubleTag() {
        super();
    }
    
    public DoubleTag(String name) {
        super(name);
    }
    
    public DoubleTag(String name, double d) {
        super(name);
        data = d;
    }
    
    @Override
    public void write(DataOutput d) throws IOException {
        d.writeDouble(data);
    }

    @Override
    public void read(DataInput d) throws IOException {
        data = d.readDouble();
    }

    @Override
    public TagType getType() {
        return TagType.DOUBLE;
    }

    @Override
    public Node getIcon() {
        return icon;
    }

    @Override
    public Object getRaw() {
        return data;
    }

}
