package com.sandpipes.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.sandpipes.NBTBrowser;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StringTag extends Tag {
    
    public String data;
    private final Node icon = new ImageView(new Image(NBTBrowser.class.getResourceAsStream("/icons/edit-small-caps-24.png"), 15, 15, false, false));

    public StringTag() {
        super();
    }
    
    public StringTag(String name) {
        super(name);
    }

    public StringTag(String name, String i) {
        super(name);
        data = i;
    }
    
    @Override
    public void write(DataOutput d) throws IOException {
        d.writeUTF(data);
    }

    @Override
    public void read(DataInput d) throws IOException {
        data = d.readUTF();
    }

    @Override
    public TagType getType() {
        return TagType.STRING;
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
