package dev.calibur.nbtbrowser.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;

import dev.calibur.nbtbrowser.NBTBrowser;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CompoundTag extends Tag {
    
    private final Node icon = new ImageView(new Image(NBTBrowser.class.getResourceAsStream("/icons/box-24.png"), 15, 15, false, false));
    public HashMap<String, Tag> data = new HashMap<String, Tag>();
    
    public CompoundTag() {
        super();
    }
    
    public CompoundTag(String name) {
        super(name);
    }
    
    public CompoundTag(String name, HashMap<String, Tag> map) {
        super(name);
        data = map;
    }
    
    public void setInt(String name, int i) {
        data.put(name, new IntTag(name, i));
    }
    
    @Override
    public void write(DataOutput d) throws IOException {
        for (Tag tag : data.values()) {
            Tag.writeTag(tag, d);
        }
        d.writeByte(TagType.END);
    }

    @Override
    public void read(DataInput d) throws IOException {
        data.clear();
        Tag tag;
        while ((tag = Tag.readTag(d)).getType() != TagType.END) {
            data.put(tag.getName(), tag);
        }
    }

    @Override
    public byte getType() {
        return TagType.COMPOUND;
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
