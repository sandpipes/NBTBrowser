package com.sandpipes.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;

import com.sandpipes.NBTBrowser;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LongArrayTag extends ArrayTag {
    
    public ArrayList<Long> data;
    //TODO Add long array icon
    private final Node icon = new ImageView(new Image(NBTBrowser.class.getResourceAsStream("/icons/edit-code-i-24.png"), 15, 15, false, false));
    private final Node cIcon = new ImageView(new Image(NBTBrowser.class.getResourceAsStream("/icons/document-l-24.png"), 15, 15, false, false));

    public LongArrayTag() {
        super();
    }
    
    public LongArrayTag(String name) {
        super(name);
    }

    public LongArrayTag(String name, ArrayList<Long> array) {
        super(name);
        data = array;
    }
    
    @Override
    public void write(DataOutput d) throws IOException {
        d.writeInt(data.size());
        for (int i = 0; i < data.size(); i++) {
            d.writeLong(data.get(i));
        }
    }

    @Override
    public void read(DataInput d) throws IOException {
        data = new ArrayList<Long>();
        int len = d.readInt();
        for(int i = 0; i<len; i++) {
            data.add(d.readLong());
        }
    }

    @Override
    public byte getType() {
        return TagType.LONG_ARRAY;
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
