package application;

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
import javafx.scene.text.Text;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.text.NumberFormat;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class Base extends Application{

	Customer customer = new Customer();
	Customer customerIn = new Customer();
	
	Manager manager = new Manager();
	Manager managerIn = new Manager();

	//A variable to track order price
	static double total = 0;
	
	//Class variables
	// Initial menu item prices
	static double priceSmall = 4.0;
	static double priceMedium = 6.0;
	static double priceLarge = 8.0;
	static double priceXLarge = 10.0;
	static double priceCrustThin = 1.5;
	static double priceCrustStuff = 2.0;
	static double priceCheeseExtra = 1.0;
	static double priceCheeseMoz = 1.50;
	static double priceMeatTwo = 2.00;
	static double priceMeatThree = 3.00;
	static double priceVegTwo = 1.00;
	static double priceVegThree = 2.00;
	static double priceBevSmall = 2.00;
	static double priceBevMedium = 2.50;
	static double priceBevLarge = 3.00;
	static double priceDeliv = 6.00;

	static String managerName = "*Name*";
	static String managerPass = "*Password*";	
	
	// Variables used to initialize and print receipt
	static String orderSize = "";
	static String orderCrust = "";
	static String orderCheese1 = "";
	static String orderCheese2 = "";
	static String orderMeat1 = "";
	static String orderMeat2 = "";
	static String orderMeat3 = "";
	static String orderVeg1 = "";
	static String orderVeg2 = "";
	static String orderVeg3 = "";
	static String orderRequest = "";
	static String orderBev = "";
	static String orderBevSize = "";
	static String orderDelivery = "";
	static String orderDateString = "";
	static Date orderDate;
	
	// Class stage
	static Stage stageTitle = new Stage();
	
	// Stores topping sizes to control max toppings
	static int meatTopCount = 0;
	static int vegTopCount = 0;
	static int cheeseTopCount = 0;
	static int topCount;
	
	public void start(Stage stage)
	{		
	
		GridPane loginPane = login();
		customer.demoCustomer();
		manager.demoManager();
		
		
		Scene scene = new Scene(loginPane, 800, 800);
		//Scene scene = new Scene(orderMenuCustom(), 800, 800);
		//Scene scene = new Scene(receipt(), 800, 800);
		
		stageTitle.setScene(scene);
		stageTitle.setTitle("Mom and Pop Pizza Shop");
		stageTitle.show();
		
	}

	// Customer/Manager Login Pane
	// 1.) Customer Login
	// 2.) Customer Register
	// 3.) Manager Login
	// 4.) Manager Register
	public GridPane login()
	{
		stageTitle.setTitle("Login Screen");
		
		
		
		// Grid Panes to hold TextFields, Labels, Buttons, & CheckBox
		GridPane gPane = new GridPane();
		GridPane gPane2 = new GridPane();
				
		// Labels, TextFields, Buttons, CheckBox
		Label lblLogin = new Label("Phone Number:");
		Label lblPassword = new Label("Password:");
				
		TextField tfLogin = new TextField();
		TextField tfPassword = new TextField();
				
		Button btLogin = new Button("Login");
		Button btRegister = new Button("Register");
				
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
		gPane2.add(btLogin, 0, 0);
		gPane2.add(btRegister, 1, 0);
		gPane2.add(cbManager, 2, 0);
				
		gPane.add(gPane2, 1, 2);
				
		// Handle Login Text Field with manager check box action
		cbManager.setOnAction(loginExtField -> {
			
			if (cbManager.isSelected())
				tfLogin.setEditable(false);
			else
				tfLogin.setEditable(true);
			
		});
		
		// Handle login action
		btLogin.setOnAction(loginSelected -> {
			
			if (cbManager.isSelected()) {
				
				
				for (int i = 0; i < manager.managerArray.length; i++)
				{		
					System.out.println("Test M: " + i);
					try
					{		
						
						if (tfPassword.getText().trim().equals(manager.managerArray[i].managerPass.trim()))
						{
							managerIn = manager.managerArray[i];
								
							gPane.getChildren().clear();
							gPane.add(managerHome(), 0, 0);               
							break;
						}
					}
					catch(NullPointerException np)
					{
						Text invalid = new Text("Invalid Username/Password");
						invalid.setFill(Color.RED);
						
						gPane.getChildren().clear();
						
						gPane.add(lblLogin, 0, 0);
						gPane.add(tfLogin, 1, 0);
						gPane.add(lblPassword, 0, 1);
						gPane.add(tfPassword, 1, 1);
						gPane.add(gPane2, 1, 2);
						gPane.add(invalid, 1, 3);
						break;
					}
				}
				
					
			}
			else
			{
				for (int i = 0; i < customer.customerArray.length; i++)
				{		
					try
					{		
						if (tfLogin.getText().trim().equals(customer.customerArray[i].custPhone.trim()) && tfPassword.getText().trim().equals(customer.customerArray[i].custPass.trim()))
						{
						//	customerIn = new Customer(customer.customerArray[i]);
							customerIn = customer.customerArray[i];
							
							gPane.getChildren().clear();
							gPane.add(customerHome(), 0, 0);               
							break;
						}
					}
					catch(NullPointerException np)
					{
						Text invalid = new Text("Invalid Username/Password");
						invalid.setFill(Color.RED);
						
						gPane.getChildren().clear();
						
						gPane.add(lblLogin, 0, 0);
						gPane.add(tfLogin, 1, 0);
						gPane.add(lblPassword, 0, 1);
						gPane.add(tfPassword, 1, 1);
						gPane.add(gPane2, 1, 2);
						gPane.add(invalid, 1, 3);
						break;
					}
				}
			}
				
		});
			
		// Handle register action
		btRegister.setOnAction( registerSelected -> {
				
			if (cbManager.isSelected()){
					
				// Display Manager Register Screen
				gPane.getChildren().clear();
				gPane.add(managerRegister(), 0, 0);
					
			}
			else{
				
				// Test button will display the next screen (customer home)
				gPane.getChildren().clear();
				gPane.add(customerRegister(), 0, 0);

				}
				
		});
				
		return gPane;
	}
	
	// Return Customer Info
	public GridPane customerInfoDuringOrder()
	{
		GridPane gTop = new GridPane();
		
		gTop.setVgap(10);
		gTop.setAlignment(Pos.TOP_CENTER);
		
		// Customer Labels
		// 1.) Receive input from parameter and initialize new label
		Label lblName = new Label(customerIn.custName);
		Label lblPhone = new Label(customerIn.custPhone);
		Label lblAddress = new Label(customerIn.custAddress);
		
		// Header Grid Pain - add labels
		gTop.add(lblName, 0, 0);
		gTop.add(lblPhone, 0, 1);
		gTop.add(lblAddress, 0, 2);
		gTop.add(new Label(), 0, 3);
		
		return gTop;
		
	}
	
	// Returns Customer Home Screen
	// 1.) Place Order Handler
	// 2.) View/Edit Account Handler
	// 3.) Transaction History Handler
	// 4.) Log Out Handler
	public BorderPane customerHome()
	{
		stageTitle.setTitle("Customer Home Screen");
		
		Button btOrder = new Button("Place Order");
		Button btView = new Button("View/Edit Account Info");
		Button btTrans = new Button("Transaction History");
		Button btOut = new Button("Log Out");
		
		GridPane gPane = new GridPane();
		BorderPane bPane = new BorderPane();
		
		gPane.setHgap(5);
		gPane.setVgap(5);
		gPane.setAlignment(Pos.CENTER);
		
		gPane.add(btOrder, 0, 0);
		gPane.add(btView, 0, 2);
		gPane.add(btTrans, 0, 4);
		gPane.add(btOut, 0, 6);
		
		bPane.setCenter(gPane);
		bPane.setTop(customerInfoDuringOrder());
		
		// Event handler for place order button
		btOrder.setOnAction(placeOrderButton -> {
			
			// Clear grid pane and insert place order pane
			bPane.getChildren().clear();
			bPane.setCenter(orderMenuCustom());
			
		});
		
		// Event handler for view/edit button
		btView.setOnAction(viewButton -> {
			
			bPane.getChildren().clear();
			bPane.setCenter(customerViewEdit());
			
		});
		
		// Event handler for transaction class
		btTrans.setOnAction(transactionButton -> {
			
			bPane.getChildren().clear();
			bPane.setCenter(customerHistory());
			
		});
		
		btOut.setOnAction(logoutPressed -> {
			
			
			bPane.getChildren().clear();
			bPane.setCenter(login());
			
		});
		
		return bPane;
		
	}
	
	// Returns Manager Home Screen
	// 1.) Menu Customization Handler
	// 2.) Customer Info Handler
	// 3.) Log Out Handler
	public BorderPane managerHome()
	{
		stageTitle.setTitle("Manager Home Screen - Manager: " + managerIn.managerName);
		
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
		menu.setOnAction(menuSelected -> {
						
			bpane.getChildren().clear();
			bpane.setCenter(managerCustomMenu());
						
		});
		
		// Event Handler for Customer Information Search
		info.setOnAction(infoSelected -> {
				
			bpane.getChildren().clear();
			bpane.setCenter(managerCustomerSearch());
				
		});
		
		logo.setOnAction(logoutHandle -> {
			
			bpane.getChildren().clear();
			bpane.setCenter(login());
			
		});
		
		return bpane;
		
	}
	
	// Returns Manager Customization Menu
	// 1.) Submit Button
	// 2.) Back Button 
	public BorderPane managerCustomMenu()
	{
		stageTitle.setTitle("Manager Customize Menu Screen - Manager: " + managerIn.managerName );
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		// Size Price's
		Label lblSize = new Label("Size: ");
		Label lblS = new Label("Small\n" + formatter.format(priceSmall));
		Label lblM = new Label("Medium\n" + formatter.format(priceMedium));
		Label lblL = new Label("Large\n" + formatter.format(priceLarge));
		Label lblXL = new Label("Extra Large\n" + formatter.format(priceXLarge));
		
		TextField tfS = new TextField(String.valueOf(priceSmall));
		TextField tfM = new TextField(String.valueOf(priceMedium));
		TextField tfL = new TextField(String.valueOf(priceLarge));
		TextField tfXL = new TextField(String.valueOf(priceXLarge));
		
		tfS.setPrefWidth(100);
		tfM.setPrefWidth(100);
		tfL.setPrefWidth(100);
		tfXL.setPrefWidth(100);
		
		GridPane gSize = new GridPane();
		gSize.add(lblS, 0, 0);
		gSize.add(tfS, 0, 1);
		gSize.add(lblM, 3, 0);
		gSize.add(tfM, 3, 1);
		gSize.add(lblL, 6, 0);
		gSize.add(tfL, 6, 1);
		gSize.add(lblXL, 9, 0);
		gSize.add(tfXL, 9, 1);
		
			
		gSize.setHgap(2);
		gSize.setVgap(2);
		
		// Crust Price's
		Label lblCrust = new Label("Crust: ");	
		Label lblThin = new Label("Thin\n" + formatter.format(priceCrustThin));
		Label lblStuff = new Label("Stuffed Crust\n" + formatter.format(priceCrustStuff));
		
		TextField tfThin = new TextField(String.valueOf(priceCrustThin));
		TextField tfStuff = new TextField(String.valueOf(priceCrustStuff));
		
		tfThin.setPrefWidth(100);
		tfStuff.setPrefWidth(100);		
		
		GridPane gCrust = new GridPane();
		gCrust.add(lblThin, 0, 0);
		gCrust.add(tfThin, 0, 1);
		gCrust.add(lblStuff, 3, 0);
		gCrust.add(tfStuff, 3, 1);
		
		gCrust.setHgap(2);
		gCrust.setVgap(2);
		
		// Cheese Price's
		Label lblC = new Label("Cheese: ");
		Label lblXCh = new Label("Extra Cheese\n" + formatter.format(priceCheeseExtra));
		Label lblMoz = new Label("Mozarella\n" + formatter.format(priceCheeseMoz));
		
		TextField tfXCh = new TextField(String.valueOf(priceCheeseExtra));
		TextField tfMoz = new TextField(String.valueOf(priceCheeseMoz));
				
		tfXCh.setPrefWidth(100);
		tfMoz.setPrefWidth(100);
		
		GridPane gCheese = new GridPane();
		gCheese.add(lblXCh, 0, 0);
		gCheese.add(tfXCh, 0, 1);
		gCheese.add(lblMoz, 3, 0);
		gCheese.add(tfMoz, 3, 1);
		
		gCheese.setHgap(2);
		gCheese.setVgap(2);
		
		// Meat Topping
		Label lblMTop = new Label("Meat Toppings: ");
		Label lblMTwoTop = new Label("Two Toppings\n" + formatter.format(priceMeatTwo));
		Label lblMThrTop = new Label("Three Toppings\n" + formatter.format(priceMeatThree));
		
		TextField tfMTwoTop = new TextField(String.valueOf(priceMeatTwo));
		TextField tfMThrTop = new TextField(String.valueOf(priceMeatThree));
		
		tfMTwoTop.setPrefWidth(100);
		tfMThrTop.setPrefWidth(100);
		
		GridPane gMeat = new GridPane();
		gMeat.add(lblMTwoTop, 0, 0);
		gMeat.add(tfMTwoTop, 0, 1);
		gMeat.add(lblMThrTop, 3, 0);
		gMeat.add(tfMThrTop, 3, 1);
		
		gMeat.setHgap(2);
		gMeat.setVgap(2);
		
		// Vegetable Topping
		Label lblVeg = new Label("Veggetable Toppings: ");
		Label lblVTwoTop = new Label("Two Toppings\n" + formatter.format(priceVegTwo));
		Label lblVThrTop = new Label("Three Toppings\n" + formatter.format(priceVegThree));
		
		TextField tfVTwoTop = new TextField(String.valueOf(priceVegTwo));
		TextField tfVThrTop = new TextField(String.valueOf(priceVegThree));
		
		tfVTwoTop.setPrefWidth(100);
		tfVThrTop.setPrefWidth(100);
		
		GridPane gVeg = new GridPane();
		gVeg.add(lblVTwoTop, 0, 0);
		gVeg.add(tfVTwoTop, 0, 1);
		gVeg.add(lblVThrTop, 3, 0);
		gVeg.add(tfVThrTop, 3, 1);
		
		gVeg.setHgap(2);
		gVeg.setVgap(2);
		
		// Beverage
		Label lblBev = new Label("Beverage: ");
		Label lblBevSmall = new Label("Small\n" + formatter.format(priceBevSmall));
		Label lblBevMed = new Label("Medium\n" + formatter.format(priceBevMedium));
		Label lblBevLarge = new Label("Large\n" + formatter.format(priceBevLarge));
		
		TextField tfBevSmall = new TextField(String.valueOf(priceBevSmall));
		TextField tfBevMed = new TextField(String.valueOf(priceBevMedium));
		TextField tfBevLarge = new TextField(String.valueOf(priceBevLarge));
		
		tfBevSmall.setPrefWidth(100);
		tfBevMed.setPrefWidth(100);
		tfBevLarge.setPrefWidth(100);
		
		// Delivery Fee
		Label lblDel = new Label("Delivery Fee: ");
		Label lblFee = new Label("Fee\n" + formatter.format(priceDeliv));
		TextField tfFee = new TextField(String.valueOf(priceDeliv));
		
		tfFee.setPrefWidth(100);
		
		GridPane gDel = new GridPane();
		gDel.add(lblFee, 0, 0);
		gDel.add(tfFee, 0, 1);
		
		
		
		
		GridPane gBev = new GridPane();
		gBev.add(lblBevSmall, 0, 0);
		gBev.add(tfBevSmall, 0, 1);
		gBev.add(lblBevMed, 3, 0);
		gBev.add(tfBevMed, 3, 1);
		gBev.add(lblBevLarge, 6, 0);
		gBev.add(tfBevLarge, 6, 1);
		
		gBev.setHgap(2);
		gBev.setVgap(2);

		// Button Operations
		Button btBack = new Button("Back");
		Button btSub = new Button("Submit");
		
//User	The user must enter a double number without the dollar sign		
		btSub.setOnAction(submitButton -> {
			
			// Update label value with entered double
			priceSmall = Double.parseDouble(tfS.getText());
			lblS.setText("Small\n" + formatter.format(priceSmall));

			priceMedium = Double.parseDouble(tfM.getText());
			lblM.setText("Medium\n" + formatter.format(priceMedium));
			
			priceLarge = Double.parseDouble(tfL.getText());
			lblL.setText("Large\n" + formatter.format(priceLarge));
			
			priceXLarge = Double.parseDouble(tfXL.getText());
			lblXL.setText("Extra Large\n" + formatter.format(priceXLarge));
			
			priceCrustThin = Double.parseDouble(tfThin.getText());
			lblThin.setText("Thin\n" + formatter.format(priceCrustThin));
			
			priceCrustStuff = Double.parseDouble(tfStuff.getText());
			lblStuff.setText("Stuffed Crust\n" + formatter.format(priceCrustStuff));
			
			priceCheeseExtra = Double.parseDouble(tfXCh.getText());
			lblXCh.setText("Extra Cheese\n" + formatter.format(priceCheeseExtra));
			
			priceCheeseMoz = Double.parseDouble(tfMoz.getText());
			lblMoz.setText("Mozarella\n" + formatter.format(priceCheeseMoz));
			
			priceMeatTwo = Double.parseDouble(tfMTwoTop.getText());
			lblMTwoTop.setText("Two Toppings\n" + formatter.format(priceMeatTwo));
			
			priceMeatThree = Double.parseDouble(tfMThrTop.getText());
			lblMThrTop.setText("Three Toppings\n" + formatter.format(priceMeatThree));
			
			priceVegTwo = Double.parseDouble(tfVTwoTop.getText());
			lblVTwoTop.setText("Two Toppings\n" + formatter.format(priceVegTwo));
			
			priceVegThree = Double.parseDouble(tfVThrTop.getText());
			lblVThrTop.setText("Three Toppings\n" + formatter.format(priceVegThree));
			
			priceBevSmall = Double.parseDouble(tfBevSmall.getText());
			lblBevSmall.setText("Small\n" + formatter.format(priceBevSmall));
	
			priceBevMedium = Double.parseDouble(tfBevMed.getText());
			lblBevMed.setText("Medium\n" + formatter.format(priceBevMedium));
	
			priceBevLarge = Double.parseDouble(tfBevLarge.getText());
			lblBevLarge.setText("Large\n" + formatter.format(priceBevLarge));
			
			priceDeliv = Double.parseDouble(tfFee.getText());
			lblFee.setText("Fee\n" + formatter.format(priceDeliv));			
			
		});
		
		
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
		gMain.add(lblDel, 0, 6);
		gMain.add(gDel, 1, 6);
		
		gMain.add(gBottom, 1, 9);
	
		gMain.setHgap(5);
		gMain.setVgap(5);
		
		BorderPane bPane = new BorderPane();
		bPane.setCenter(gMain);
		
		btBack.setOnAction(backButtonPressed -> {
			
			
			bPane.setCenter(managerHome());
			
		});
		
		return bPane;
	}
	
	// Return Manager Customer Search Menu
	// 1.) Search Phone Number
	// 2.) Cancel Button To Manager Home
	public BorderPane managerCustomerSearch()
	{
	
		stageTitle.setTitle("Manager Customer Search Screen - Manager: " + managerIn.managerName );
		
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
		
		gpane2.add(cncl, 0, 0);
		gpane2.add(srch, 1, 0);
		
		gpane2.setHgap(10);
		gpane2.setVgap(10);
		
		gpane.add(phone, 0, 0);
		gpane.add(entry, 1, 0);
		gpane.add(gpane2, 1, 1);
		
		gpane.setHgap(10);
		gpane.setVgap(10);
		
		bpane.setCenter(gpane);
		
		srch.setOnAction(searchButton -> {
			
			// Display Customer History Screen
		//	bpane.getChildren().clear();
			
			for (int i = 0; i < customer.customerArray.length; i++)
			{		
				try
				{		
					if (entry.getText().trim().equals(customer.customerArray[i].custPhone.trim()))
					{
						gpane.getChildren().clear();
						gpane.add(customerHistoryManager(customer.customerArray[i]), 0, 0);               
						break;
					}
				}
				catch(NullPointerException np)
				{
					Text invalid = new Text("Invalid Username/Password");
					invalid.setFill(Color.RED);
					
				//	gpane.getChildren().clear();
					
					gpane.add(invalid, 1, 2);
					break;
				}
			}
		//	bpane.setCenter(customerHistoryManager());
			
		});
		
		cncl.setOnAction(cancelButton -> {
			
			// Display Manager Home Screen
			bpane.getChildren().clear();
			bpane.setCenter(managerCustomerSearch());
			
		});
		
		return bpane;
	}
	
	// Returns Customer View and Edit Information Screen
	// 1.) Submit Button to update customer object
	// 2.) Clear Button to reset text fields
	// 3.) Cancel Button to return to customer home
	public BorderPane customerViewEdit()
	{
		stageTitle.setTitle("Customer Information Screen");
		
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
		Label lblPass = new Label("Password: ");
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
			
		TextArea txDesc = new TextArea(customerIn.custAddDesc);
		txDesc.setPrefHeight(40);
		txDesc.setPrefWidth(200);
		txDesc.setWrapText(true);
			
		TextField tfName = new TextField(customerIn.custName);
		TextField tfPhone = new TextField(customerIn.custPhone);
		TextField tfPass = new TextField(customerIn.custPass);
		TextField tfAddr = new TextField(customerIn.custAddress);
		TextField tfCName = new TextField(customerIn.custCardName);
		TextField tfNum = new TextField(customerIn.custCardNum);
		TextField tfExp = new TextField(customerIn.custCardExp);
		TextField tfSc = new TextField(customerIn.custSecCod);
		TextField tfANum = new TextField(customerIn.custAccount);
		TextField tfRNum = new TextField(customerIn.custRoute);
			
			
		Button submit = new Button("Submit");
		Button clear = new Button("Clear");
		Button back = new Button("Back");
		
		
			
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
		
		gCenterPane.add(lblPass, 0, 2);
		gCenterPane.add(tfPass, 1, 2);
		
		gCenterPane.add(lblAddr, 0, 3);
		gCenterPane.add(tfAddr, 1, 3);
		gCenterPane.add(lblDesc, 0, 4);
		gCenterPane.add(txDesc, 1, 4);
		gCenterPane.add(lblPay, 0, 5);
		gCenterPane.add(gButton, 1, 5);
		gCenterPane.add(lblCard, 0, 6);
		gCenterPane.add(lblCName, 0, 7);
		gCenterPane.add(tfCName, 1, 7);
		gCenterPane.add(lblNum, 0, 8);
		gCenterPane.add(tfNum, 1, 8);
		gCenterPane.add(lblExp, 0, 9);
		gCenterPane.add(tfExp, 1, 9);
		gCenterPane.add(lblSc, 0, 10);
		gCenterPane.add(tfSc, 1, 10);
		gCenterPane.add(lblChk, 0, 11);
		gCenterPane.add(lblANum, 0, 12);
		gCenterPane.add(tfANum, 1, 12);
		gCenterPane.add(lblRNum, 0, 13);
		gCenterPane.add(tfRNum, 1, 13);
			
		GridPane bottomButton = new GridPane();
		
		bottomButton.add(submit, 2, 0);
		bottomButton.add(clear, 1, 0);
		bottomButton.add(back, 0, 0);
		
		bottomButton.setHgap(10);
		bottomButton.setVgap(10);
		
		GridPane bottom = new GridPane();
		bottom.setHgap(10);
		bottom.setVgap(10);
		bottom.setAlignment(Pos.CENTER);
		bottom.add(bottomButton, 0, 0);
		
		
		gBottomPane.add(bottom, 0, 1);

		submit.setOnAction(submitSelected -> {
				
			for (int i = 0; i < customer.customerArray.length; i++)
			{
				if (customerIn.custPass.trim().equals(customer.customerArray[i].custPass.trim()) && customerIn.custPhone.trim().equals(customer.customerArray[i].custPhone))
				{
				
					customer.customerArray[i] = new Customer(tfName.getText().trim(), tfPhone.getText().trim(), tfPass.getText().trim(), tfAddr.getText().trim(),
							txDesc.getText().trim(), tfCName.getText().trim(), tfNum.getText().trim(), tfExp.getText().trim(), tfSc.getText().trim(), tfANum.getText().trim(), tfRNum.getText().trim());
					
					customerIn = new Customer(customer.customerArray[i]);
					break;
				}
			}
	
			
			Text updated = new Text("Information Updated");
			updated.setFill(Color.RED);
			GridPane gupdated = new GridPane();
			gupdated.add(updated, 0, 0);
			bottom.add(updated, 0, 2);
			
			
		});
		
		clear.setOnAction(clearButton -> {
			
			tfName.clear();
			tfPhone.clear();
			tfAddr.clear();
			tfCName.clear();
			tfNum.clear();
			tfExp.clear();
			tfSc.clear();
			tfANum.clear();
			tfRNum.clear();
			txDesc.clear();
			
		});
		
		back.setOnAction(backButton -> {
			
			bPane.getChildren().clear();
			bPane.setCenter(customerHome());
			
		});
		
			
		gBottomPane.setVgap(10);
		gBottomPane.setHgap(10);	
			
		bPane.setCenter(gCenterPane);
		bPane.setBottom(gBottomPane);
			
		return bPane;
			
	}
	
	// Returns Customer View and Edit Information Screen
	// 1.) Submit button to create new customer object
	// 2.) Clear Button to reset text fields
	// 3.) Cancel Button to return to login screen
	public BorderPane customerRegister()
	{
		stageTitle.setTitle("Customer Registration Screen");
		
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
		Label lblPass = new Label("Password: ");
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
		TextField tfPass = new TextField();
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
		gCenterPane.add(lblPass, 0, 2);
		gCenterPane.add(tfPass, 1, 2);
		gCenterPane.add(lblAddr, 0, 3);
		gCenterPane.add(tfAddr, 1, 3);
		gCenterPane.add(lblDesc, 0, 4);
		gCenterPane.add(txDesc, 1, 4);
		gCenterPane.add(lblPay, 0, 5);
		gCenterPane.add(gButton, 1, 5);
		gCenterPane.add(lblCard, 0, 6);
		gCenterPane.add(lblCName, 0, 7);
		gCenterPane.add(tfCName, 1, 7);
		gCenterPane.add(lblNum, 0, 8);
		gCenterPane.add(tfNum, 1, 8);
		gCenterPane.add(lblExp, 0, 9);
		gCenterPane.add(tfExp, 1, 9);
		gCenterPane.add(lblSc, 0, 10);
		gCenterPane.add(tfSc, 1, 10);
		gCenterPane.add(lblChk, 0, 11);
		gCenterPane.add(lblANum, 0, 12);
		gCenterPane.add(tfANum, 1, 12);
		gCenterPane.add(lblRNum, 0, 13);
		gCenterPane.add(tfRNum, 1, 13);
				
				
		gBottomPane.add(submit, 2, 0);
		gBottomPane.add(clear, 1, 0);
		gBottomPane.add(cancel, 0, 0);
		
	
	
		submit.setOnAction(submitButton -> {
			
			// For loop to insert new customer into next available slot
			for (int i = 0; i < customer.customerArray.length; i++)
			{
				try
				{
					if(customer.customerArray[i].isEmpty())
					{
						customer.customerArray[i] = new Customer(tfName.getText().trim(), tfPhone.getText().trim(), tfPass.getText().trim(), tfAddr.getText().trim(),
								txDesc.getText().trim(), tfCName.getText().trim(), tfNum.getText().trim(), tfExp.getText().trim(), tfSc.getText().trim(), tfANum.getText().trim(), tfRNum.getText().trim());
						
						break;
					}
				}
				catch (NullPointerException np)
				{
					customer.customerArray[i] = new Customer(tfName.getText().trim(), tfPhone.getText().trim(), tfPass.getText().trim(), tfAddr.getText().trim(),
							txDesc.getText().trim(), tfCName.getText().trim(), tfNum.getText().trim(), tfExp.getText().trim(), tfSc.getText().trim(), tfANum.getText().trim(), tfRNum.getText().trim());
					break;
				}
				
			}
			
			bPane.getChildren().clear();
			bPane.setCenter(login());
			
		});
			
		clear.setOnAction(clearButton -> {
				
			tfName.clear();
			tfPhone.clear();
			tfPass.clear();
			tfAddr.clear();
			tfCName.clear();
			tfNum.clear();
			tfExp.clear();
			tfSc.clear();
			tfANum.clear();
			tfRNum.clear();
			txDesc.clear();
				
		});
			
		cancel.setOnAction(cancelButton -> {
				
			bPane.getChildren().clear();
			bPane.setCenter(login());
				
		});
			
				
		gBottomPane.setVgap(10);
		gBottomPane.setHgap(10);	
				
		bPane.setCenter(gCenterPane);
		bPane.setBottom(gBottomPane);
				
		return bPane;
				
	}
	
	
	
	// Returns Manager Register Screen
	// 1.) Handle Button to create new manager object
	// 2.) Back Button to return to customer home
	public BorderPane managerRegister()
	{
		
		stageTitle.setTitle("Manager Registration Screen");
		
		BorderPane bpane = new BorderPane();
		GridPane gCenterPane = new GridPane();
		GridPane gBottomPane = new GridPane();
		GridPane gButtonPane = new GridPane();
		
		gCenterPane.setVgap(5);
		gCenterPane.setHgap(5);
		gCenterPane.setAlignment(Pos.CENTER);
				
		gBottomPane.setVgap(10);
		gBottomPane.setHgap(10);
		gBottomPane.setAlignment(Pos.CENTER);
				
		gButtonPane.setVgap(10);
		gButtonPane.setHgap(10);
		gButtonPane.setAlignment(Pos.CENTER);
		
		
		
		Label lblmanagerName = new Label("Enter Name: ");
		Label lblmanagerPass = new Label("Enter Password: ");
		
		TextField tfmanagerName = new TextField();
		TextField tfmanagerPass = new TextField();
		
		gCenterPane.add(lblmanagerName, 0, 0);
		gCenterPane.add(tfmanagerName, 1, 0);
		gCenterPane.add(lblmanagerPass, 0, 1);
		gCenterPane.add(tfmanagerPass, 1, 1);
		
		Button btSubmit = new Button("Submit");
		Button btBack = new Button("Back");
		
		// Handle Back Button Event
		btBack.setOnAction(backPressed -> {
			
			bpane.getChildren().clear();
			bpane.setCenter(login());
			
		});
		
		// Handle Submit Button Event
		btSubmit.setOnAction(submitPressed -> {
			
			managerName = tfmanagerName.getText();
			managerPass = tfmanagerPass.getText();
			
			
			System.out.println(managerName + " added to system.");
			
			// For loop to insert new customer into next available slot
			for (int i = 0; i < manager.managerArray.length; i++)
			{
				try
				{
					if(manager.managerArray[i].isEmpty())
					{
						manager.managerArray[i] = new Manager(tfmanagerName.getText(), tfmanagerPass.getText());
						break;
					}
				}
				catch (NullPointerException np)
				{
					manager.managerArray[i] = new Manager(tfmanagerName.getText(), tfmanagerPass.getText());
					break;
				}
				
			}
			
			bpane.getChildren().clear();
			bpane.setCenter(login());
			
		});
		
		gButtonPane.add(btBack, 0, 1);
		gButtonPane.add(btSubmit, 1, 1);
		
		gBottomPane.add(gButtonPane, 0, 0);
		
		bpane.setCenter(gCenterPane);
		bpane.setBottom(gBottomPane);
		
		
		
		return bpane;
	}
	
	// Return Transaction History
	// 1.) Date Buttons to hold receipt for customer object
	// 2.) Back Button to return to customer home screen
	public BorderPane customerHistory()
	{
		stageTitle.setTitle("Customer Transaction History Screen");
		
		BorderPane bpane = new BorderPane();
		GridPane gcenter = new GridPane();
		GridPane gbutton = new GridPane();
		
		int control = 0;
		try
		{
			for (control = 0; control < customerIn.receiptArray.length; control++)
			{
				gcenter.add(customerIn.receiptArray[control].dateButton, 0, control);
				
			}	
		}
		catch(NullPointerException np)
		{
			if (control == 0)
				gcenter.add(new Label("No Past Orders"), 0, 0);
		//	if (control == (customerIn.receiptArray.l))
		}
		Button btBack = new Button("Back");
		
		
		gbutton.add(btBack, 0, 1);
		
		gbutton.setHgap(10);
		gbutton.setVgap(10);
		gbutton.setAlignment(Pos.CENTER);

		gcenter.setHgap(10);
		gcenter.setVgap(10);
		gcenter.setAlignment(Pos.CENTER);
		
		bpane.setTop(customerInfoDuringOrder());
		bpane.setCenter(gcenter);
		bpane.setBottom(gbutton);
		
		// Handle Button Action Event
		btBack.setOnAction(backPressed -> {
			
			// Display Customer Home Screen
			bpane.getChildren().clear();
			bpane.setCenter(customerHome());
			
		});
		
		return bpane;
		
	}
	
	// Return Manager Customer Search History
	// 1.) Date Buttons to hold receipt for customer object
	// 2.) Back Button to return to manager home screen
	public BorderPane customerHistoryManager(Customer cust)
	{
		
		stageTitle.setTitle("Customer Search Screen - Manager: " + managerIn.managerName);
		
		BorderPane bpane = new BorderPane();
		GridPane gcenter = new GridPane();
		GridPane gbutton = new GridPane();
		
		int control = 0;
		try
		{
			for (control = 0; control < cust.receiptArray.length; control++)
			{
				gcenter.add(cust.receiptArray[control].dateButton, 0, control);
				
			}	
		}
		catch(NullPointerException np)
		{
			if (control == 0)
				gcenter.add(new Label("No Past Orders"), 0, 0);
		//	if (control == (customerIn.receiptArray.l))
		}
		Button btBack = new Button("Back");
		
		
		gbutton.add(btBack, 0, 1);
		
		gbutton.setHgap(10);
		gbutton.setVgap(10);
		gbutton.setAlignment(Pos.CENTER);

		gcenter.setHgap(10);
		gcenter.setVgap(10);
		gcenter.setAlignment(Pos.CENTER);
		
		bpane.setTop(customerInfoDuringOrder());
		bpane.setCenter(gcenter);
		bpane.setBottom(gbutton);
		
		// Handle Button Action Event
		btBack.setOnAction(backPressed -> {
			
			// Display Customer Home Screen
			bpane.getChildren().clear();
			bpane.setCenter(managerHome());
			
		});
		
		return bpane;
		
	}
	
	// Return Order menu
	// 1.) Submit button to process order
	// 2.) Back button to return to home screen
	public BorderPane orderMenuCustom()
	{
		
		stageTitle.setTitle("Order Menu Screen");
		
		// Border Pane and Grid Panes For Each Node
		BorderPane bPane = new BorderPane();
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
		GridPane gBevSize = new GridPane();
				
		gCenter.setHgap(10);
		gCenter.setVgap(10);
		gCenter.setAlignment(Pos.CENTER);
				
		gBottom.setHgap(10);
		gBottom.setAlignment(Pos.CENTER);
				
		// Section Labels
		Label lblSize = new Label("Size: ");
		Label lblCrust = new Label("Crust: \n\n4 Toppings Maximum Allowed");
		Label lblRqst = new Label("Special Requests: ");
		Label lblDop = new Label("Delivery or Pick-up: ");
				
		TextArea taRqst = new TextArea();
		taRqst.setPrefHeight(10);
		taRqst.setPrefWidth(50);
		taRqst.setWrapText(true);
				
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		// Pizza Size Check Box
		Label lblPriceS = new Label(formatter.format(priceSmall));
		Label lblPriceM = new Label(formatter.format(priceMedium));
		Label lblPriceL = new Label(formatter.format(priceLarge));
		Label lblPriceXL = new Label(formatter.format(priceXLarge));
				
		CheckBox cbSmall = new CheckBox("Small\n(4 Slices)\n" + lblPriceS.getText());
		CheckBox cbMedium = new CheckBox("Medium\n(6 Slices)\n"+ lblPriceM.getText());
		CheckBox cbLarge = new CheckBox("Large\n(8 Slices)\n" + lblPriceL.getText());
		CheckBox cbXLarge = new CheckBox("X-Large\n(10 Slices)\n" + lblPriceXL.getText());
		
		cbSmall.setOnAction(smallChecked -> {
			
			if (cbSmall.isSelected())
			{
				cbMedium.setDisable(true);
				cbLarge.setDisable(true);
				cbXLarge.setDisable(true);
				
				orderSize = "Small";
				total += 4.0;
				
			}
			else
			{
				cbMedium.setDisable(false);
				cbLarge.setDisable(false);
				cbXLarge.setDisable(false);
				total -= 4.0;
			}
			
		});
				
		cbMedium.setOnAction(smallChecked -> {
			
			if (cbMedium.isSelected())
			{
				cbSmall.setDisable(true);
				cbLarge.setDisable(true);
				cbXLarge.setDisable(true);
				
				orderSize = "Medium";
				total += 6.0;
			}
			else
			{
				cbSmall.setDisable(false);
				cbLarge.setDisable(false);
				cbXLarge.setDisable(false);
				total -= 6.0;
			}
			
		});
		
		cbLarge.setOnAction(smallChecked -> {
			
			if (cbLarge.isSelected())
			{
				cbSmall.setDisable(true);
				cbMedium.setDisable(true);
				cbXLarge.setDisable(true);
				
				orderSize = "Large";
				total += 8.0;
				
			}
			else
			{
				cbSmall.setDisable(false);
				cbMedium.setDisable(false);
				cbXLarge.setDisable(false);
				total -= 8.0;
			}
			
		});
		
		cbXLarge.setOnAction(smallChecked -> {

			if (cbXLarge.isSelected())
			{
				cbSmall.setDisable(true);
				cbMedium.setDisable(true);
				cbLarge.setDisable(true);
				
				orderSize = "Extra Large";
				total += 10.0;
				
			}
			else
			{
				cbSmall.setDisable(false);
				cbMedium.setDisable(false);
				cbLarge.setDisable(false);
				total -= 10.0;
			}
			
		});
		
		gSize.add(cbSmall, 0, 0);
		gSize.add(cbMedium, 3, 0);
		gSize.add(cbLarge, 6, 0);
		gSize.add(cbXLarge, 9, 0);
		gSize.setHgap(2);
		gSize.setVgap(2);
				
		// Crust CheckBox
		Label lblPriceT = new Label(formatter.format(priceCrustThin));
		Label lblPriceSC = new Label(formatter.format(priceCrustStuff));
				
		CheckBox cbOrig = new CheckBox("Original");
		CheckBox cbThin = new CheckBox("Thin\n" + lblPriceT.getText());		
		CheckBox cbSc = new CheckBox("Stuffed\n" + lblPriceSC.getText());
			
		// Handling checkbox events to allow only one crust
		cbOrig.setOnAction(originalChecked -> {
			
			if (cbOrig.isSelected())
			{
				cbThin.setDisable(true);
				cbSc.setDisable(true);
				
				orderCrust = "Original";
			}
			else
			{
				cbThin.setDisable(false);
				cbSc.setDisable(false);
			}
			
		});
		
		cbThin.setOnAction(thinChecked -> {
			
			if (cbThin.isSelected())
			{
				cbOrig.setDisable(true);
				cbSc.setDisable(true);
				orderCrust = "Thin";
				total += 1.5;
			}
			else
			{
				cbOrig.setDisable(false);
				cbSc.setDisable(false);
				total -= 1.5;
			}
			
		});
		
		cbSc.setOnAction(stuffChecked -> {
			
			if (cbSc.isSelected())
			{
				cbThin.setDisable(true);
				cbOrig.setDisable(true);
				
				orderCrust = "Stuffed";
				total += 2.0;
			}
			else
			{
				cbThin.setDisable(false);
				cbOrig.setDisable(false);
				total -= 2.0;
			}
			
		});
		
		// Add Check Box to Crust pane
		gCrust.add(cbOrig, 0, 0);
		gCrust.add(cbThin, 6, 0);
		gCrust.add(cbSc, 3, 0);
		gCrust.setVgap(2);
		gCrust.setHgap(2);
				
		Label lblC = new Label("Cheese:");
		
		Label lblXCheeseP = new Label(formatter.format(priceCheeseExtra));
		Label lblMozP = new Label(formatter.format(priceCheeseMoz));
				
		CheckBox cbCheese = new CheckBox("Cheese");
		CheckBox cbXCheese = new CheckBox("Extra Cheese\n+" + lblXCheeseP.getText());
		CheckBox cbMoz = new CheckBox("Mozarella\n+" + lblMozP.getText());
		
		cbCheese.setOnAction(cheeseChecked -> {
			
			if (cbCheese.isSelected())
			{
				++cheeseTopCount;
				if (cheeseTopCount == 1)
					orderCheese1 = "Cheese";
			}
			else
			{
				--cheeseTopCount;
				if (cheeseTopCount == 0)
					orderCheese1 = "";
			}
			
		});
		
		cbXCheese.setOnAction(extraChecked -> {
			
			
			
			if(cbXCheese.isSelected())
			{
				cbCheese.setDisable(true);
				++cheeseTopCount;
				total += 1.0;
				if (cheeseTopCount == 1)
					orderCheese1 = "Extra Cheese";
				if (cheeseTopCount == 2)
				{
					orderCheese2 = "Extra Cheese";
				}
			}
			else
			{
				cbCheese.setDisable(false);
				--cheeseTopCount;
				total -= 1.0;
				if (cheeseTopCount == 0)
					orderCheese1 = "";
				if (cheeseTopCount == 1)
					orderCheese2 = "";
			}
		});
		
		cbMoz.setOnAction(mozChecked -> {
			
			if(cbMoz.isSelected())
			{
				++cheeseTopCount;
				total += 1.5;
				if (cheeseTopCount == 1)
					orderCheese1 = "Mozarella";
				if (cheeseTopCount == 2)
					orderCheese2 = "Mozarella";
				
			}
			else
			{
				--cheeseTopCount;
				total -= 1.5;
				if (cheeseTopCount == 0)
					orderCheese1 = "";
				if (cheeseTopCount == 1)
					orderCheese2 = "";
			}
			
		});
				
		gCheese.add(cbCheese, 0, 0);
		gCheese.add(cbXCheese, 3, 0);
		gCheese.add(cbMoz, 6, 0);
		
		// Meat Topping's Check Box
		CheckBox cbPep = new CheckBox("Pepperoni");
		CheckBox cbChees = new CheckBox("Three-Cheese");
		CheckBox cbSaus = new CheckBox("Sausage");
		CheckBox cbChic = new CheckBox("Chicken");
		CheckBox cbBeef = new CheckBox("Beef");
		CheckBox cbMeat = new CheckBox("Meat Ball");
		CheckBox cbHam = new CheckBox("Ham");
		CheckBox cbBacon = new CheckBox("Bacon");
		
		cbPep.setOnAction(pepChecked -> {
			
			if (cbPep.isSelected())
			{
				++meatTopCount;
				if (meatTopCount == 1)
					orderMeat1 = "Pepperoni";
				if (meatTopCount == 2)
					orderMeat2 = "Pepperoni";
				if (meatTopCount == 3)
					orderMeat3 = "Pepperoni";
				
			}
			else
			{
				--meatTopCount;
				if (meatTopCount == 0)
					orderMeat1 = "";
				if (meatTopCount == 1)
					orderMeat2 = "";
				if (meatTopCount == 2)
					orderMeat3 = "";
			}
		});
		
		cbChees.setOnAction(cheesChecked -> {
			
			if (cbChees.isSelected())
			{
				++meatTopCount;
				if (meatTopCount == 1)
					orderMeat1 = "Three-Cheese";
				if (meatTopCount == 2)
					orderMeat2 = "Three-Cheese";
				if (meatTopCount == 3)
					orderMeat3 = "Three-Cheese";
			}
			else
			{
				--meatTopCount;
				if (meatTopCount == 0)
					orderMeat1 = "";
				if (meatTopCount == 1)
					orderMeat2 = "";
				if (meatTopCount == 2)
					orderMeat3 = "";
			}
		});
		
		cbSaus.setOnAction(sausChecked -> {

			if (cbSaus.isSelected())
			{
				++meatTopCount;
				if (meatTopCount == 1)
					orderMeat1 = "Sausage";
				if (meatTopCount == 2)
					orderMeat2 = "Sausage";
				if (meatTopCount == 3)
					orderMeat3 = "Sausage";
			}
			else
			{
				--meatTopCount;
				if (meatTopCount == 0)
					orderMeat1 = "";
				if (meatTopCount == 1)
					orderMeat2 = "";
				if (meatTopCount == 2)
					orderMeat3 = "";
			}
			
		});
		
		cbChic.setOnAction(chicChecked -> {

			if (cbChic.isSelected())
			{
				++meatTopCount;
				if (meatTopCount == 1)
					orderMeat1 = "Chicken";
				if (meatTopCount == 2)
					orderMeat2 = "Chicken";
				if (meatTopCount == 3)
					orderMeat3 = "Chicken";
			}
			else
			{
				--meatTopCount;
				if (meatTopCount == 0)
					orderMeat1 = "";
				if (meatTopCount == 1)
					orderMeat2 = "";
				if (meatTopCount == 2)
					orderMeat3 = "";
			}
			
		});		
		
		cbBeef.setOnAction(beefChecked -> {

			if (cbBeef.isSelected())
			{
				++meatTopCount;
				if (meatTopCount == 1)
					orderMeat1 = "Beef";
				if (meatTopCount == 2)
					orderMeat2 = "Beef";
				if (meatTopCount == 3)
					orderMeat3 = "Beef";
			}
			else
			{
				--meatTopCount;
				if (meatTopCount == 0)
					orderMeat1 = "";
				if (meatTopCount == 1)
					orderMeat2 = "";
				if (meatTopCount == 2)
					orderMeat3 = "";
			}
			
		});
		
		cbMeat.setOnAction(meatChecked -> {

			if (cbMeat.isSelected())
			{
				++meatTopCount;
				if (meatTopCount == 1)
					orderMeat1 = "Meat Ball";
				if (meatTopCount == 2)
					orderMeat2 = "Meat Ball";
				if (meatTopCount == 3)
					orderMeat3 = "Meat Ball";
			}
			else
			{
				--meatTopCount;
				if (meatTopCount == 0)
					orderMeat1 = "";
				if (meatTopCount == 1)
					orderMeat2 = "";
				if (meatTopCount == 2)
					orderMeat3 = "";
			}
			
		});

		cbHam.setOnAction(hamChecked -> {

			if (cbHam.isSelected())
			{
				++meatTopCount;
				if (meatTopCount == 1)
					orderMeat1 = "Ham";
				if (meatTopCount == 2)
					orderMeat2 = "Ham";
				if (meatTopCount == 3)
					orderMeat3 = "Ham";
			}
			else
			{
				--meatTopCount;
				if (meatTopCount == 0)
					orderMeat1 = "";
				if (meatTopCount == 1)
					orderMeat2 = "";
				if (meatTopCount == 2)
					orderMeat3 = "";
			}
			
		});		
		
		cbBacon.setOnAction(baconChecked -> {

			if (cbBacon.isSelected())
			{
				++meatTopCount;
				if (meatTopCount == 1)
					orderMeat1 = "Bacon";
				if (meatTopCount == 2)
					orderMeat2 = "Bacon";
				if (meatTopCount == 3)
					orderMeat3 = "Bacon";
			}
			else
			{
				--meatTopCount;
				if (meatTopCount == 0)
					orderMeat1 = "";
				if (meatTopCount == 1)
					orderMeat2 = "";
				if (meatTopCount == 2)
					orderMeat3 = "";
			}
			
		});		
		
		Label lblTwoP = new Label(formatter.format(priceMeatTwo));
		Label lblThreeP = new Label(formatter.format(priceMeatThree));
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
				
		cbRedO.setOnAction(redChecked -> {
			
			if(cbRedO.isSelected())
			{
				++vegTopCount;
				if (vegTopCount == 1)
					orderVeg1 = "Red Onion";
				if (vegTopCount == 2)
					orderVeg2 = "Red Onion";
				if (vegTopCount == 3)
					orderVeg3 = "Red Onion";
			}
			else
			{
				--vegTopCount;
				if (vegTopCount == 0)
					orderVeg1 = "";
				if (vegTopCount == 1)
					orderVeg2 = "";
				if (vegTopCount == 2)
					orderVeg3 = "";
			}
			
		});
		
		cbBlkO.setOnAction(blackChecked -> {
	
			if(cbBlkO.isSelected())
			{
				++vegTopCount;
				if (vegTopCount == 1)
					orderVeg1 = "Black Olive";
				if (vegTopCount == 2)
					orderVeg2 = "Black Olive";
				if (vegTopCount == 3)
					orderVeg3 = "Black Olive";
			}
			else
			{
				--vegTopCount;
				if (vegTopCount == 0)
					orderVeg1 = "";
				if (vegTopCount == 1)
					orderVeg2 = "";
				if (vegTopCount == 2)
					orderVeg3 = "";
			}
			
		});
		
		cbMush.setOnAction(mushroomChecked -> {
			
			if(cbMush.isSelected())
			{
				++vegTopCount;
				if (vegTopCount == 1)
					orderVeg1 = "Mushroom";
				if (vegTopCount == 2)
					orderVeg2 = "Mushroom";
				if (vegTopCount == 3)
					orderVeg3 = "Mushroom";
			}
			else
			{
				--vegTopCount;
				if (vegTopCount == 0)
					orderVeg1 = "";
				if (vegTopCount == 1)
					orderVeg2 = "";
				if (vegTopCount == 2)
					orderVeg3 = "";
			}
			
		});
		
		cbSpin.setOnAction(spinachChecked -> {
			
			if(cbSpin.isSelected())
			{
				++vegTopCount;
				if (vegTopCount == 1)
					orderVeg1 = "Spinach";
				if (vegTopCount == 2)
					orderVeg2 = "Spinach";
				if (vegTopCount == 3)
					orderVeg3 = "Spinach";
			}
			else
			{
				--vegTopCount;
				if (vegTopCount == 0)
					orderVeg1 = "";
				if (vegTopCount == 1)
					orderVeg2 = "";
				if (vegTopCount == 2)
					orderVeg3 = "";
			}
			
		});		
		
		cbGPep.setOnAction(greenPepChecked -> {
			
			if(cbGPep.isSelected())
			{
				++vegTopCount;
				if (vegTopCount == 1)
					orderVeg1 = "Green Pepper";
				if (vegTopCount == 2)
					orderVeg2 = "Green Pepper";
				if (vegTopCount == 3)
					orderVeg3 = "Green Pepper";
			}
			else
			{
				--vegTopCount;
				if (vegTopCount == 0)
					orderVeg1 = "";
				if (vegTopCount == 1)
					orderVeg2 = "";
				if (vegTopCount == 2)
					orderVeg3 = "";
			}
			
		});	
		
		cbBPep.setOnAction(blackPepChecked -> {
			
			if(cbBPep.isSelected())
			{
				++vegTopCount;
				if (vegTopCount == 1)
					orderVeg1 = "Black Pepper";
				if (vegTopCount == 2)
					orderVeg2 = "Black Pepper";
				if (vegTopCount == 3)
					orderVeg3 = "Black Pepper";
			}
			else
			{
				--vegTopCount;
				if (vegTopCount == 0)
					orderVeg1 = "";
				if (vegTopCount == 1)
					orderVeg2 = "";
				if (vegTopCount == 2)
					orderVeg3 = "";
			}
			
		});
		
		cbPine.setOnAction(pineChecked -> {
			
			if(cbPine.isSelected())
			{
				++vegTopCount;
				if (vegTopCount == 1)
					orderVeg1 = "Pineapple";
				if (vegTopCount == 2)
					orderVeg2 = "Pineapple";
				if (vegTopCount == 3)
					orderVeg3 = "Pineapple";
			}
			else
			{
				--vegTopCount;
				if (vegTopCount == 0)
					orderVeg1 = "";
				if (vegTopCount == 1)
					orderVeg2 = "";
				if (vegTopCount == 2)
					orderVeg3 = "";
			}
			
		});
		
		cbJalp.setOnAction(jalChecked -> {
			
			if(cbJalp.isSelected())
			{
				++vegTopCount;
				if (vegTopCount == 1)
					orderVeg1 = "Jalapeno Pepper";
				if (vegTopCount == 2)
					orderVeg2 = "Jalapeno Pepper";
				if (vegTopCount == 3)
					orderVeg3 = "Jalapeno Pepper";
			}
			else
			{
				--vegTopCount;
				if (vegTopCount == 0)
					orderVeg1 = "";
				if (vegTopCount == 1)
					orderVeg2 = "";
				if (vegTopCount == 2)
					orderVeg3 = "";
			}
			
		});
		
		cbToma.setOnAction(tomaChecked -> {
			
			if(cbToma.isSelected())
			{
				++vegTopCount;
				if (vegTopCount == 1)
					orderVeg1 = "Tomatoes";
				if (vegTopCount == 2)
					orderVeg2 = "Tomatoes";
				if (vegTopCount == 3)
					orderVeg3 = "Tomatoes";
			}
			else
			{
				--vegTopCount;
				if (vegTopCount == 0)
					orderVeg1 = "";
				if (vegTopCount == 1)
					orderVeg2 = "";
				if (vegTopCount == 2)
					orderVeg3 = "";
			}
			
		});
		
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
				
		Label lblVTwoP = new Label(formatter.format(priceVegTwo));
		Label lblVThreeP = new Label(formatter.format(priceVegThree));
		Label lblVeg = new Label("Veggetable Toppings: \n2 toppings: +" + lblVTwoP.getText() + "\n3 toppings: +" + lblVThreeP.getText());
			
		// Beverage Check Box
		CheckBox cbCoke = new CheckBox("Coca-Cola");
		CheckBox cbZero = new CheckBox("Coca-Cola Zero");
		CheckBox cbDiet = new CheckBox("Diet Coca-Cola");
		CheckBox cbSprt = new CheckBox("Sprite");
		CheckBox cbFant = new CheckBox("Fanta Orange");
		
		cbCoke.setOnAction(cokeSelected -> {
			
			if (cbCoke.isSelected())
			{
				orderBev = "Coca-Cola";
				cbZero.setDisable(true);
				cbDiet.setDisable(true);
				cbSprt.setDisable(true);
				cbFant.setDisable(true);
				
			}
			else
			{
				orderBev = "";
				cbZero.setDisable(false);
				cbDiet.setDisable(false);
				cbSprt.setDisable(false);
				cbFant.setDisable(false);
			}
		});
		
		cbZero.setOnAction(zeroSelected -> {
			
			if (cbZero.isSelected())
			{
				orderBev = "Coca-Cola Zero";
				cbCoke.setDisable(true);
				cbDiet.setDisable(true);
				cbSprt.setDisable(true);
				cbFant.setDisable(true);
			}
			else
			{
				orderBev = "";
				cbCoke.setDisable(false);
				cbDiet.setDisable(false);
				cbSprt.setDisable(false);
				cbFant.setDisable(false);
			}
		});
		
		cbDiet.setOnAction(dietSelected -> {
			
			if (cbDiet.isSelected())
			{
				orderBev = "Diet Coca-Cola";
				cbCoke.setDisable(true);
				cbZero.setDisable(true);
				cbSprt.setDisable(true);
				cbFant.setDisable(true);
			}
			else
			{
				orderBev = "";
				cbCoke.setDisable(false);
				cbZero.setDisable(false);
				cbSprt.setDisable(false);
				cbFant.setDisable(false);
				
			}
		});
		
		cbSprt.setOnAction(spriteSelected -> {
			
			if (cbSprt.isSelected())
			{
				orderBev = "Sprite";
				cbCoke.setDisable(true);
				cbZero.setDisable(true);
				cbDiet.setDisable(true);
				cbFant.setDisable(true);
				
			}
			else
			{
				orderBev = "";
				cbCoke.setDisable(false);
				cbZero.setDisable(false);
				cbDiet.setDisable(false);
				cbFant.setDisable(false);
			}
		});
		
		cbFant.setOnAction(fantaSelected -> {
			
			if(cbFant.isSelected())
			{
				orderBev = "Fanta Orange";
				cbCoke.setDisable(true);
				cbZero.setDisable(true);
				cbDiet.setDisable(true);
				cbSprt.setDisable(true);
			}
			else
			{
				orderBev = "";
				cbCoke.setDisable(false);
				cbZero.setDisable(false);
				cbDiet.setDisable(false);
				cbSprt.setDisable(false);
			}
		});
				
		gBev.add(cbCoke, 0, 0);
		gBev.add(cbZero, 3, 0);
		gBev.add(cbDiet, 6, 0);
		gBev.add(cbSprt, 0, 1);
		gBev.add(cbFant, 3, 1);
		gBev.setHgap(2);
		gBev.setVgap(2);
		
		Label lblBev = new Label("Beverage: ");
		
		Label lblBevSize = new Label("Beverage\nSize: ");
		
		Label lblSmallP = new Label(formatter.format(priceBevSmall));
		Label lblMediumP = new Label(formatter.format(priceBevMedium));
		Label lblLargeP = new Label(formatter.format(priceBevLarge));
		
		CheckBox cbBevSmall = new CheckBox("Small\n" + lblSmallP.getText());
		CheckBox cbBevMedium = new CheckBox("Medium\n" + lblMediumP.getText());
		CheckBox cbBevLarge = new CheckBox("Large\n" + lblLargeP.getText());
		
		cbBevSmall.setOnAction(smallSelected -> {
			
			if(cbBevSmall.isSelected())
			{
				orderBevSize = "Small";
				total += 2.0;
				cbBevMedium.setDisable(true);
				cbBevLarge.setDisable(true);
			}
			else
			{
				orderBevSize = "";
				total -= 2.0;
				cbBevMedium.setDisable(false);
				cbBevLarge.setDisable(false);
			}
			
			
		});
		
		cbBevMedium.setOnAction(mediumSelected -> {
			
			if(cbBevMedium.isSelected())
			{
				orderBevSize = "Medium";
				total += 2.5;
				cbBevSmall.setDisable(true);
				cbBevLarge.setDisable(true);
			}
			else
			{
				orderBevSize = "";
				total -= 2.5;
				cbBevSmall.setDisable(false);
				cbBevLarge.setDisable(false);
			}
			
			
		});
		
		cbBevLarge.setOnAction(largeSelected -> {
			
			if (cbBevLarge.isSelected())
			{
				orderBevSize = "Large";
				total += 3.0;
				cbBevSmall.setDisable(true);
				cbBevMedium.setDisable(true);
			}
			else
			{
				orderBevSize = "";
				total -= 3.0;
				cbBevSmall.setDisable(false);
				cbBevMedium.setDisable(false);
			}
		});
		
		gBevSize.add(cbBevSmall, 0, 0);
		gBevSize.add(cbBevMedium, 3, 0);
		gBevSize.add(cbBevLarge, 6, 0);
		
		
		// Delivery/Pickup Check Box
		Label lblDelP = new Label(formatter.format(priceDeliv));
		CheckBox cbDel = new CheckBox("Delivery \n+" + lblDelP.getText());
		CheckBox cbPic = new CheckBox("Pick-up");
		
		cbDel.setOnAction(deliverySelected -> {
			
			if (cbDel.isSelected())
			{
				orderDelivery = "Delivery";
				total += 6.0;
				cbPic.setDisable(true);
			}
			else
			{
				orderDelivery = "";
				total -= 6.0;
				cbPic.setDisable(false);
			}
			
		});
		
		cbPic.setOnAction(pickupSelected -> {
			
			if(cbPic.isSelected())
			{
				orderDelivery = "Pick-up";
				cbPic.setDisable(true);
			}
			else
			{
				orderDelivery = "";
				cbPic.setDisable(false);
			}
		});
				
		gDop.add(cbDel, 0, 0);
		gDop.add(cbPic, 1, 0);
		gDop.setHgap(2);
		gDop.setVgap(2);
				
		// Body Grid Pane - Add labels and grid panes
		gCenter.add(lblSize, 0, 0);
		gCenter.add(gSize, 1, 0);
		gCenter.add(lblCrust, 0, 1);
		gCenter.add(gCrust, 1, 1);
				
		gCenter.add(lblC, 0, 2);
		gCenter.add(gCheese, 1, 2);
				
		gCenter.add(lblMTop, 0,3);
		gCenter.add(gMTop, 1, 3);//
		gCenter.add(lblVeg, 0, 4);
		gCenter.add(gVeg, 1, 4);
		gCenter.add(lblRqst, 0, 5);
		gCenter.add(taRqst, 1, 5);
		gCenter.add(lblBev, 0, 6);
		gCenter.add(gBev, 1, 6);
		gCenter.add(lblBevSize, 0, 7);
		gCenter.add(gBevSize, 1, 7);
		gCenter.add(lblDop, 0, 8);
		gCenter.add(gDop, 1, 8);
		
				
		Button btBack = new Button("Back");
		Button btSub = new Button("Submit");
		
		btBack.setOnAction(backPressed -> {
			
			bPane.getChildren().clear();
			bPane.setCenter(customerHome());
			
			meatTopCount = 0;
			vegTopCount = 0;
			cheeseTopCount = 0;
			
			orderSize = "";
			orderCrust = "";
			orderCheese1 = "";
			orderCheese2 = "";
			orderMeat1 = "";
			orderMeat2 = "";
			orderMeat3 = "";
			orderVeg1 = "";
			orderVeg2 = "";
			orderVeg3 = "";
			orderRequest = "";
			orderBev = "";
			orderBevSize = "";
			orderDelivery = "";
			
		});
		
		btSub.setOnAction(submitPressed -> {
			
			topCount = meatTopCount + cheeseTopCount + vegTopCount;
			
			if (topCount > 4)
			{
				Text top = new Text("More than 4 topping's selected");
				top.setFill(Color.RED);
				
				bPane.setBottom(top);
				bPane.setAlignment(top, Pos.TOP_CENTER);
				
				
			}
			if(meatTopCount == 2)
			{
				total += 2.0;
			}
			else if(meatTopCount == 3)
			{
				total += 3.0;
			}
			if(vegTopCount == 2)
			{
				total += 1.0;
			}
			else if(vegTopCount == 3)
			{
				total += 2.0;
			}
			else
			{
				bPane.getChildren().clear();
				bPane.setCenter(orderConfirmation());
				
			}
			
		});
		
		gBottom.add(btBack, 0, 0);
		gBottom.add(btSub, 3, 0);
		gBottom.setHgap(10);
		gBottom.setVgap(10);
				
		gCenter.add(gBottom, 1, 9);
				
		// Set Border Pane with grid pane's
		bPane.setCenter(gCenter);
		bPane.setTop(customerInfoDuringOrder());
				
		return bPane;

	}
	
	public BorderPane receipt() {
		
		stageTitle.setTitle("Receipt Screen");
		
		BorderPane bpane = new BorderPane();
		
		GridPane gcenter = new GridPane();
		
		Label lblSize = new Label("Pizza Size:\t\t\t" + orderSize);
		Label lblCrust = new Label("Crust:\t\t\t\t" + orderCrust);
		Label lblCheese1 = new Label("Cheese1:\t\t\t\t" + orderCheese1);
		Label lblCheese2 = new Label("Cheese2:\t\t\t\t" + orderCheese2);
		Label lblMeatTop1 = new Label("Meat Topping1:\t\t" + orderMeat1);
		Label lblMeatTop2 = new Label("Meat Topping2:\t\t" + orderMeat2);
		Label lblMeatTop3 = new Label("Meat Topping3:\t\t" + orderMeat3);
		Label lblVegTop1 = new Label("Veggetable Topping1:\t" + orderVeg1);
		Label lblVegTop2 = new Label("Veggetable Topping2:\t" + orderVeg2);
		Label lblVegTop3 = new Label("Veggetable Topping3:\t" + orderVeg3);
		Label lblRequest = new Label("Special Request:\t\t" + orderRequest);
		Label lblBev = new Label("Beverage:\t\t\t" + orderBev + " " + orderBevSize);
		Label lblDel = new Label("Delivery or Pickup:\t\t" + orderDelivery);
		Label lblTotal = new Label("Order Total: \t\t\t$" + total);
		customerIn.setDate();
			
		Label lblDate = new Label("Order Date:\t\t\t" + customerIn.orderDateString);
		
		// String array to send to receipt
		String[] orderDetails = {orderSize, orderCrust, orderCheese1, orderCheese2,
								 orderMeat1, orderMeat2, orderMeat3, orderVeg1,
								 orderVeg2, orderVeg3, orderRequest, orderBev,
								 orderBevSize, orderDelivery, String.valueOf(total)};
		
		int tcControl = 0;
		try
		{
			for (tcControl = 0; tcControl < customerIn.receiptArray.length; tcControl++)
			{
				if (customerIn.receiptArray[tcControl].equals(null))
					new NullPointerException();
			}
		}
		catch(NullPointerException np)
		{
			customerIn.receiptArray[tcControl] = new Receipt(customerIn, orderDetails);
			
		}
		
		gcenter.add(lblSize, 0, 0);
		gcenter.add(lblCrust, 0, 1);
		gcenter.add(lblCheese1, 0, 2);
		gcenter.add(lblCheese2, 0, 3);
		gcenter.add(lblMeatTop1, 0, 4);
		gcenter.add(lblMeatTop2, 0, 5);
		gcenter.add(lblMeatTop3, 0, 6);
		gcenter.add(lblVegTop1, 0, 7);
		gcenter.add(lblVegTop2, 0, 8);
		gcenter.add(lblVegTop3, 0, 9);
		gcenter.add(lblRequest, 0, 10);
		gcenter.add(lblBev, 0, 11);
		gcenter.add(lblDel, 0, 12);
		gcenter.add(lblDate, 0, 13);
		gcenter.add(lblTotal, 0, 14);
		
		Button btDone = new Button("Done");
		
		btDone.setOnAction(doneSelected -> {
			
			bpane.getChildren().clear();
			total = 0;
			meatTopCount = 0;
			vegTopCount = 0;
			cheeseTopCount = 0;
			
			orderSize = "";
			orderCrust = "";
			orderCheese1 = "";
			orderCheese2 = "";
			orderMeat1 = "";
			orderMeat2 = "";
			orderMeat3 = "";
			orderVeg1 = "";
			orderVeg2 = "";
			orderVeg3 = "";
			orderRequest = "";
			orderBev = "";
			orderBevSize = "";
			orderDelivery = "";
			bpane.setCenter(customerHome());
			
		});
		
		GridPane buttonPane = new GridPane();
		
		buttonPane.add(btDone, 0, 1);
		
		buttonPane.setHgap(10);
		buttonPane.setVgap(10);
		buttonPane.setAlignment(Pos.CENTER);
		
		bpane.setCenter(gcenter);
		bpane.setBottom(buttonPane);
		bpane.setTop(customerInfoDuringOrder());
		
		return bpane;
		
	}
	
	public BorderPane orderConfirmation()
	{
		BorderPane bpane = new BorderPane();
		stageTitle.setTitle("Order Confirmation Screen");
		
		GridPane gcenter = new GridPane();

		Label lblSize = new Label("Pizza Size:\t\t\t" + orderSize);
		Label lblCrust = new Label("Crust:\t\t\t\t" + orderCrust);
		Label lblCheese1 = new Label("Cheese1:\t\t\t\t" + orderCheese1);
		Label lblCheese2 = new Label("Cheese2:\t\t\t\t" + orderCheese2);
		Label lblMeatTop1 = new Label("Meat Topping1:\t\t" + orderMeat1);
		Label lblMeatTop2 = new Label("Meat Topping2:\t\t" + orderMeat2);
		Label lblMeatTop3 = new Label("Meat Topping3:\t\t" + orderMeat3);
		Label lblVegTop1 = new Label("Veggetable Topping1:\t" + orderVeg1);
		Label lblVegTop2 = new Label("Veggetable Topping2:\t" + orderVeg2);
		Label lblVegTop3 = new Label("Veggetable Topping3:\t" + orderVeg3);
		Label lblRequest = new Label("Special Request:\t\t" + orderRequest);
		Label lblBev = new Label("Beverage:\t\t\t" + orderBev + " " + orderBevSize);
		Label lblDel = new Label("Delivery or Pickup:\t\t" + orderDelivery);
		Label lblTotal = new Label("Order Total: \t\t\t$"+ String.valueOf(total));
		
		orderDate = new Date();
		orderDateString = orderDate.toString();

		Label lblDate = new Label("Order Date:\t\t\t" + orderDateString);


		gcenter.add(lblSize, 0, 0);
		gcenter.add(lblCrust, 0, 1);
		gcenter.add(lblCheese1, 0, 2);
		gcenter.add(lblCheese2, 0, 3);
		gcenter.add(lblMeatTop1, 0, 4);
		gcenter.add(lblMeatTop2, 0, 5);
		gcenter.add(lblMeatTop3, 0, 6);
		gcenter.add(lblVegTop1, 0, 7);
		gcenter.add(lblVegTop2, 0, 8);
		gcenter.add(lblVegTop3, 0, 9);
		gcenter.add(lblRequest, 0, 10);
		gcenter.add(lblBev, 0, 11);
		gcenter.add(lblDel, 0, 12);
		gcenter.add(lblDate, 0, 13);
		gcenter.add(lblTotal, 0, 14);
		
		
		Button btSubmit = new Button("Submit");
		Button btNew = new Button("Create New");
		Button btHome = new Button("Home");
		
		GridPane gbutton = new GridPane();
		
		gbutton.add(btHome, 0, 0);
		gbutton.add(btNew, 2, 0);
		gbutton.add(btSubmit, 4, 0);
		
		gbutton.setHgap(10);
		gbutton.setVgap(10);
		gbutton.setAlignment(Pos.CENTER);
		
		bpane.setBottom(gbutton);
		bpane.setCenter(gcenter);
		
		btNew.setOnAction(newSelected -> {
			
			total = 0;
			meatTopCount = 0;
			vegTopCount = 0;
			topCount = 0;
			bpane.getChildren().clear();
			bpane.setCenter(orderMenuCustom());
			
		});
		
		btSubmit.setOnAction(submitSelected -> {
						
			bpane.getChildren().clear();
			bpane.setCenter(receipt());

		});
		
		
		return bpane;
		
	}

	public static void main(String[] args)
	{
		launch(args);
	}

}

