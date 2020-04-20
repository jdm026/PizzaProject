package application;

import javafx.application.Application;



import javafx.stage.Stage;



import javafx.scene.Scene;



import javafx.scene.layout.BorderPane;



import javafx.scene.layout.GridPane;



import javafx.scene.control.ScrollPane;



import javafx.scene.control.Label;



import javafx.scene.control.CheckBox;



import javafx.scene.control.Button;



import javafx.scene.control.TextField;



import javafx.scene.control.TextArea;



import javafx.event.ActionEvent;



import javafx.event.EventHandler;



import javafx.geometry.Insets;



import javafx.geometry.Pos;







public class OrderMenu extends Application {







	public void start(Stage stage) {











		// Border Pane and Grid Panes For Each Node



		BorderPane bPane = new BorderPane();



		GridPane gTop = new GridPane();



		GridPane gCenter = new GridPane();



		GridPane gBottom = new GridPane();



		GridPane gSize = new GridPane();



		GridPane gCrust = new GridPane();



		GridPane gMTop = new GridPane();



		GridPane gMeatx = new GridPane();



		GridPane gVeg = new GridPane();



		GridPane gVegX = new GridPane();



		GridPane gBev = new GridPane();



		GridPane gDop = new GridPane();



		GridPane gCheese = new GridPane();







		gTop.setVgap(10);



		gTop.setAlignment(Pos.TOP_CENTER);







		gCenter.setHgap(10);



		gCenter.setVgap(10);



		gCenter.setAlignment(Pos.CENTER);







		gBottom.setHgap(10);



		gBottom.setAlignment(Pos.CENTER);







		// Customer Labels



		Label lblName = new Label("[Name]");



		Label lblPhone = new Label("[Phone Number]");



		Label lblAddress = new Label("[Address]");







		// Section Labels



		Label lblSize = new Label("Size: ");



//		Label lblMeatx = new Label("Extra Toppings: ");



		Label lblCrust = new Label("Crust: ");	



//		Label lblVegX = new Label("Extra Veggetable\nToppings");



		Label lblRqst = new Label("Special Requests: ");



		Label lblDop = new Label("Delivery or Pick-up: ");







		TextArea taRqst = new TextArea();



		taRqst.setPrefHeight(10);



		taRqst.setPrefWidth(50);



		taRqst.setWrapText(true);







		// Pizza Size Check Box



		Label lblPriceS = new Label("$4.00");



		Label lblPriceM = new Label("$6.00");



		Label lblPriceL = new Label("$8.00");
		
		Label lblPriceXL = new Label("$10.00");







		CheckBox cbSmall = new CheckBox("Small\n(4 Slices)\n" + lblPriceS.getText());



		CheckBox cbMedium = new CheckBox("Medium\n(6 Slices)\n"+ lblPriceM.getText());



		CheckBox cbLarge = new CheckBox("Large\n(8 Slices)\n" + lblPriceL.getText());
		
		CheckBox cbXLarge = new CheckBox("X-Large\n(10 Slices)\n" + lblPriceXL.getText());







		// Add Check Box to Pane



		gSize.add(cbSmall, 0, 0);



		gSize.add(cbMedium, 3, 0);



		gSize.add(cbLarge, 6, 0);
		
		gSize.add(cbXLarge, 9, 0);



		gSize.setHgap(2);



		gSize.setVgap(2);







		// Crust CheckBox







		Label lblPriceH = new Label("+$1.50");



		Label lblPriceT = new Label("+$2.00");



		Label lblPriceSC = new Label("+$3.00");







		CheckBox cbOrig = new CheckBox("Original");	 



		CheckBox cbHt = new CheckBox("Hand Toss\n" + lblPriceH.getText());



		CheckBox cbThin = new CheckBox("Thin\n" + lblPriceT.getText());



		CheckBox cbSc = new CheckBox("Stuffed\n" + lblPriceSC.getText());







		// Add Check Box to Crust pane



		gCrust.add(cbOrig, 0, 0);



		gCrust.add(cbHt, 3, 0);



		gCrust.add(cbThin, 6, 0);



		gCrust.add(cbSc, 0, 2);



		gCrust.setVgap(2);



		gCrust.setHgap(2);







		Label lblC = new Label("Cheese: ");







		Label lblCheeseP = new Label("$0.50");



		Label lblXCheeseP = new Label("$1.00");



		Label lblMozP = new Label("$1.50");



		Label lblParmP = new Label("$1.50");







		CheckBox cbCheese = new CheckBox("Cheese");



		CheckBox cbXCheese = new CheckBox("Extra Cheese\n+" + lblXCheeseP.getText());



		CheckBox cbMoz = new CheckBox("Mozarella\n+" + lblMozP.getText());



		CheckBox cbParm = new CheckBox("Parmessean\n+" + lblParmP.getText());







		gCheese.add(cbCheese, 0, 0);



		gCheese.add(cbXCheese, 3, 0);



		gCheese.add(cbMoz, 6, 0);



		gCheese.add(cbParm, 0, 1);











		// Meat Topping's Check Box



		CheckBox cbPep = new CheckBox("Pepperoni");



		CheckBox cbChees = new CheckBox("Three-Cheese");



		CheckBox cbSaus = new CheckBox("Sausage");



		CheckBox cbChic = new CheckBox("Chicken");



		CheckBox cbBeef = new CheckBox("Beef");



		CheckBox cbMeat = new CheckBox("Meat Ball");



		CheckBox cbHam = new CheckBox("Ham");



		CheckBox cbBacon = new CheckBox("Bacon");







		Label lblTwoP = new Label("$2.00");



		Label lblThreeP = new Label("$3.00");



		Label lblMTop = new Label("Meat Toppings: \n2 toppings: +" + lblTwoP.getText() + "\n3 toppings: +" + lblThreeP.getText());







		// Add Check Box to Meat Topping pane



		gMTop.add(cbPep, 0, 0);



		gMTop.add(cbChees, 3, 0);



		gMTop.add(cbSaus, 6, 0);



		gMTop.add(cbChic, 0, 1);



		gMTop.add(cbBeef, 3, 1);



		gMTop.add(cbMeat, 6, 1);



		gMTop.add(cbHam, 0, 2);



		gMTop.add(cbBacon, 3, 2);



		gMTop.setHgap(2);



		gMTop.setVgap(2);







	/*



		// Extra Meat Topping's



		CheckBox cbXPep = new CheckBox("Pepperoni");



		CheckBox cbXChees = new CheckBox("Cheese");



		CheckBox cbXSaus = new CheckBox("Sausage");



		CheckBox cbXChic = new CheckBox("Chicken");



		CheckBox cbXBeef = new CheckBox("Beef");



		CheckBox cbXMeat = new CheckBox("Meat Ball");



		CheckBox cbXHam = new CheckBox("Ham");



		CheckBox cbXBacon = new CheckBox("Bacon");







		// Add Check Box to Extra Topping's pane



		gMeatx.add(cbXPep, 0, 0);



		gMeatx.add(cbXChees, 3, 0);



		gMeatx.add(cbXSaus, 6, 0);



		gMeatx.add(cbXChic, 0, 1);



		gMeatx.add(cbXBeef, 3, 1);



		gMeatx.add(cbXMeat, 6, 1);



		gMeatx.add(cbXHam, 0, 2);



		gMeatx.add(cbXBacon, 3, 2);



		gMeatx.setHgap(2);



		gMeatx.setVgap(2);



	*/	



		// Veggie Topping Check Box



		CheckBox cbRedO = new CheckBox("Red Onion");



		CheckBox cbBlkO = new CheckBox("Black Olive");



		CheckBox cbMush = new CheckBox("Mushroom");



		CheckBox cbSpin = new CheckBox("Spinach");



		CheckBox cbGPep = new CheckBox("Green Pepper");



		CheckBox cbBPep = new CheckBox("Banana Pepper");



		CheckBox cbPine = new CheckBox("Pineapple");



		CheckBox cbJalp = new CheckBox("Jalapeno Pepper");



		CheckBox cbToma = new CheckBox("Tomatoes");







		// Add Check Box to 



		gVeg.add(cbRedO, 0, 0);



		gVeg.add(cbBlkO, 3, 0);



		gVeg.add(cbMush, 6, 0);



		gVeg.add(cbSpin, 0, 1);



		gVeg.add(cbGPep, 3, 1);



		gVeg.add(cbBPep, 6, 1);



		gVeg.add(cbPine, 0, 2);



		gVeg.add(cbJalp, 3, 2);



		gVeg.add(cbToma, 6, 2);



		gVeg.setHgap(2);



		gVeg.setVgap(2);







		Label lblVTwoP = new Label("$1.00");



		Label lblVThreeP = new Label("$2.00");



		Label lblVeg = new Label("Veggetable Toppings: \n2 toppings: +" + lblVTwoP.getText() + "\n3 toppings: +" + lblVThreeP.getText());



	/*	



		// Extra Veggie Topping Check Box



		CheckBox cbRedOX = new CheckBox("Red Onion");



		CheckBox cbBlkOX = new CheckBox("Black Olive");



		CheckBox cbMushX = new CheckBox("Mushroom");



		CheckBox cbSpinX = new CheckBox("Spinach");



		CheckBox cbGPepX = new CheckBox("Green Pepper");



		CheckBox cbBPepX = new CheckBox("Banana Pepper");



		CheckBox cbPineX = new CheckBox("Pineapple");



		CheckBox cbJalpX = new CheckBox("Jalapeno Pepper");



		CheckBox cbTomaX = new CheckBox("Tomatoes");











		// Add Extra Check Box to grid



		gVegX.add(cbRedOX, 0, 0);



		gVegX.add(cbBlkOX, 3, 0);



		gVegX.add(cbMushX, 6, 0);



		gVegX.add(cbSpinX, 0, 1);



		gVegX.add(cbGPepX, 3, 1);



		gVegX.add(cbBPepX, 6, 1);



		gVegX.add(cbPineX, 0, 2);



		gVegX.add(cbJalpX, 3, 2);



		gVegX.add(cbTomaX, 6, 2);



		gVegX.setHgap(2);



		gVegX.setVgap(2);



*/		



		// Beverage Check Box



		CheckBox cbCoke = new CheckBox("Coca-Cola");



		CheckBox cbZero = new CheckBox("Coca-Cola Zero");



		CheckBox cbDiet = new CheckBox("Diet Coca-Cola");



		CheckBox cbSprt = new CheckBox("Sprite");



		CheckBox cbFant = new CheckBox("Fanta Orange");







		gBev.add(cbCoke, 0, 0);



		gBev.add(cbZero, 3, 0);



		gBev.add(cbDiet, 6, 0);



		gBev.add(cbSprt, 0, 1);



		gBev.add(cbFant, 3, 1);



		gBev.setHgap(2);



		gBev.setVgap(2);







		Label lblBevPS = new Label("$2.00");
		Label lblBevPM = new Label("$2.50");
		Label lblBevPL = new Label("$3.00");



		Label lblBev = new Label("Beverage: \n");
		
		Label lblBevSize = new Label("Beverage size: \n");
		
		CheckBox cbBevPS = new CheckBox("Small +"+lblBevPS.getText());
		CheckBox cbBevPM = new CheckBox("Medium+"+lblBevPM.getText());
		CheckBox cbBevPL = new CheckBox("Large+"+lblBevPL.getText());







		// Delivery/Pickup Check Box



		Label lblDelP = new Label("$6.00");



		CheckBox cbDel = new CheckBox("Delivery +" + lblDelP.getText());



		CheckBox cbPic = new CheckBox("Pick-up");







		gBottom.add(cbDel, 1, 12);



		gBottom.add(cbPic, 2, 12);



		gDop.setHgap(2);



		gDop.setVgap(2);







		// Header Grid Pain - add labels



		gTop.add(lblName, 0, 0);



		gTop.add(lblPhone, 0, 1);



		gTop.add(lblAddress, 0, 2);







		// Body Grid Pane - Add labels and grid panes



		gCenter.add(lblSize, 0, 0);



		gCenter.add(gSize, 1, 0);



		gCenter.add(lblCrust, 0, 1);



		gCenter.add(gCrust, 1, 1);







		gCenter.add(lblC, 0, 2);



		gCenter.add(gCheese, 1, 2);







		gCenter.add(lblMTop, 0,3);



		gCenter.add(gMTop, 1, 3);



		gCenter.add(lblVeg, 0, 4);



		gCenter.add(gVeg, 1, 4);



//		gCenter.add(lblVegX, 0, 5);



//		gCenter.add(gVegX, 1, 5);



		gCenter.add(lblRqst, 0, 5);



		gCenter.add(taRqst, 1, 5);



		gCenter.add(lblBev, 0, 6);
		
		gCenter.add(lblBevSize, 0, 7);
		gCenter.add(cbBevPS, 1, 7);
		gCenter.add(cbBevPM, 2, 7);
		gCenter.add(cbBevPL, 3, 7);



		gCenter.add(gBev, 1, 6);



		gCenter.add(lblDop, 0, 8);



		gCenter.add(gDop, 1, 8);







		Button btBack = new Button("Back");



		Button btSub = new Button("Submit");







		gBottom.add(btBack, 1, 24);



		gBottom.add(btSub, 2, 24);



		gBottom.setHgap(2);



		gBottom.setVgap(2);







		gCenter.add(gBottom, 1, 8);















		// Set Border Pane with grid pane's



		bPane.setTop(gTop);



		bPane.setCenter(gCenter);



//		bPane.setBottom(gBottom);







		// Place final pane in scroll pane



		ScrollPane scroll = new ScrollPane();



		scroll.setContent(bPane);











		// Set Scene



		Scene scene = new Scene(scroll, 500, 500);







		// Set Stage



		stage.setTitle("Order Menu");



		stage.setScene(scene);



		stage.show();







	}











	public static void main(String[] args) {







		launch(args);







	}







}
