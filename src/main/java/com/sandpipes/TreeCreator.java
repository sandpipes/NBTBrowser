package com.sandpipes;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sandpipes.tags.ArrayTag;
import com.sandpipes.tags.CompoundTag;
import com.sandpipes.tags.ListTag;
import com.sandpipes.tags.Tag;
import com.sandpipes.tags.TagType;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class TreeCreator {
    
    public static void setDefaultTree(TreeView<String> tree) {
        if(tree == null)
            tree = new TreeView<String>();
        else
            tree.setRoot(null);
        
        TreeItem<String> root = new TreeItem<String>("Default", BrowserGUI.rootIcon);      
        TreeItem<String> item = new TreeItem<String> ("null");            
        root.getChildren().add(item);
        
        root.setExpanded(true);
        
        tree.setRoot(root);
    }

    @SuppressWarnings("rawtypes")
    public static void setUpTree(String fname, TreeView<String> tree,  CompoundTag rootTag) {
        TreeItem<String> root = new TreeItem<String>(fname, BrowserGUI.rootIcon);      

        Iterator it = rootTag.data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Tag tag = (Tag)pair.getValue();
            if(tag.getType() == TagType.END) break;
            
            TreeItem<String> item = null; 
            
            if(tag.getType() == TagType.COMPOUND)
                item = addCompoundTree(null, (CompoundTag)tag);
            else if(tag.getType() == TagType.LIST)
                item = addListTree(null, (ListTag)tag);
            else if(!tag.isArray())
                item = new TreeItem<String> ((String) pair.getKey() + ": " + ((Tag)pair.getValue()).getRaw(), ((Tag)pair.getValue()).getIcon()); 
            else
                item = addArrayTree(null, (ArrayTag)tag);
            
            root.getChildren().add(item);

        }
        
        root.setExpanded(true);

        tree.setRoot(root);
    }
    
    @SuppressWarnings("rawtypes")
    public static TreeItem<String> addArrayTree(TreeItem<String> parent, ArrayTag tag) {
        TreeItem<String> root = new TreeItem<String> (tag.getName(), tag.getIcon());
        
        if(tag.getRaw() == null) return root;
        
        List array = (List)tag.getRaw();

        for(int i = 0; i<array.size(); i++) {
            Object o = array.get(i);
            TreeItem<String> item = new TreeItem<String> ("" + o.toString(), tag.getChildIcon());
            root.getChildren().add(item);
        }
        
        if(parent != null) parent.getChildren().add(root);
        
        return root;
    }
    
    @SuppressWarnings("rawtypes")
    public static TreeItem<String> addListTree(TreeItem<String> parent, ListTag tag) {
        TreeItem<String> root = new TreeItem<String> (tag.getName(), tag.getIcon());

        Iterator it = tag.data.iterator();
        while (it.hasNext()) {
            Tag t = (Tag) it.next();
            if(t.getType() == TagType.END) break;
            
            switch(tag.listType) {
                case TagType.LIST:
                    addListTree(root, (ListTag) t); 
                    break;
                case TagType.COMPOUND:
                    addCompoundTree(root, (CompoundTag) t);
                    break;
                case TagType.LONG_ARRAY:
                case TagType.INT_ARRAY:
                case TagType.BYTE_ARRAY:
                    addArrayTree(root, (ArrayTag) t);
                    break;
                default:
                    TreeItem<String> item = new TreeItem<String> ("" + t.getRaw(), t.getIcon());
                    root.getChildren().add(item);
                    break;
            }
        }                
        if(parent != null) parent.getChildren().add(root);

        return root;
    }
    
    @SuppressWarnings("rawtypes")
    public static TreeItem<String> addCompoundTree(TreeItem<String> parent, CompoundTag tag) {
        String name = "";
        if(tag.getName() == null)
            name = tag.data.entrySet().size() + " entries";
        else
            name = tag.getName() + ": " + tag.data.entrySet().size() + " entries";
        
        TreeItem<String> root = new TreeItem<String> (name, tag.getIcon());

        Iterator it = tag.data.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            Tag child = (Tag) entry.getValue();
            
            if(child.getType() == TagType.END) break;
            
            switch(child.getType()) {
                case TagType.LIST:
                    addListTree(root, (ListTag) child); 
                    break;
                case TagType.COMPOUND:
                    addCompoundTree(root, (CompoundTag) child);
                    break;
                case TagType.LONG_ARRAY:
                case TagType.INT_ARRAY:
                case TagType.BYTE_ARRAY:
                    addArrayTree(root, (ArrayTag) child);
                    break;
                default:
                    TreeItem<String> item = new TreeItem<String> (child.getName() + ": " + child.getRaw(), child.getIcon());
                    root.getChildren().add(item);
                    break;
            }
        }                
        
        if(parent != null) parent.getChildren().add(root);

        return root;
    }
}