class Customer {
	
	String custName;
	String custPhone;
	String custAddress;
	String custPass;
	String custAddDesc;
	String custCardName;
	String custCardNum;
	String custCardExp;
	String custSecCod;
	String custAccount;
	String custRoute;
	String orderDateString;
	Date orderDate;
	Customer customerArray[] = new Customer[20];
	Receipt receiptArray[] = new Receipt[50];
	
	protected Customer()
	{
		
	}
	
	protected Customer(String name, String phone, String pass, String address, String desc, String cname,
					   String cnum, String cexp, String seccod, String acc, String rout)
	{
		
		custName = name;
		custPhone = phone;
		custPass = pass;
		custAddress = address;
		custAddDesc = desc;
		custCardName = cname;
		custCardNum = cnum;
		custCardExp = cexp;
		custSecCod = seccod;
		custAccount = acc;
		custRoute = rout;
		
	}
	
	protected Customer(Customer newCust)
	{
		custName = newCust.custName;
		custPhone = newCust.custPhone;
		custPass = newCust.custPass;
		custAddress = newCust.custAddress;
		custAddDesc = newCust.custAddDesc;
		custCardName = newCust.custCardName;
		custCardNum = newCust.custCardNum;
		custCardExp = newCust.custCardExp;
		custSecCod = newCust.custSecCod;
		custAccount = newCust.custAccount;
		custRoute = newCust.custRoute;
		
	}
	
