package com.sandpipes.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.sandpipes.NBTBrowser;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IntTag extends Tag {
    
    public int data;
    private final Node icon = new ImageView(new Image(NBTBrowser.class.getResourceAsStream("/icons/document-i-24.png"), 15, 15, false, false));

    public IntTag() {
        super();
    }
    
    public IntTag(String name) {
        super(name);
    }
    
    public IntTag(String name, int i) {
        super(name);
        data = i;
    }
    
    @Override
    public void write(DataOutput d) throws IOException {
        d.writeInt(data);
    }

    @Override
    public void read(DataInput d) throws IOException {
        data = d.readInt();
    }

    @Override
    public byte getType() {
        return TagType.INT;
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
