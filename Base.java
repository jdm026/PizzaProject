import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class Base extends Application{

	public void start(Stage stage)
	{
		
		GridPane loginPane = login();
		
		Scene scene = new Scene(loginPane, 500, 500);
		
		stage.setScene(scene);
		stage.setTitle("Login Window");
		stage.show();
		
	}
	
	// Customer/Manager Login Pane
	public GridPane login()
	{
		// Grid Panes to hold TextFields, Labels, Buttons, & CheckBox
		GridPane gPane = new GridPane();
		GridPane gPane2 = new GridPane();
				
		// Labels, TextFields, Buttons, CheckBox
		Label lblLogin = new Label("Phone Number:");
		Label lblPassword = new Label("Password:");
				
		TextField tfLogin = new TextField();
		TextField tfPassword = new TextField();
				
		Button b1 = new Button("Login");
		Button b2 = new Button("Register");
				
		CheckBox cbManager = new CheckBox("Management");
				
		// Set the control variables into the panes
		gPane.setAlignment(Pos.CENTER);
				
		gPane.setPadding(new Insets(5, 5, 5, 5));
		gPane.setHgap(10);
		gPane.setVgap(10);
				
		gPane.add(lblLogin, 0, 0);
		gPane.add(tfLogin, 1, 0);
		gPane.add(lblPassword, 0, 1);
		gPane.add(tfPassword, 1, 1);
				
		gPane2.setAlignment(Pos.BASELINE_LEFT);
		gPane2.setPadding(new Insets(5, 5, 5, 5));
		gPane2.setHgap(5);
		gPane2.setVgap(5);
		gPane2.add(b1, 0, 0);
		gPane2.add(b2, 1, 0);
		gPane2.add(cbManager, 2, 0);
				
		gPane.add(gPane2, 1, 2);
			
		// Handle Check Box Action
		// Manager only needs a password
		EventHandler<ActionEvent> checkBoxHandler = e -> {
					
			if (cbManager.isSelected()) {
				tfLogin.setEditable(false);
				// System.out.println("Test");
				
				// Event Handler for Manager Login Button
				EventHandler<ActionEvent> loginHandlerManager = loginHandleM -> {
					
					b1.setOnAction(b1Selected -> {
						
						// Test button will display the next screen (manager home)
						gPane.getChildren().clear();
						gPane.add(managerHome(), 0, 0);
						
					});
					
				};
				
				b1.setOnAction(loginHandlerManager);
				
			}
			else {
				tfLogin.setEditable(true);
				
				
				
				// Event Handler for Customer Login Button
				// 1.) Need to figure out database access
				EventHandler<ActionEvent> loginHandlerCustomer = loginHandle -> {
							
					// Login button action event
					// Validates login information as customer
					// C1.) Give access to next pane or display message to user
					// 2.) Save user information into data type and try to query database
					// 3.) Check database to display desired customer home screen
					b1.setOnAction( b1Selected ->{
								
						// ** Need to check if the customer login information is valid **
								
						// Test button will display the next screen (customer home)
						gPane.getChildren().clear();
						gPane.add(customerHome(), 0, 0);
					// System.out.println("Testing");	PASSED
								
					});
					
					
				};
				
				b1.setOnAction(loginHandlerCustomer);
			}
			
					
		};
				
		// Event Handler for Register Button
		// 1.) Need to figure out database access to register new data
		// 2.) 
		EventHandler<ActionEvent> registerHandler = registerHandle -> {
					
			// Register button action event
			// Checks if the user exists, if not proceed to registration pane
			// 1.) Save user information into data type and try to query database
			// 2.) Display registration pane 
			b2.setOnAction(b2Selected -> {
				
				// Display screen to register new customer
				gPane.getChildren().clear();
				gPane.add(customerViewEdit(), 0, 0);	
				
			});				
					
		};
		
		// Action Event handlers for nodes in login pane
		cbManager.setOnAction(checkBoxHandler);
		b1.setOnAction(checkBoxHandler);
		b2.setOnAction(registerHandler);
		
				
		return gPane;
	}
	
	
	// Returns Customer Home Screen
	// 1.) Place Order Handler
	// 2.) View/Edit Account Handler
	// 3.) Transaction History Handler
	// 4.) Log Out Handler
	public GridPane customerHome()
	{
		
		Button btOrder = new Button("Place Order");
		Button btView = new Button("View/Edit Account Info");
		Button btTrans = new Button("Transaction History");
		Button btOut = new Button("Log Out");
		
		GridPane gPane = new GridPane();
		gPane.setHgap(5);
		gPane.setVgap(5);
		gPane.setAlignment(Pos.CENTER);
		
		gPane.add(btOrder, 0, 0);
		gPane.add(btView, 0, 2);
		gPane.add(btTrans, 0, 4);
		gPane.add(btOut, 0, 6);
		
		
		// Event handler for place order button
		EventHandler placeOrderEvent = placeOrder -> {
			
			// Clear grid pane and insert place order pane
			gPane.getChildren().clear();
			gPane.add(placeOrder(), 0, 0);
			
		};
		
		// Action event handler for nodes in pane
		btOrder.setOnAction(placeOrderEvent);
		
		return gPane;
		
	}
	
	// Returns Manager Home Screen
	// 1.) Menu Customization Handler
	// 2.) Customer Info Handler
	// 3.) Log Out Handler
	public BorderPane managerHome()
	{
		BorderPane bpane = new BorderPane();
		GridPane gpane = new GridPane();
		
		bpane.setPadding(new Insets(5, 5, 5, 5));
		
		Button menu = new Button("Menu Customization");
		Button info = new Button("Customer Info");
		Button logo = new Button ("Log Out");
		
		gpane.add(menu, 0, 1);
		gpane.add(info, 0, 2);
		gpane.add(logo, 0, 3);
		
		gpane.setAlignment(Pos.CENTER);
		gpane.setHgap(10);
		gpane.setVgap(10);
		
		bpane.setCenter(gpane);
		bpane.setAlignment(gpane, Pos.CENTER);		
		
		// Event Handler for Menu Customization Button
		EventHandler<ActionEvent> menuCustomHandler = menuCustomHandle -> {
			
			menu.setOnAction(menuSelected -> {
				
				bpane.getChildren().clear();
				bpane.setCenter(managerCustomMenu());
				
			});
		};
		
		// Event Handler for Customer Information Search
		EventHandler<ActionEvent> custSearchHandler = custSearchHandle -> {
		
			info.setOnAction(infoSelected -> {
				
				bpane.getChildren().clear();
				bpane.setCenter(managerCustomerSearch());
				
			});
			
		};
		
		menu.setOnAction(menuCustomHandler);
		info.setOnAction(custSearchHandler);
		
		
		return bpane;
		
	}
	
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ SPRINT 2 REQUIREMENTS NEEDING TO BE UPDATED HERE ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
//    - Make Extra Large Crust Size text field (***Update Customer Order menu***)
//    - 2 Crust toppings (keep original and stuffed)
//	  - 5 Beverages
//    - 3 Distinct beverage sizes
	
	
	// Returns Manager Customization Menu
	// 1.) Handle change of price in gui to database
	// 2.) Handle back button
	// 3.) Handle submit button -> test changes in database
	public ScrollPane managerCustomMenu()
	{
		// Size Price's
		Label lblSize = new Label("Size: ");
		Label lblS = new Label("Small");
		Label lblM = new Label("Medium");
		Label lblL = new Label("Large");
		
		TextField tfS = new TextField("$X.XX");
		TextField tfM = new TextField("$X.XX");
		TextField tfL = new TextField("$X.XX");
		
		tfS.setPrefWidth(100);
		tfM.setPrefWidth(100);
		tfL.setPrefWidth(100);
		
		GridPane gSize = new GridPane();
		gSize.add(lblS, 0, 0);
		gSize.add(tfS, 0, 1);
		gSize.add(lblM, 3, 0);
		gSize.add(tfM, 3, 1);
		gSize.add(lblL, 6, 0);
		gSize.add(tfL, 6, 1);
		
		gSize.setHgap(2);
		gSize.setVgap(2);
		
		// Crust Price's
		Label lblCrust = new Label("Crust: ");	
		Label lblHand = new Label("Hand Tossed");
		Label lblThin = new Label("Thin");
		Label lblStuf = new Label("Stuffed Crust");
		
		TextField tfHand = new TextField("$X.XX");
		TextField tfThin = new TextField("$X.XX");
		TextField tfStuf = new TextField("$X.XX");
		
		tfHand.setPrefWidth(100);
		tfThin.setPrefWidth(100);
		tfStuf.setPrefWidth(100);
		
		GridPane gCrust = new GridPane();
		gCrust.add(lblHand, 0, 0);
		gCrust.add(tfHand, 0, 1);
		gCrust.add(lblThin, 3, 0);
		gCrust.add(tfThin, 3, 1);
		gCrust.add(lblStuf, 6, 0);
		gCrust.add(tfStuf, 6, 1);
		
		gCrust.setHgap(2);
		gCrust.setVgap(2);
		
		// Cheese Price's
		Label lblC = new Label("Cheese: ");
		Label lblXCh = new Label("Extra Cheese");
		Label lblMoz = new Label("Mozarella");
		Label lblParm = new Label("Parmessean");
		
		TextField tfXCh = new TextField("$X.XX");
		TextField tfMoz = new TextField("$X.XX");
		TextField tfParm = new TextField("$X.XX");
		
		tfXCh.setPrefWidth(100);
		tfMoz.setPrefWidth(100);
		tfParm.setPrefWidth(100);
		
		GridPane gCheese = new GridPane();
		gCheese.add(lblXCh, 0, 0);
		gCheese.add(tfXCh, 0, 1);
		gCheese.add(lblMoz, 1, 0);
		gCheese.add(tfMoz, 1, 1);
		gCheese.add(lblParm, 2, 0);
		gCheese.add(tfParm, 2, 1);
		
		gCheese.setHgap(2);
		gCheese.setVgap(2);
		
		// Meat Topping
		Label lblMTop = new Label("Meat Toppings: ");
		Label lblMTwoTop = new Label("Two Toppings");
		Label lblMThrTop = new Label("Three Toppings");
		
		TextField tfMTwoTop = new TextField("$X.XX");
		TextField tfMThrTop = new TextField("$X.XX");
		
		tfMTwoTop.setPrefWidth(100);
		tfMThrTop.setPrefWidth(100);
		
		GridPane gMeat = new GridPane();
		gMeat.add(lblMTwoTop, 0, 0);
		gMeat.add(tfMTwoTop, 0, 1);
		gMeat.add(lblMThrTop, 1, 0);
		gMeat.add(tfMThrTop, 1, 1);
		
		gMeat.setHgap(2);
		gMeat.setVgap(2);
		
		// Vegetable Topping
		Label lblVeg = new Label("Veggetable Toppings: ");
		Label lblVTwoTop = new Label("Two Toppings");
		Label lblVThrTop = new Label("Three Toppings");
		
		TextField tfVTwoTop = new TextField("$X.XX");
		TextField tfVThrTop = new TextField("$X.XX");
		
		tfVTwoTop.setPrefWidth(100);
		tfVThrTop.setPrefWidth(100);
		
		GridPane gVeg = new GridPane();
		gVeg.add(lblVTwoTop, 0, 0);
		gVeg.add(tfVTwoTop, 0, 1);
		gVeg.add(lblVThrTop, 1, 0);
		gVeg.add(tfVThrTop, 1, 1);
		
		gVeg.setHgap(2);
		gVeg.setVgap(2);
		
		// Beverage
		Label lblBev = new Label("Beverage: ");
		Label lblBTwo = new Label("2-Liter");
		
		TextField tfBTwo = new TextField("$X.XX");
		
		tfBTwo.setPrefWidth(100);
		
		GridPane gBev = new GridPane();
		gBev.add(lblBTwo, 0, 0);
		gBev.add(tfBTwo, 0, 1);
		
		gBev.setHgap(2);
		gBev.setVgap(2);
		
		// Button Operations
		Button btBack = new Button("Back");
		Button btSub = new Button("Submit");
		
		GridPane gBottom = new GridPane();
		gBottom.add(btBack, 0, 0);
		gBottom.add(btSub, 3, 0);
		gBottom.setHgap(30);
		gBottom.setVgap(10);
		
		// Add Nodes to gridPane
		GridPane gMain = new GridPane();
		gMain.add(lblSize, 0, 0);
		gMain.add(gSize, 1, 0);
		gMain.add(lblCrust, 0, 1);
		gMain.add(gCrust, 1, 1);
		gMain.add(lblC, 0, 2);
		gMain.add(gCheese, 1, 2);
		gMain.add(lblMTop, 0, 3);
		gMain.add(gMeat, 1, 3);
		gMain.add(lblVeg, 0, 4);
		gMain.add(gVeg, 1, 4);
		gMain.add(lblBev, 0, 5);
		gMain.add(gBev, 1, 5);
		gMain.add(gBottom, 1, 9);
	
		gMain.setHgap(5);
		gMain.setVgap(5);
		
		ScrollPane scroll = new ScrollPane();
		scroll.setContent(gMain);
		
		return scroll;
	}
	
	// Return Manager Customer Search Menu
	// 1.) Receive user input for phone number and check database for records
	// 2.) Handle if the phone number was found or not (Search button)
	// 3.) Handle cancel button
	public BorderPane managerCustomerSearch()
	{
		
		BorderPane bpane = new BorderPane();
		GridPane gpane = new GridPane();
		GridPane gpane2 = new GridPane();
		
		bpane.setPadding(new Insets(10, 10, 10, 10));
		gpane.setHgap(5);
		gpane.setVgap(5);
		gpane.setAlignment(Pos.CENTER);
		
		gpane2.setHgap(5);
		gpane2.setVgap(5);
		
		Label phone = new Label("Phone Number:");
		TextField entry = new TextField();
		Button srch = new Button("Search");
		Button cncl = new Button("Cancel");
		
		
		gpane2.add(srch, 0, 0);
		gpane2.add(cncl, 1, 0);
		
		gpane.add(phone, 0, 0);
		gpane.add(entry, 1, 0);
		gpane.add(gpane2, 1, 1);
		
		bpane.setCenter(gpane);
		
		return bpane;
	}
	
	// Returns Customer View and Edit Information Screen
	// 1.) Handle submit button
	//      - Check if user information is already in database
	// 2.) Handle clear button
	// 3.) Handle cancel
	public BorderPane customerViewEdit()
	{
		BorderPane bPane = new BorderPane();
		GridPane gCenterPane = new GridPane();
		GridPane gBottomPane = new GridPane();
		GridPane gButton = new GridPane();
			
		gCenterPane.setVgap(5);
		gCenterPane.setHgap(5);
		gCenterPane.setAlignment(Pos.CENTER);
			
		gBottomPane.setVgap(10);
		gBottomPane.setHgap(10);
		gBottomPane.setAlignment(Pos.CENTER);
			
		gButton.setVgap(5);
		gButton.setHgap(5);
		gButton.setAlignment(Pos.CENTER);
			
		Label lblName = new Label("Name: ");
		Label lblPhone = new Label("Phone Number: ");
		Label lblAddr = new Label("Address:\n(Street, City, Zip)");
		Label lblDesc = new Label("Address Desctription:");
		Label lblPay = new Label("Payment Method: ");
		Label lblCard = new Label("Card Information (if applicable)");
		Label lblCName = new Label("Name On Card: ");
		Label lblNum = new Label("Card Number: ");
		Label lblExp = new Label("Exp Date: ");
		Label lblSc = new Label("Security Code: ");
		Label lblChk = new Label("Check Information (if applicable)");
		Label lblANum = new Label("Account Number: ");
		Label lblRNum = new Label("Routing Number: ");
			
		TextArea txDesc = new TextArea();
		txDesc.setPrefHeight(40);
		txDesc.setPrefWidth(200);
		txDesc.setWrapText(true);
			
		TextField tfName = new TextField();
		TextField tfPhone = new TextField();
		TextField tfAddr = new TextField();
		TextField tfCName = new TextField();
		TextField tfNum = new TextField();
		TextField tfExp = new TextField();
		TextField tfSc = new TextField();
		TextField tfANum = new TextField();
		TextField tfRNum = new TextField();
			
			
		Button submit = new Button("Submit");
		Button clear = new Button("Clear");
		Button cancel = new Button("Cancel");
			
		CheckBox cash = new CheckBox("Cash");
		CheckBox check = new CheckBox("Check");
		CheckBox card = new CheckBox("Card");
			
		gButton.add(card, 0, 0);
		gButton.add(cash, 1, 0);
		gButton.add(check, 2, 0);
			
		gCenterPane.add(lblName, 0, 0);
		gCenterPane.add(tfName, 1, 0);
		gCenterPane.add(lblPhone, 0, 1);
		gCenterPane.add(tfPhone, 1, 1);
		gCenterPane.add(lblAddr, 0, 2);
		gCenterPane.add(tfAddr, 1, 2);
		gCenterPane.add(lblDesc, 0, 3);
		gCenterPane.add(txDesc, 1, 3);
		gCenterPane.add(lblPay, 0, 4);
		gCenterPane.add(gButton, 1, 4);
		gCenterPane.add(lblCard, 0, 5);
		gCenterPane.add(lblCName, 0, 6);
		gCenterPane.add(tfCName, 1, 6);
		gCenterPane.add(lblNum, 0, 7);
		gCenterPane.add(tfNum, 1, 7);
		gCenterPane.add(lblExp, 0, 8);
		gCenterPane.add(tfExp, 1, 8);
		gCenterPane.add(lblSc, 0, 9);
		gCenterPane.add(tfSc, 1, 9);
		gCenterPane.add(lblChk, 0, 10);
		gCenterPane.add(lblANum, 0, 11);
		gCenterPane.add(tfANum, 1, 11);
		gCenterPane.add(lblRNum, 0, 12);
		gCenterPane.add(tfRNum, 1, 12);
			
			
		gBottomPane.add(submit, 2, 0);
		gBottomPane.add(clear, 1, 0);
		gBottomPane.add(cancel, 0, 0);
			
			
			
		bPane.setCenter(gCenterPane);
		bPane.setBottom(gBottomPane);
			
		return bPane;
			
	}
	
// ~~~~~~ Leaving 1 & 2 For After Sprint 2
	// Return Place Order Pane
	// 1.) Display customer information
	// 2.) Handle check box's with next button event
	//		- Pepperoni
	//		- Supreme
	//		- Hawaiian
	//		- Meat Lover
	//      - Three Cheese
	// 3.) Handle next button event with create own pizza checked
	// 4.) Handle back button event
	public Pane placeOrder()
	{
		
		BorderPane bPane = new BorderPane();
		GridPane gPane = new GridPane();
		
		GridPane gPaneCenter = new GridPane();
		GridPane gPaneTop = new GridPane();
				
		bPane.setPadding(new Insets(10, 10, 10, 10));
		gPane.setAlignment(Pos.CENTER);

		
		Label lblName = new Label("[Name]");
		Label lblPhone = new Label("[Phone]");
		Label lblAddress = new Label("[Address]");
		
		gPaneTop.add(lblName, 0, 0);
		gPaneTop.add(lblPhone, 0, 1);
		gPaneTop.add(lblAddress, 0, 2);
		
		gPaneTop.setHgap(10);
		gPaneTop.setVgap(10);
		gPaneTop.setAlignment(Pos.CENTER);
		bPane.setTop(gPaneTop);
		
		
		CheckBox cbPep = new CheckBox("Pepperoni");
		CheckBox cbSup = new CheckBox("Supreme");
		CheckBox cbHaw = new CheckBox("Hawaiian");
		CheckBox cbMlv = new CheckBox("Meat Lover");
		CheckBox cbTch = new CheckBox("Three Cheese");
		CheckBox cbCre = new CheckBox("Create Your Own");
		
		gPaneCenter.add(cbPep, 0, 0);
		gPaneCenter.add(cbSup, 0, 1);
		gPaneCenter.add(cbHaw, 0, 2);
		gPaneCenter.add(cbMlv, 0, 3);
		gPaneCenter.add(cbTch, 0, 4);
		gPaneCenter.add(cbCre, 0, 5);
		
		gPaneCenter.setHgap(10);
		gPaneCenter.setVgap(10);
		gPaneCenter.setAlignment(Pos.CENTER);
		
		bPane.setCenter(gPaneCenter);
		
		Button btNext = new Button("Next");
		Button btBack = new Button("Back");
		gPane.add(btNext, 2, 0);
		gPane.add(btBack, 0, 0);
		
		gPane.setHgap(10);
		gPane.setVgap(10);
		
		bPane.setBottom(gPane);
		
		EventHandler<ActionEvent> nextBtEvent = nextBt -> {
			
			// Pepperoni
			//		- Supreme
			//		- Hawaiian
			//		- Meat Lover
			//      - Three Cheese
			
			// Create Your Own
			if (cbCre.isSelected())
			{
				
				
				btNext.setOnAction(nextSelected -> {
					
					bPane.getChildren().clear();
					bPane.setTop(customerInfoDuringOrder());
					bPane.setCenter(orderMenuCustomer());
					
				});
				
				
				
			}
		};
		
		btNext.setOnAction(nextBtEvent);
		
		return bPane;
		
	}
	
	// Return Customer Info ** Think about how to save values from the initial login
	public GridPane customerInfoDuringOrder()
	{
		GridPane gTop = new GridPane();
		
		gTop.setVgap(10);
		gTop.setAlignment(Pos.TOP_CENTER);
		
		// Customer Labels
		// 1.) Receive input from parameter and initialize new label
		Label lblName = new Label("[Name]");
		Label lblPhone = new Label("[Phone Number]");
		Label lblAddress = new Label("[Address]");
		
		// Header Grid Pain - add labels
		gTop.add(lblName, 0, 0);
		gTop.add(lblPhone, 0, 1);
		gTop.add(lblAddress, 0, 2);
		
		return gTop;
		
	}
	
	// Return Create Your Own Menu
	
	public ScrollPane orderMenuCustomer()
	{
		// Border Pane and Grid Panes For Each Node
				BorderPane bPane = new BorderPane();
		//		GridPane gTop = new GridPane();
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
				
		//		gTop.setVgap(10);
		//		gTop.setAlignment(Pos.TOP_CENTER);
				
				gCenter.setHgap(10);
				gCenter.setVgap(10);
				gCenter.setAlignment(Pos.CENTER);
				
				gBottom.setHgap(10);
				gBottom.setAlignment(Pos.CENTER);
				
				// Customer Labels
		//		Label lblName = new Label("[Name]");
		//		Label lblPhone = new Label("[Phone Number]");
		//		Label lblAddress = new Label("[Address]");
				
				// Section Labels
				Label lblSize = new Label("Size: ");
//				Label lblMeatx = new Label("Extra Toppings: ");
				Label lblCrust = new Label("Crust: ");	
//				Label lblVegX = new Label("Extra Veggetable\nToppings");
				Label lblRqst = new Label("Special Requests: ");
				Label lblDop = new Label("Delivery or Pick-up: ");
				
				TextArea taRqst = new TextArea();
				taRqst.setPrefHeight(10);
				taRqst.setPrefWidth(50);
				taRqst.setWrapText(true);
				
				// Pizza Size Check Box
				Label lblPriceS = new Label("$X.XX");
				Label lblPriceM = new Label("$X.XX");
				Label lblPriceL = new Label("$X.XX");
				
				CheckBox cbSmall = new CheckBox("Small\n(4 Slices)\n" + lblPriceS.getText());
				CheckBox cbMedium = new CheckBox("Medium\n(6 Slices)\n"+ lblPriceM.getText());
				CheckBox cbLarge = new CheckBox("Large\n(8 Slices)\n" + lblPriceL.getText());
				
				// Add Check Box to Pane
				gSize.add(cbSmall, 0, 0);
				gSize.add(cbMedium, 3, 0);
				gSize.add(cbLarge, 6, 0);
				gSize.setHgap(2);
				gSize.setVgap(2);
				
				// Crust CheckBox
				
				Label lblPriceH = new Label("+$X.XX");
				Label lblPriceT = new Label("+$X.XX");
				Label lblPriceSC = new Label("+$X.XX");
				
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
				
				Label lblCheeseP = new Label("$X.XX");
				Label lblXCheeseP = new Label("$X.XX");
				Label lblMozP = new Label("$X.XX");
				Label lblParmP = new Label("$X.XX");
				
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
				
				Label lblTwoP = new Label("$X.XX");
				Label lblThreeP = new Label("$X.XX");
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
				
				Label lblVTwoP = new Label("$X.XX");
				Label lblVThreeP = new Label("$X.XX");
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
				
				Label lblBevP = new Label("$X.XX");
				Label lblBev = new Label("Beverage: \n+" + lblBevP.getText());
				
				// Delivery/Pickup Check Box
				Label lblDelP = new Label("$X.XX");
				CheckBox cbDel = new CheckBox("Delivery +" + lblDelP.getText());
				CheckBox cbPic = new CheckBox("Pick-up");
				
				gDop.add(cbDel, 0, 0);
				gDop.add(cbPic, 1, 0);
				gDop.setHgap(2);
				gDop.setVgap(2);
				
				// Header Grid Pain - add labels
		//		gTop.add(lblName, 0, 0);
		//		gTop.add(lblPhone, 0, 1);
		//		gTop.add(lblAddress, 0, 2);
				
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
//				gCenter.add(lblVegX, 0, 5);
//				gCenter.add(gVegX, 1, 5);
				gCenter.add(lblRqst, 0, 5);
				gCenter.add(taRqst, 1, 5);
				gCenter.add(lblBev, 0, 6);
				gCenter.add(gBev, 1, 6);
				gCenter.add(lblDop, 0, 7);
				gCenter.add(gDop, 1, 7);
				
				Button btBack = new Button("Back");
				Button btSub = new Button("Submit");
				
				gBottom.add(btBack, 0, 0);
				gBottom.add(btSub, 3, 0);
				gBottom.setHgap(2);
				gBottom.setVgap(2);
				
				gCenter.add(gBottom, 1, 8);
				
				
				
				// Set Border Pane with grid pane's
		//		bPane.setTop(gTop);
				bPane.setCenter(gCenter);
//				bPane.setBottom(gBottom);
				
				// Place final pane in scroll pane
				ScrollPane scroll = new ScrollPane();
				scroll.setContent(bPane);
				
				return scroll;
				
	}
	
	
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
}