	public boolean isEmpty()
	{
		return (custName.isEmpty() && custPhone.isEmpty() && custAddress.isEmpty());
	}
	
	public void demoCustomer() {
		
		customerArray[0] = new Customer("Justin Mauer", "6783332550", "0000", "123 Main St.",
									"Red House", "Justin D. Mauer", "123-456-7890", "01/25", "123", "", "");
		customerArray[1] = new Customer("Ryan Moser", "7705487777", "1111", "124 Main St.",
									"Blue House", "", "", "", "", "1234567890", "0987654321");
		customerArray[2] = new Customer("Aaron Newsome", "6786505378", "2222", "125 Main Blvd.",
									"Gate Code 123. Big green house on the left", "Aaron Newsome", "098-765-4321",
									"02/25", "321", "", "");
		customerArray[3] = new Customer("Tanaka Marape", "4049529282", "3333", "126 Main Blvd.",
									"Gate Code 234. House with the fence on the left",  "", "", "", "", "0987654321", "1234567890");
	}

	public Date setDate()
	{
		orderDate = new Date();
		orderDateString = orderDate.toString();
		
		return orderDate;
	}
	
}

class Receipt {

	String name;
	String phone;
	String address;
	
	String size;
	String crust;
	String cheese1;
	String cheese2;
	String meat1;
	String meat2;
	String meat3;
	String veg1;
	String veg2;
	String veg3;
	String request;
	String bev; 
	String bevSize;
	String delivery;
	String total;
	
