package dev.calibur.nbtbrowser.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import javafx.scene.Node;

public class EndTag extends Tag {
    
    public EndTag() {
        super(null);
    }
    
    public EndTag(String name) {
        super(null);
    }
    
    @Override
    public void write(DataOutput d) throws IOException {
        
    }

    @Override
    public void read(DataInput d) throws IOException {
        
    }

    @Override
    public byte getType() {
        return TagType.END;
    }

    @Override
    public Node getIcon() {
        return null;
    }

    @Override
    public Object getRaw() {
        return null;
    }

}
