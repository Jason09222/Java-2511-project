package unsw.loopmania;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
//import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;

import javax.swing.Action;

import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;

public class HeroCastleMenuController {

    private MenuSwitcher gameSwitcher;
    private LoopManiaWorld world;
    private LoopManiaWorldController controller;
    private IntegerProperty goldInt;
    @FXML
    private GridPane inventory;

    public HeroCastleMenuController(LoopManiaWorld world, LoopManiaWorldController controller) {
        this.world = world;
        this.controller = controller;
        //Image swordImage = new Image((new File("src/images/basic_sword.png")).toURI().toString());
        
    }

    public void setGameSwitcher(MenuSwitcher gameSwitcher) {
        this.gameSwitcher = gameSwitcher;
    }
    
    @FXML
    private void switchToGame(){
        gameSwitcher.switchMenu();
    }
 
    @FXML
    private StackPane paneToSell;

    @FXML
    private Label gold;

    @FXML
    private Button exitButton;

    @FXML
    private Label swordPrice;

    @FXML
    private Button purchaseSword;

    @FXML
    private Label staffPrice;

    @FXML
    private Button purchaseStaff;

    @FXML
    private Label stakePrice;

    @FXML
    private Button purchaseStake;

    @FXML
    private Label armourPrice;

    @FXML
    private Button purchaseArmour;

    @FXML
    private Label helmetPrice;

    @FXML
    private Button purchaseHelmet;

    @FXML
    private Button purchaseShield;

    @FXML
    private Label potionPrice;

    @FXML
    private Button purchasePotion;

    @FXML
    private Button sell;

    @FXML
    private StackPane currentGold;


    private int nextAvailableX = 0;
    private int nextAvailableY = 0;


    @FXML
    void updateInventory(ActionEvent event) {
        initialiseInventory();
        //goldInt.set(world.getGolds());
        sell.setText("Sell");
        for(ItemProperty item: world.getUnequippedInventoryItems()) {
            if (item != null) {

                ImageView view = item.onLoadItems();
                inventory.add(view, nextAvailableX, nextAvailableY);
                if (nextAvailableX == LoopManiaWorld.unequippedInventoryWidth - 1) {
                    nextAvailableX = 0;
                    nextAvailableY++;
                } else {
                    nextAvailableX++;
                }
    
    
                view.setOnMouseClicked(e->selected(item));
            }
        }
    }

    void selected(ItemProperty item) {
        initialisePane();
        ImageView view = item.onLoadItems();
        view.setFitHeight(100);
        view.setFitWidth(100);
        paneToSell.getChildren().add(view);
        Label label = new Label(item.getType().name() + ":  $" + item.getPrice());
        paneToSell.getChildren().add(label);
        StackPane.setAlignment(label, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(view, Pos.TOP_CENTER);
    }

    public void initialiseInventory() {
        inventory.getChildren().clear();
        this.nextAvailableX = 0;
        this.nextAvailableY = 0;
    }

    @FXML
    void handleExitButton(ActionEvent event) {
        resetButtons();
        switchToGame();
    }


    private void buyItem(ItemType itemType) {
        world.addGold(-1 * world.getItemPrice(itemType));
        //goldInt.set(world.getGolds());
        //gold.textProperty().bind(goldInt.asString());
        if (itemType == ItemType.HEALTHPOTION) {
            world.addPotion(1);

        }
        else {
            ItemProperty item = world.addUnequippedItem(itemType);
            controller.onLoad(item);
        }

    }

    @FXML
    void handlePurchaseArmour(ActionEvent event) {
        if (world.getGold().get() >= world.getItemPrice(ItemType.ARMOUR)) {
            buyItem(ItemType.ARMOUR);
        }
        
    }

    @FXML
    void handlePurchaseHelmet(ActionEvent event) {
        if (world.getGold().get() >= world.getItemPrice(ItemType.HELMET)) {
            buyItem(ItemType.HELMET);
        }
        
    }

    @FXML
    void handlePurchasePotion(ActionEvent event) {
        if (world.getGold().get() >= world.getItemPrice(ItemType.HEALTHPOTION)) {
            buyItem(ItemType.HEALTHPOTION);
        }
        
    }

    @FXML
    void handlePurchaseShield(ActionEvent event) {
        if (world.getGold().get() >= world.getItemPrice(ItemType.SHIELD)) {
            buyItem(ItemType.SHIELD);
        }
        
    }

    @FXML
    void handlePurchaseStaff(ActionEvent event) {
        if (world.getGold().get() >= world.getItemPrice(ItemType.STAFF)) {
            buyItem(ItemType.STAFF);
        }
        
    }

    @FXML
    void handlePurchaseStake(ActionEvent event) {
        if (world.getGold().get() >= world.getItemPrice(ItemType.STAKE)) {
            buyItem(ItemType.STAKE);
        }
        
    }

    @FXML
    void handlePurchaseSword(ActionEvent event) {
        if (!purchaseSword.getText().equals("\u2713") && world.getGold().get() >= world.getItemPrice(ItemType.SWORD)) {
            buyItem(ItemType.SWORD);
            purchaseSword.setText("\u2713");
        }
        
    }


    @FXML
    public void initialize() {
        goldInt = world.getGold();
        gold = new Label(String.valueOf(goldInt.get()));
        gold.textProperty().bind(goldInt.asString());
        gold.setTextFill(Color.ORANGE);
        gold.setFont(new Font("Cambria", 40));
        //goldInt.set(world.getGolds());
        currentGold.getChildren().add(gold);
        StackPane.setAlignment(gold, Pos.CENTER_RIGHT);

    }



    @FXML
    public void sellItem(ActionEvent event) {
        if (paneToSell.getChildren().size() == 0) return;
        String text = "";
        for (Node each: paneToSell.getChildren()) {
            if (each instanceof Label) {
                Label label = (Label)each;
                text = label.getText().split(":")[0];
            }
        }

        removeItem(text);
        sell.setText("\u2713");
        //goldInt.set(world.getGolds());
    }

    public void initialisePane() {
        paneToSell.getChildren().clear();
    }

    public void removeItem(String text) {
        for (ItemProperty item: world.getUnequippedInventoryItems()) {
            if(item.getType().name().equals(text)) {
                world.getUnequippedInventoryItems().remove(item);
                controller.unLoad(item);
                //world.addGold(item.getPrice());
                goldInt.set(goldInt.get() + item.getPrice());
                item.destroy();
                return;
            }
        }
    }
    public void resetButtons() {
        sell.setText("Sell");
        purchaseSword.setText("Buy");
    }

}