	String orderDateString;
	Date orderDate;
	String orderDateDD;
	String orderDateMM;
	String orderDateYYYY;
	int count;
	
	Button dateButton;
	
	public Receipt(Customer c, String[] s)
	{
		name = c.custName;
		phone = c.custPhone;
		address = c.custAddress;
		
		size = s[0];
		crust = s[1];
		cheese1 = s[2];
		cheese2 = s[3];
		meat1 = s[4];
		meat2 = s[5];
		meat3 = s[6];
		veg1 = s[7];
		veg2 = s[8];
		veg3 = s[9];
		request = s[10];
		bev = s[11];
		bevSize = s[12];
		delivery = s[13];
		total = s[14];
		
		orderDateString = c.orderDateString;
		orderDate = c.orderDate;
		dateButton =  receiptButton();
		
		dateButton.setOnAction(buttonSelected -> {
			
			Stage stage = new Stage();
			Scene scene = new Scene(displayReceipt());
			
			stage.setScene(scene);
			stage.setTitle(name + " order placed on " + orderDateString);
			stage.show();
			
			
		});
		
	}
	
	// Displays receipt in a BorderPane
	public BorderPane displayReceipt()
	{
		
		BorderPane bpane = new BorderPane();
		GridPane gpane = new GridPane();
		
		Label lblSize = new Label("Pizza Size:\t\t\t" + size);
		Label lblCrust = new Label("Crust:\t\t\t\t" + crust);
		Label lblCheese1 = new Label("Cheese1:\t\t\t\t" + cheese1);
		Label lblCheese2 = new Label("Cheese2:\t\t\t\t" + cheese2);
		Label lblMeatTop1 = new Label("Meat Topping1:\t\t" + meat1);
		Label lblMeatTop2 = new Label("Meat Topping2:\t\t" + meat2);
		Label lblMeatTop3 = new Label("Meat Topping3:\t\t" + meat3);
		Label lblVegTop1 = new Label("Veggetable Topping1:\t" + veg1);
		Label lblVegTop2 = new Label("Veggetable Topping2:\t" + veg2);
		Label lblVegTop3 = new Label("Veggetable Topping3:\t" + veg3);
		Label lblRequest = new Label("Special Request:\t\t" + request);
		Label lblBev = new Label("Beverage:\t\t\t" + bev + " " + bevSize);
		Label lblDel = new Label("Delivery or Pickup:\t\t" + delivery);
		Label lblTotal = new Label("Order Total: \t\t\t$" + String.valueOf(total));
			
		Label lblDate = new Label("Order Date:\t\t\t" + orderDateString);
		
		
		gpane.add(lblSize, 0, 0);
		gpane.add(lblCrust, 0, 1);
		gpane.add(lblCheese1, 0, 2);
		gpane.add(lblCheese2, 0, 3);
		gpane.add(lblMeatTop1, 0, 4);
		gpane.add(lblMeatTop2, 0, 5);
		gpane.add(lblMeatTop3, 0, 6);
		gpane.add(lblVegTop1, 0, 7);
		gpane.add(lblVegTop2, 0, 8);
		gpane.add(lblVegTop3, 0, 9);
		gpane.add(lblRequest, 0, 10);
		gpane.add(lblBev, 0, 11);
		gpane.add(lblDel, 0, 12);
		gpane.add(lblDate, 0, 13);
		gpane.add(lblTotal, 0, 14);
		
		gpane.setHgap(10);
		gpane.setVgap(10);
		
		gpane.setAlignment(Pos.CENTER);
		
		bpane.setTop(customerInfoGrid());
		bpane.setBottom(gpane);
		
		
		return bpane;
	}
	
