package dev.calibur.nbtbrowser.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;

import dev.calibur.nbtbrowser.NBTBrowser;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IntArrayTag extends ArrayTag {
    
    public ArrayList<Integer> data;
    private final Node icon = new ImageView(new Image(NBTBrowser.class.getResourceAsStream("/icons/edit-code-i-24.png"), 15, 15, false, false));
    private final Node cIcon = new ImageView(new Image(NBTBrowser.class.getResourceAsStream("/icons/document-i-24.png"), 15, 15, false, false));

    public IntArrayTag() {
        super();
    }
    
    public IntArrayTag(String name) {
        super(name);
    }
    
    public IntArrayTag(String name, ArrayList<Integer> array) {
        super(name);
        data = array;
    }
    
    @Override
    public void write(DataOutput d) throws IOException {
        d.writeInt(data.size());
        for (int i = 0; i < data.size(); i++) {
            d.writeInt(data.get(i));
        }
    }

    @Override
    public void read(DataInput d) throws IOException {
        data = new ArrayList<Integer>();
        int len = d.readInt();
        for(int i = 0; i<len; i++) {
            data.add(d.readInt());
        }
    }

    @Override
    public byte getType() {
        return TagType.INT_ARRAY;
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
