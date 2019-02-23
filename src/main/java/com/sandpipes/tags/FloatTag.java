package com.sandpipes.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.sandpipes.NBTBrowser;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FloatTag extends Tag {

    public float data;
    private final Node icon = new ImageView(new Image(NBTBrowser.class.getResourceAsStream("/icons/document-f-24.png"), 15, 15, false, false));

    public FloatTag() {
        super();
    }
    
    public FloatTag(String name) {
        super(name);
    }
    
    public FloatTag(String name, float f) {
        super(name);
        data = f;
    }
    
    @Override
    public void write(DataOutput d) throws IOException {
        d.writeFloat(data);
    }

    @Override
    public void read(DataInput d) throws IOException {
        data = d.readFloat();
    }

    @Override
    public byte getType() {
        return TagType.FLOAT;
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
