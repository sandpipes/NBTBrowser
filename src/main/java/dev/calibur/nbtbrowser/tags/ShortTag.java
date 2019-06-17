package dev.calibur.nbtbrowser.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import dev.calibur.nbtbrowser.NBTBrowser;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShortTag extends Tag {
    
    public short data;
    private final Node icon = new ImageView(new Image(NBTBrowser.class.getResourceAsStream("/icons/document-s-24.png"), 15, 15, false, false));

    public ShortTag() {
        super();
    }
    
    public ShortTag(String name) {
        super(name);
    }
    
    public ShortTag(String name, short i) {
        super(name);
        data = i;
    }
    
    @Override
    public void write(DataOutput d) throws IOException {
        d.writeShort(data);
    }

    @Override
    public void read(DataInput d) throws IOException {
        data = d.readShort();
    }

    @Override
    public byte getType() {
        return TagType.SHORT;
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
