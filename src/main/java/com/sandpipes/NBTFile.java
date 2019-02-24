package com.sandpipes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.sandpipes.tags.CompoundTag;
import com.sandpipes.tags.Tag;

public class NBTFile {
    
    public static CompoundTag readNBTFile(File file) {  
        CompoundTag outtag = null;
        try (InputStream input = new FileInputStream(file);
                GZIPInputStream gz = new GZIPInputStream(input);
                DataInputStream dis = new DataInputStream(gz);
                ){
            
            outtag = (CompoundTag)Tag.readTag(dis);       
            
            return outtag;
        } catch (IOException e) {
            Helper.showInfo("Unable to read NBT file.");
        }
        
        return null;
    }
    
    public static CompoundTag readNBTFile(String path) {        
        return readNBTFile(new File(path));
    }
    
    public static int writeNBTFile(CompoundTag tag, File file) {
        try (OutputStream input = new FileOutputStream(file);
                GZIPOutputStream gz = new GZIPOutputStream(input);
                DataOutputStream dis = new DataOutputStream(gz);
                ){
            
            Tag.writeTag(tag, dis); 
            
            return 0;
        } catch (IOException e) {
            Helper.showInfo("Unable to write NBT file.");
        }
        
        return -1;
    }
    
    public static int writeNBTFile(CompoundTag tag, String path) {
        return writeNBTFile(tag, new File(path));
    }
}
