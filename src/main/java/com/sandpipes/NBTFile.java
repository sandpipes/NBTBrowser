package com.sandpipes;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import com.sandpipes.tags.CompoundTag;
import com.sandpipes.tags.Tag;

public class NBTFile {
    
    public static CompoundTag readFile(String path) {        
        InputStream input;
        DataInputStream dis;
        try {
            input = new FileInputStream(path);
            dis = new DataInputStream(new BufferedInputStream(new GZIPInputStream(input)));
            
            return (CompoundTag)Tag.readTag(dis);
        } catch (IOException e) {
            Helper.showInfo("Unable to open file.");
        }
        
        return null;
    }
}
