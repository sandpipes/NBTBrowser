package com.sandpipes.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.sandpipes.NBTBrowser;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ByteTag extends Tag {
    
    private final Node icon = new ImageView(new Image(NBTBrowser.class.getResourceAsStream("/icons/document-b-24.png"), 15, 15, false, false));
    private byte data;
    
    public ByteTag() {
        super();
    }
    
    public ByteTag(String name) {
        super(name);
    }
    
    public ByteTag(String name, byte val) {
        super(name);
        this.data = val;
    }
    
    public byte get() {
        return data;
    }

    public void set(byte b) {
        data = b;      
    }
    
    @Override
    public void write(DataOutput d) throws IOException {
        d.writeByte(data);
    }

    @Override
    public void read(DataInput d) throws IOException {
        data = d.readByte();
    }

    @Override
    public TagType getType() {
        return TagType.BYTE;
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
