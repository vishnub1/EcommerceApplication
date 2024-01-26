package org.example.ecommerce;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;


public class ECommerce extends Application {

    private final int width=440,height=400,headerLine=50;
    ProductList productList=new ProductList();
    Pane bodyPane;

    GridPane footerBar;

    Order order=new Order ();

    ObservableList<Product> cartItemList= FXCollections.observableArrayList();

    Button signInButton=new Button("Sign In");

    Button placeOrderButton=new Button("Place Order");
    Button signupButton=new Button("Sign Up");
    Label welcomeLabel=new Label("Welcome Customer");

    Customer loggedInCustomer=null;

    private void addItemsToCart(Product product){
        if(cartItemList.contains(product)){
            return;
        }
        cartItemList.add(product);
        System.out.println("Products in Cart "+cartItemList.stream().count());
    }
    private GridPane headerBar(){


        TextField searchBar=new TextField();
        searchBar.setPromptText("Search Products");
        Button searchButton=new Button("Search");
        Button cartButton=new Button("Cart");


        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productList.getAllProducts());
            }
        });


        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
            }
        });

        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productList.productsInCart(cartItemList));
            }
        });

        GridPane header=new GridPane();
        header.setHgap(10);

        header.add(searchBar,0,0);
        header.add(searchButton,1,0);
        header.add(signInButton,2,0);
        header.add(welcomeLabel,3,0);
        header.add(cartButton,4,0);
//        header.add(ordersButton,5,0);
        return header;
    }

    private GridPane signUpPage(){
        Label userLabel = new Label("User Name");
        TextField userName = new TextField();
        userName.setPromptText("Enter User Name");
        Label passLabel = new Label ("Password");
        Label confirmPassword = new Label ("Confirm Password");
        PasswordField password = new PasswordField();
        password.setPromptText("Enter Password");
        PasswordField confPassword = new PasswordField();
        confPassword.setPromptText("Confirm Password");
        Label userEmail = new Label("E-mail");
        TextField email = new TextField();
        email.setPromptText("Enter e-mail id");
        Label mobile = new Label("Phone");
        TextField mobileNum = new TextField();
        mobileNum.setPromptText("Enter Phone no.");
        Label userAddress = new Label ("Address");
        TextField address = new TextField();
        address.setPromptText("Enter your address");

        signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String user = userName.getText();
                String pass = password.getText();
                String confirmPassword= confPassword.getText();
                String eml = email.getText();
                String add = address.getText();
                String mono = mobileNum.getText();
                if(pass.equals(confirmPassword) && SignUp.validateEmail(eml)){
//                    Login.signUp(eml, user, add, pass, mono);
                    Login.signUp(user,eml,add,pass,mono);
                    showDialogue("Signup Successful!! \n Please Login");
                }
                else{
                    showDialogue("Signup Failed \n please try again");
                }
            }
        });


        GridPane signUpPane = new GridPane();
        signUpPane.setTranslateY(50); //moving the control
        signUpPane.setTranslateX(100);
//        signUpPane.setTranslateY(50);
        signUpPane.setVgap(10);
        signUpPane.setHgap(10);
        signUpPane.add(userLabel, 0, 0);
        signUpPane.add(userName, 1, 0);
        signUpPane.add(passLabel, 0, 1);
        signUpPane.add(password, 1, 1);
        signUpPane.add(confirmPassword , 0, 2);
        signUpPane.add(confPassword, 1, 2);
        signUpPane.add(userEmail , 0, 3);
        signUpPane.add(email, 1, 3);
        signUpPane.add(mobile , 0, 4);
        signUpPane.add(mobileNum, 1, 4);
        signUpPane.add(userAddress , 0, 5);
        signUpPane.add(address, 1, 5);
        signUpPane.add(signupButton,0, 6);

        return signUpPane;

    }
    private GridPane loginPage(){
        Label userLabel=new Label("User Name");
        Label passLabel=new Label("Password");
        TextField userName=new TextField();
        userName.setPromptText("Enter User Name");
        PasswordField password=new PasswordField();
        password.setPromptText("Enter Password");
        Button loginButton=new Button("Login");
        Label messageLabel=new Label("Log-In Message");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String user=userName.getText();
                String pass=password.getText();
                loggedInCustomer =Login.customerLogin(user,pass);
                if(loggedInCustomer!=null){
                    messageLabel.setText("LogIn Successful");
                    welcomeLabel.setText("Welcome "+loggedInCustomer.getName());
                }
                else{
                    messageLabel.setText("LogIn Failed");
                }
            }
        });

        GridPane loginPane=new GridPane();
        loginPane.setTranslateY(100); //moving the control
        loginPane.setTranslateX(100);
        loginPane.setVgap(10);
        loginPane.setHgap(10);
        loginPane.add(userLabel,0,0);
        loginPane.add(userName,1,0);
        loginPane.add(passLabel,0,1);
        loginPane.add(password,1,1);
        loginPane.add(loginButton,0,2);
        loginPane.add(messageLabel,1,2);

        return loginPane;
    }

    private void showDialogue(String message){

        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle("Order Status");

        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText(message);
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }
    private GridPane footerBar(){
        Button buyNowButton=new Button("  Buy Now  ");
        Button addToCartButton=new Button(" Add to Cart ");

        Button ordersButton=new Button(" Orders ");
        Button createAccount = new Button("Create Account");

        ordersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(order.getOrders());
            }
        });
        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product=productList.getSelectedProduct();
                boolean orderStatus=false;
                if(product!=null && loggedInCustomer!=null){
                    orderStatus=order.placeOrder(loggedInCustomer,product);
                }
                if(orderStatus){
                    showDialogue("Order Successful");
                }
                else{

                }
            }
        });

        addToCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product=productList.getSelectedProduct();
                addItemsToCart(product);
            }
        });

        createAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(signUpPage());
            }
        });

        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int orderCount=0;
                if(cartItemList!=null && loggedInCustomer!=null){
                    orderCount=order.placeOrderMultipleProducts(cartItemList,loggedInCustomer);
                }
                if(orderCount>0){
                    showDialogue("Order for "+ orderCount +" Order Placed Successfully");
                }
                else{

                }
            }
        });

        GridPane footer=new GridPane();
        footer.setHgap(10);
        footer.setTranslateY(headerLine+height);
        footer.add(buyNowButton,0,0);
        footer.add(addToCartButton ,1,0);
        footer.add(placeOrderButton,2,0);
        footer.add(ordersButton,3,0);
        footer.add(createAccount,4,0);
        return footer;
    }

    private Pane createContent(){
        Pane root=new Pane();
        BackgroundFill bg=new BackgroundFill(Color.BEIGE,CornerRadii.EMPTY, Insets.EMPTY);
        Background bgf=new Background(bg);
        root.setBackground(bgf);
        root.setPrefSize(width,height+2*headerLine);

        bodyPane=new Pane();
        bodyPane.setPrefSize(width,height);
        bodyPane.setTranslateY(headerLine);
        bodyPane.setTranslateX(10);
        bodyPane.getChildren().addAll(loginPage());
        footerBar=footerBar();

        root.getChildren().addAll(headerBar(),bodyPane,footerBar);
        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Scene scene = new Scene(createContent());
        stage.setTitle("ECommerce");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
