package dev.calibur.nbtbrowser;

import java.io.File;

import dev.calibur.nbtbrowser.tags.CompoundTag;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class BrowserGUI extends Application {

    private TreeView<String> tree = new TreeView<String>();
    private Stage stage;
    public static final Node rootIcon = new ImageView(new Image(NBTBrowser.class.getResourceAsStream("/icons/stick.png"), 15, 15, false, false));
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        stage.getIcons().add(new Image(NBTBrowser.class.getResourceAsStream("/icons/stick.png"), 15, 15, false, false));
        stage.setTitle("NBTBrowser");        
        
        BorderPane root = new BorderPane();
        
        TreeCreator.setDefaultTree(tree);
        
        GridPane gp = new GridPane();
        gp.add(createMenuBar(), 0, 0);
        gp.add(createActionBar(), 0, 1);
        
        root.setTop(gp);
        root.setCenter(tree);
        
        Scene scene = new Scene(root, 400, 450);
        
        setDropFileEvent();
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    private void setDropFileEvent() {
        tree.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getGestureSource() != tree && event.getDragboard().hasFiles())
                    event.acceptTransferModes(TransferMode.COPY);
                
                event.consume();
            }
        });
        
        tree.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    File file = db.getFiles().get(0);
                    CompoundTag ctag = NBTFile.readNBTFile(file);
                    if(ctag == null) return;
                   
                    TreeCreator.setUpTree(file.getName(), tree, ctag);
                    success = true;
                }
                event.setDropCompleted(success);
                
                event.consume();
             }
        });        
    }

    public MenuBar createActionBar() {
        Menu menuFile = new Menu("Edit", Icon.editIcon);  
        menuFile.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                
            }
        });
        
        MenuBar menuBar = new MenuBar();
        
        menuBar.getMenus().addAll(menuFile);
        
        return menuBar;
    }
    
    public MenuBar createMenuBar() {
        Menu menuFile = new Menu("File");
        MenuItem open = new MenuItem("Open");
        open.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open NBT File");
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    CompoundTag ctag = NBTFile.readNBTFile(file);
                    if(ctag == null) return;
                    
                    TreeCreator.setUpTree(file.getName(), tree, ctag);
                }
                
                event.consume();
            }
        });        
     
        MenuItem save = new MenuItem("Save");
        save.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save NBT File");
                File file = fileChooser.showSaveDialog(stage);
                if (file != null) {
                    //NBTFile.writeNBTFile(c, file);
                    
                    //TreeCreator.setUpTree(file.getName(), tree, ctag);
                }
                
                event.consume();
            }
        });    
        
        
        menuFile.getItems().addAll(open, save);
        
        Menu menuHelp = new Menu("Help");

        MenuBar menuBar = new MenuBar();
        //menuBar.useSystemMenuBarProperty().set(true);
        
        menuBar.getMenus().addAll(menuFile, menuHelp);
        
        return menuBar;
    }
    
    public static void startGUI(String[] args) {
        launch(args);
    }

}
