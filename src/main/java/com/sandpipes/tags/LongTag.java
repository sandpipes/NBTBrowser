package com.sandpipes.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.sandpipes.NBTBrowser;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LongTag extends Tag {

    public long data;
    private final Node icon = new ImageView(new Image(NBTBrowser.class.getResourceAsStream("/icons/document-l-24.png"), 15, 15, false, false));

    public LongTag() {
        super();
    }
    
    public LongTag(String name) {
        super(name);
    }
    
    public LongTag(String name, long i) {
        super(name);
        data = i;
    }
    
    @Override
    public void write(DataOutput d) throws IOException {
        d.writeLong(data);
    }

    @Override
    public void read(DataInput d) throws IOException {
        data = d.readLong();
    }

    @Override
    public byte getType() {
        return TagType.LONG;
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