	// Displays customer info in a grid pane
	public GridPane customerInfoGrid()
	{
		GridPane gTop = new GridPane();
		
		gTop.setVgap(10);
		gTop.setAlignment(Pos.TOP_CENTER);
		
		
		Label lblName = new Label(name);
		Label lblPhone = new Label(phone);
		Label lblAddress = new Label(address);
		
		// Header Grid Pain - add labels
		gTop.add(lblName, 0, 0);
		gTop.add(lblPhone, 0, 1);
		gTop.add(lblAddress, 0, 2);
		gTop.add(new Label(), 0, 3);
		
		return gTop;
		
	}
	
	public Button receiptButton()
	{
		
		Calendar calendar = new GregorianCalendar();
		
		orderDateDD = Integer.toString(calendar.get(Calendar.DATE));
		orderDateMM = Integer.toString(calendar.get(Calendar.MONTH));
		orderDateYYYY = Integer.toString(calendar.get(Calendar.YEAR));
		
		return new Button(orderDateMM + "/" + orderDateDD + "/" + orderDateYYYY);
	}
	
	
	
}

class Manager {
	
	String managerName = "";
	String managerPass = "";
	Manager managerArray[] = new Manager[50];
	
	public Manager()
	{
		
	}
	
	public Manager(String name, String pass)
	{
		managerName = name;
		managerPass = pass;
		
	}
	
	public Manager(Manager newM)
	{
		managerName = newM.managerName;
		managerPass = newM.managerPass;
	}
	
	void demoManager()
	{
		managerArray[0] = new Manager("Justin Mauer", "0000");
		managerArray[1] = new Manager("Ryan Moser","1111");
		managerArray[2] = new Manager("Aaron Newsome","2222");
		managerArray[3] = new Manager("Tanaka Marape", "3333");		
	}
	
	boolean isEmpty()
	{
		return (managerName == "" && managerPass == "");
	}
	
}