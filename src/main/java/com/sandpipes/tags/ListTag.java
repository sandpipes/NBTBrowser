package com.sandpipes.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sandpipes.NBTBrowser;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ListTag<T extends Tag> extends Tag {

    public List<T> data = new ArrayList<T>();
    public byte listType;
    private final Node icon = new ImageView(new Image(NBTBrowser.class.getResourceAsStream("/icons/edit-list-24.png"), 15, 15, false, false));

    public ListTag() {
        super();
    }
    
    public ListTag(String name) {
        super(name);
    }
    
    @Override
    public void write(DataOutput d) throws IOException {
        if (data.size() > 0) listType = data.get(0).getType();
        else listType = TagType.BYTE;

        d.writeByte(listType);
        d.writeInt(data.size());
        for (int i = 0; i < data.size(); i++)
            data.get(i).write(d);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void read(DataInput d) throws IOException {
        listType = d.readByte();
        int size = d.readInt();

        data = new ArrayList<T>();
        for (int i = 0; i < size; i++) {
            Tag tag = Tag.createTag(listType, null);
            tag.read(d);
            data.add((T) tag);
        }        
    }

    @Override
    public byte getType() {
        return TagType.LIST;
    }

    @Override
    public Node getIcon() {
        return icon;
    }

    @Override
    public Object getRaw() {
        return data;
    }
    
    public Node getChildIcon() {
        return icon;
    }
    

}
