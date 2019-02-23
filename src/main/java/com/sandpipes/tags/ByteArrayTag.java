package com.sandpipes.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;

import com.sandpipes.NBTBrowser;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ByteArrayTag extends ArrayTag {
    
    private final Node cIcon = new ImageView(new Image(NBTBrowser.class.getResourceAsStream("/icons/document-b-24.png"), 15, 15, false, false));
    private final Node icon = new ImageView(new Image(NBTBrowser.class.getResourceAsStream("/icons/edit-code-24.png"), 15, 15, false, false));
    public ArrayList<Byte> data;
    
    public ByteArrayTag() {
        super();
    }
    
    public ByteArrayTag(String name) {
        super(name);
    }
    
    public ByteArrayTag(String name, ArrayList<Byte> array) {
        super(name);
        data = array;
    }
    
    @Override
    public void write(DataOutput d) throws IOException {
        d.writeInt(data.size());
        for (int i = 0; i < data.size(); i++) {
            d.writeByte(data.get(i));
        }
    }

    @Override
    public void read(DataInput d) throws IOException {
        data = new ArrayList<Byte>();
        int len = d.readInt();
        for(int i = 0; i<len; i++) {
            data.add(d.readByte());
        }
    }

    @Override
    public byte getType() {
        return TagType.BYTE_ARRAY;
    }

    @Override
    public Node getIcon() {
        return icon;
    }

    @Override
    public Object getRaw() {
        return data;
    }

    @Override
    public Node getChildIcon() {
        return cIcon;
    }

}
