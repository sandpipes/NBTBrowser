package com.sandpipes.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import javafx.scene.Node;

public abstract class Tag {
    
    private String name;
    
    public Tag() {
        this.name = "";
    }
    
    public Tag(String name) {
        this.name = name;
    } 
    
    public abstract void write(DataOutput d) throws IOException;
    public abstract void read(DataInput d) throws IOException;
    public abstract TagType getType();
    public abstract Node getIcon();
    public abstract Object getRaw();
    
    public String getName() {
        return name;
    }
    
    public static Tag readTag(DataInput d) throws IOException {
        byte type = d.readByte();
        if (type == 0) return new EndTag();

        String name = d.readUTF();

        Tag tag = createTag(type, name);

        tag.read(d);
        return tag;
    }
    
    public static void writeTag(Tag tag, DataOutput d) throws IOException {
        d.writeByte(TagType.getType(tag.getType()));
        if(tag.getType() == TagType.END) return;
        d.writeUTF(tag.getName());
        tag.write(d);
    }
    
    public boolean isArray() {
        return getType() == TagType.BYTE_ARRAY 
                || getType() == TagType.INT_ARRAY
                || getType() == TagType.LONG_ARRAY;
    }
    
    @SuppressWarnings("rawtypes")
    public static Tag createTag(byte type, String name) {
        switch(TagType.getType(type)) {
            case END:
                return new EndTag(name);
            case BYTE:
                return new ByteTag(name);
            case BYTE_ARRAY:
                return new ByteArrayTag(name);
            case COMPOUND:
                return new CompoundTag(name);
            case DOUBLE:
                return new DoubleTag(name);
            case FLOAT:
                return new FloatTag(name);
            case INT:
                return new IntTag(name);
            case INT_ARRAY:
                return new IntArrayTag(name);
            case LIST:
                return new ListTag(name);
            case LONG:
                return new LongTag(name);
            case LONG_ARRAY:
                return new LongArrayTag(name);
            case SHORT:
                return new ShortTag(name);
            case STRING:
                return new StringTag(name);
            default:
                return null;
        }
    }
    
    public static Tag createTag(TagType type, String name) {
        return createTag(TagType.getType(type), name);
    }
}
