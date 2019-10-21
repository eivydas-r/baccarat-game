import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;


public class BaccaratGame extends Application {
	ArrayList<Card> playerHand;
	ArrayList<Card> bankerHand;
	BaccaratDealer theDealer;
	BaccaratGameLogic gameLogic;
	double currentBet;
	double totalWinnings;

	HashMap<String, Scene> sceneMap;
	String bettingOn;

	public BaccaratGame(){ // constructor
		playerHand = new ArrayList<Card>();
		bankerHand = new ArrayList<Card>();
		theDealer = new BaccaratDealer();
		gameLogic = new BaccaratGameLogic();
	}

	// This method will determine if the user won or lost their bet
	// and return the amount won or lost based on the value in currentBet.
	public double evaluateWinnings(){
		double winnings = 0;
		String winner = gameLogic.whoWon(bankerHand,playerHand); // banker goes first in parameters
		if(bettingOn == winner && winner == "Banker"){ // won the bet on Banker, w/ 5% commission
			winnings += currentBet;
		} else if(bettingOn == winner && winner == "Draw"){ // won the bet on Draw, bets 8:1
			winnings += currentBet*8;
		} else if(bettingOn == winner && winner == "Player") { // won the bet on Player
			winnings += currentBet;
		} else if(bettingOn != winner){ // lost the bet
			winnings -= currentBet;
		}
		return winnings;
	}

	// JavaFX //maven

	Scene scene;

	////////////

	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		//// TESTING MODE
		boolean testing = false;
		boolean gameOnly = false;
		////

		primaryStage.setTitle("Baccarat by Eivydas Raulynaitis");
		sceneMap = new HashMap<String, Scene>();

		BorderPane borderPane = new BorderPane();
		borderPane.setStyle("-fx-background-color: midnightblue");

		VBox mainVbox = new VBox();
		mainVbox.setAlignment(Pos.CENTER);


		Label welcome = new Label("♣ Welcome to ♦");
		welcome.setStyle("-fx-font: baghdad;" +
				"-fx-font-size: 27;" +
				"-fx-text-fill: mediumslateblue");
		welcome.setOpacity(0);

		Label baccarat = new Label("Baccarat");
		baccarat.setStyle("-fx-font: Phosphate;" +
				"-fx-font-size: 115;" +
				"-fx-text-fill: gold");
		baccarat.setOpacity(0);

		Label credits = new Label("a game by Eivydas Raulynaitis");
		credits.setStyle("-fx-font: baghdad;" +
				"-fx-font-size: 33;" +
				"-fx-text-fill: cornflowerblue");
		credits.setOpacity(0);

		mainVbox.setMargin(welcome, new Insets(0,3,-40,0));
		mainVbox.setMargin(baccarat, new Insets(0,2,0,0));
		mainVbox.setMargin(credits, new Insets(-20,0,0,0));

		Button sceneChangeButton = new Button("◀  Start  ▶");
		sceneChangeButton.setAlignment(Pos.CENTER);
		sceneChangeButton.setMinWidth(200);
		sceneChangeButton.setMaxWidth(200);
		sceneChangeButton.setMinHeight(60);
		sceneChangeButton.setMaxHeight(60);
		sceneChangeButton.setStyle("-fx-font: baghdad;" +
				"-fx-font-size: 30;" +
				"-fx-text-fill: black;" +
				"-fx-background-color: gold;");
		sceneChangeButton.setOpacity(0);

		mainVbox.setMargin(sceneChangeButton, new Insets(30,0,0,0));


		FadeTransition ft = new FadeTransition(Duration.millis(3000),welcome);
		ft.setFromValue(0.0);
		ft.setToValue(1);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);

		FadeTransition ft2 = new FadeTransition(Duration.millis(3000),baccarat);
		ft2.setFromValue(0.0);
		ft2.setToValue(1);
		ft2.setCycleCount(1);
		ft2.setAutoReverse(false);

		FadeTransition ft3 = new FadeTransition(Duration.millis(3000),credits);
		ft3.setFromValue(0.0);
		ft3.setToValue(1);
		ft3.setCycleCount(1);
		ft3.setAutoReverse(false);

		FadeTransition ft4 = new FadeTransition(Duration.millis(3000),sceneChangeButton);
		ft4.setFromValue(0.0);
		ft4.setToValue(1);
		ft4.setCycleCount(1);
		ft4.setAutoReverse(false);

		PauseTransition pause1 = new PauseTransition(Duration.seconds(3));
		pause1.setOnFinished(e->ft.play());
		pause1.play();

		PauseTransition pause2 = new PauseTransition(Duration.seconds(5));
		pause2.setOnFinished(e->ft2.play());
		pause2.play();

		PauseTransition pause3 = new PauseTransition(Duration.seconds(7));
		pause3.setOnFinished(e->ft3.play());
		pause3.play();

		PauseTransition pause4 = new PauseTransition(Duration.seconds(10));
		pause4.setOnFinished(e->ft4.play());
		pause4.play();

		//ft.play();

		//sceneChangeButton.setOnAction(e->primaryStage.setScene(sceneMap.get("gameScene")));
		/*FadeTransition ft5 = new FadeTransition(Duration.millis(3000),mainVbox);
		ft5.setFromValue(1);
		ft5.setToValue(0);
		ft5.setCycleCount(1);
		ft5.setAutoReverse(false);*/

		PauseTransition pause = new PauseTransition(Duration.seconds(3));
		pause.setOnFinished(e->primaryStage.setScene(sceneMap.get("gameScene"))); // set to main scene

		FadeTransition ft6 = new FadeTransition(Duration.millis(3000),borderPane);
		ft6.setFromValue(1);
		ft6.setToValue(0);
		ft6.setCycleCount(1);
		ft6.setAutoReverse(false);

		sceneChangeButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent action) {
				ft6.play();
				pause.play();
			}
		});

		mainVbox.getChildren().addAll(welcome,baccarat,credits,sceneChangeButton);
		borderPane.setCenter(mainVbox);


		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		sceneMap.put("gameScene", gameScene(primaryStage));

		if(testing){
			primaryStage.setScene(sceneMap.get("gameScene"));
			primaryStage.show();
			primaryStage.close();
		}
		if(gameOnly){
			primaryStage.setScene(sceneMap.get("gameScene"));
			primaryStage.show();
		} else{
			scene = new Scene(borderPane,width,height);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}


	public Scene gameScene(Stage primaryStage){
		primaryStage.setTitle("Baccarat by Eivydas Raulynaitis");
		BorderPane borderPane = new BorderPane();

		//Button button1 = new Button("Click me!!");

		//button1.setOnAction(e->button1.setText("Yes"));



		//// BorderPane: Bottom
		HBox hboxBottom = new HBox();
		borderPane.setBottom(hboxBottom);

		ListView listView = new ListView();

		listView.getItems().add("Welcome to Baccarat!");
		listView.getItems().add("Game developed by Eivydas Raulynaitis.");
		listView.getItems().add("[1] Enter your bid in the box on the right.");
		listView.getItems().add("[2] You can bid on either the Player, Banker, or Draw.");
		listView.getItems().add("[3] If you bid on Draw your winnings are 8:1!");
		listView.getItems().add("Make a bid to play!");

		listView.setMinHeight(200);
		listView.setMaxHeight(200);
		listView.setMinWidth(500);
		listView.setMaxWidth(500);
		listView.setStyle("-fx-control-inner-background: darkgreen;");
		hboxBottom.setMargin(listView, new Insets(0,0,40,30));


		hboxBottom.getChildren().addAll(listView);
		hboxBottom.setAlignment(Pos.CENTER);


		//// BorderPane: Top
		// Menu:
		MenuBar menuBar = new MenuBar();
		borderPane.setTop(menuBar);
		Menu menuBtn1 = new Menu("Options");

		MenuItem menuItem1 = new MenuItem("Fresh Start");
		MenuItem menuItem2 = new MenuItem("Exit");
		menuItem2.setOnAction(e->primaryStage.close()); // close window if Exit

		menuBar.getMenus().addAll(menuBtn1);
		menuBtn1.getItems().addAll(menuItem1,menuItem2);

		//// BorderPane: Center
		VBox centerBox = new VBox(15);
		borderPane.setCenter(centerBox);
		centerBox.setAlignment(Pos.CENTER);

		Label labelBanker = new Label("♥            Banker            ♦");
		labelBanker.setStyle("-fx-font: baghdad;" +
				"-fx-font-size: 30;" +
				"-fx-text-fill: black");

		StackPane stackPaneBanker = new StackPane();
		Rectangle rectangleBid4 = new Rectangle();
		rectangleBid4.setFill(Color.DARKGREEN);
		rectangleBid4.setHeight(60);
		rectangleBid4.setWidth(400);
		stackPaneBanker.getChildren().addAll(rectangleBid4,labelBanker);

		//labelBanker.setStyle("-fx-font-color: white"); // make text and edit text values
		//labelBanker.setFont(Font.font("Verdana", FontWeight.BOLD, 70));


		Label labelPlayer = new Label("♠                         Player                         ♣");
		labelPlayer.setStyle("-fx-font: baghdad;" +
				"-fx-font-size: 30;" +
				"-fx-text-fill: black;");

		StackPane stackPanePlayer = new StackPane();
		Rectangle rectangleBid5 = new Rectangle();
		rectangleBid5.setFill(Color.DARKGREEN);
		rectangleBid5.setHeight(60);
		rectangleBid5.setWidth(600);
		stackPanePlayer.getChildren().addAll(rectangleBid5,labelPlayer);



		//banker HBox
		HBox bankerCards = new HBox(5);
		bankerCards.setAlignment(Pos.CENTER);

/// was here

		//player HBox
		HBox playerCards = new HBox(5);
		playerCards.setAlignment(Pos.CENTER);

		centerBox.getChildren().addAll(stackPaneBanker,bankerCards,playerCards,stackPanePlayer);
		//borderPane.setMargin(playerCards, new Insets(150,12,12,500));

		//// BorderPane: Left
		VBox vboxLeft = new VBox(5);
		borderPane.setLeft(vboxLeft);
		Image deckCard = new Image("Red_back.jpg");
		ImageView imageViewDeckCard = new ImageView(deckCard);
		imageViewDeckCard.setPreserveRatio(true);
		imageViewDeckCard.setFitHeight(150);
		imageViewDeckCard.setRotate(imageViewDeckCard.getRotate() + 90);
		//imageViewDeckCard.setX(100);
		//imageViewDeckCard.setY(500);

		Button shuffleButton = new Button("Shuffle");
		shuffleButton.setMinWidth(150);
		shuffleButton.setMaxWidth(150);
		shuffleButton.setMinHeight(60);
		shuffleButton.setMaxHeight(60);
		shuffleButton.setStyle("-fx-font: baghdad;" +
				"-fx-font-size: 30;" +
				"-fx-text-fill: black;" +
				"-fx-background-color: palegreen;");

		shuffleButton.setOnAction(new EventHandler<ActionEvent>() { // do a fresh start
			public void handle(ActionEvent event) {
				theDealer.shuffleDeck();
				shuffleButton.setDisable(true);

				FadeTransition ft = new FadeTransition(Duration.seconds(0.5),imageViewDeckCard);
				ft.setFromValue(1);
				ft.setToValue(0.1);
				ft.setCycleCount(4);
				ft.setAutoReverse(true);
				ft.play();

				PauseTransition pause2 = new PauseTransition(Duration.seconds(3));
				pause2.setOnFinished(e-> {
					listView.getItems().add("The Dealer has shuffled the deck!");
					listView.scrollTo(listView.getItems().size());
					shuffleButton.setDisable(false);
					/*ft.setFromValue(1);
					ft.setToValue(0.5);
					ft.setCycleCount(2);
					ft.setAutoReverse(false);
					ft.play();*/
				});
				pause2.play();
			}
		});

		StackPane stackPane2 = new StackPane();
		Label winningsLabel = new Label("Winnings");
		winningsLabel.setStyle("-fx-font: baghdad;" +
				"-fx-font-size: 30;" +
				"-fx-text-fill: white");

		Rectangle rectangleBid2 = new Rectangle();
		rectangleBid2.setFill(Color.DARKGREEN);
		rectangleBid2.setHeight(60);
		rectangleBid2.setWidth(150);
		stackPane2.getChildren().addAll(rectangleBid2,winningsLabel);

		StackPane stackPane3 = new StackPane();
		Label winningsAmount = new Label("$0.0");
		winningsAmount.setStyle("-fx-font: baghdad;" +
				"-fx-font-size: 30;" +
				"-fx-text-fill: black");

		menuItem1.setOnAction(new EventHandler<ActionEvent>() { // do a fresh start
			public void handle(ActionEvent event) {
				totalWinnings = 0;
				winningsAmount.setText("$0.0");
			}
		});

		Rectangle rectangleBid3 = new Rectangle();
		rectangleBid3.setFill(Color.WHITE);
		rectangleBid3.setHeight(60);
		rectangleBid3.setWidth(150);
		stackPane3.getChildren().addAll(rectangleBid3,winningsAmount);



		vboxLeft.getChildren().addAll(imageViewDeckCard, shuffleButton, stackPane2,stackPane3);
		vboxLeft.setAlignment(Pos.CENTER);
		borderPane.setMargin(vboxLeft, new Insets(0,0,0,70));
		vboxLeft.setMargin(stackPane3, new Insets(-5,0,0,0));
		vboxLeft.setMargin(shuffleButton, new Insets(0,0,20,0));
		vboxLeft.setMargin(imageViewDeckCard, new Insets(0,0,-25,0));


		//// BorderPane: Right
		VBox vboxRight = new VBox(5);
		borderPane.setRight(vboxRight);
		StackPane stackPane1 = new StackPane();

		Label bidLabel = new Label("Bid");
		bidLabel.setStyle("-fx-font: baghdad;" +
						  "-fx-font-size: 36;" +
						  "-fx-text-fill: white");

		Rectangle rectangleBid = new Rectangle();
		rectangleBid.setFill(Color.DARKGREEN);
		rectangleBid.setHeight(60);
		rectangleBid.setWidth(150);
		stackPane1.getChildren().addAll(rectangleBid,bidLabel);

		Button bankerBidButton = new Button("Banker");
		bankerBidButton.setMinWidth(150);
		bankerBidButton.setMaxWidth(150);
		bankerBidButton.setMinHeight(60);
		bankerBidButton.setMaxHeight(60);
		bankerBidButton.setStyle("-fx-font: baghdad;" +
								 "-fx-font-size: 30;" +
								 "-fx-text-fill: black;" +
								 "-fx-background-color: gold;");

		Button drawBidButton = new Button("Draw");
		drawBidButton.setMinWidth(150);
		drawBidButton.setMaxWidth(150);
		drawBidButton.setMinHeight(60);
		drawBidButton.setMaxHeight(60);
		drawBidButton.setStyle("-fx-font: baghdad;" +
								"-fx-font-size: 30;" +
								"-fx-text-fill: black;" +
								"-fx-background-color: forestgreen;");

		Button playerBidButton = new Button("Player");
		playerBidButton.setMinWidth(150);
		playerBidButton.setMaxWidth(150);
		playerBidButton.setMinHeight(60);
		playerBidButton.setMaxHeight(60); // yellow green red
		playerBidButton.setStyle("-fx-font: baghdad;" +
								"-fx-font-size: 30;" +
								"-fx-text-fill: black;" +
								"-fx-background-color: crimson;");

		TextField bidTextField = new TextField("$0");
		bidTextField.setMinWidth(150);
		bidTextField.setMaxWidth(150);
		bidTextField.setMinHeight(60);
		bidTextField.setMaxHeight(60);
		bidTextField.setStyle("-fx-font: baghdad;" +
				"-fx-font-size: 30;" +
				"-fx-text-fill: black");
		bidTextField.setAlignment(Pos.CENTER);


		vboxRight.getChildren().addAll(stackPane1,bankerBidButton,drawBidButton,playerBidButton,bidTextField);
		vboxRight.setAlignment(Pos.CENTER);
		borderPane.setMargin(vboxRight, new Insets(0,50,0,0));

		theDealer.generateDeck();
		theDealer.shuffleDeck();


		centerBox.setMargin(bankerCards, new Insets(0,0,130,0));
		centerBox.setMargin(playerCards, new Insets(130,0,0,0));

		PauseTransition pauseBankerCard1 = new PauseTransition(Duration.seconds(2));
		pauseBankerCard1.setOnFinished(e-> {
			Image bankerCard1 = new Image(bankerHand.get(0).value+bankerHand.get(0).suite+".jpg");
			ImageView imageViewBanker1 = new ImageView(bankerCard1);
			imageViewBanker1.setPreserveRatio(true);
			imageViewBanker1.setFitHeight(130);

			centerBox.setMargin(bankerCards, new Insets(0,0,0,0));
			bankerCards.getChildren().addAll(imageViewBanker1);

			imageViewBanker1.setOpacity(0);
			FadeTransition ft = new FadeTransition(Duration.seconds(2),imageViewBanker1);
			ft.setFromValue(0);
			ft.setToValue(1);
			ft.setCycleCount(1);
			ft.setAutoReverse(false);
			ft.play();
		});

		PauseTransition pauseBankerCard2 = new PauseTransition(Duration.seconds(4));
		pauseBankerCard2.setOnFinished(e-> {
			Image bankerCard1 = new Image(bankerHand.get(1).value+bankerHand.get(1).suite+".jpg");
			ImageView imageViewBanker1 = new ImageView(bankerCard1);
			imageViewBanker1.setPreserveRatio(true);
			imageViewBanker1.setFitHeight(130);

			centerBox.setMargin(bankerCards, new Insets(0,0,0,0));
			bankerCards.getChildren().addAll(imageViewBanker1);

			imageViewBanker1.setOpacity(0);
			FadeTransition ft = new FadeTransition(Duration.seconds(2),imageViewBanker1);
			ft.setFromValue(0);
			ft.setToValue(1);
			ft.setCycleCount(1);
			ft.setAutoReverse(false);
			ft.play();
		});

		PauseTransition pauseBankerCard3 = new PauseTransition(Duration.seconds(6));
		pauseBankerCard3.setOnFinished(e-> {
			Image bankerCard1 = new Image(bankerHand.get(2).value+bankerHand.get(2).suite+".jpg");
			ImageView imageViewBanker1 = new ImageView(bankerCard1);
			imageViewBanker1.setPreserveRatio(true);
			imageViewBanker1.setFitHeight(130);

			centerBox.setMargin(bankerCards, new Insets(0,0,0,0));
			bankerCards.getChildren().addAll(imageViewBanker1);

			imageViewBanker1.setOpacity(0);
			FadeTransition ft = new FadeTransition(Duration.seconds(2),imageViewBanker1);
			ft.setFromValue(0);
			ft.setToValue(1);
			ft.setCycleCount(1);
			ft.setAutoReverse(false);
			ft.play();
		});

		PauseTransition pausePlayerCard1 = new PauseTransition(Duration.seconds(1));
		pausePlayerCard1.setOnFinished(e-> {
			Image playerCard1 = new Image(playerHand.get(0).value+playerHand.get(0).suite+".jpg");
			ImageView imageViewPlayer1 = new ImageView(playerCard1);
			imageViewPlayer1.setPreserveRatio(true);
			imageViewPlayer1.setFitHeight(130);

			centerBox.setMargin(playerCards, new Insets(0,0,0,0));
			playerCards.getChildren().addAll(imageViewPlayer1);

			imageViewPlayer1.setOpacity(0);
			FadeTransition ft = new FadeTransition(Duration.seconds(2),imageViewPlayer1);
			ft.setFromValue(0);
			ft.setToValue(1);
			ft.setCycleCount(1);
			ft.setAutoReverse(false);
			ft.play();
		});

		PauseTransition pausePlayerCard2 = new PauseTransition(Duration.seconds(3));
		pausePlayerCard2.setOnFinished(e-> {
			Image playerCard1 = new Image(playerHand.get(1).value+playerHand.get(1).suite+".jpg");
			ImageView imageViewPlayer1 = new ImageView(playerCard1);
			imageViewPlayer1.setPreserveRatio(true);
			imageViewPlayer1.setFitHeight(130);

			centerBox.setMargin(playerCards, new Insets(0,0,0,0));
			playerCards.getChildren().addAll(imageViewPlayer1);

			imageViewPlayer1.setOpacity(0);
			FadeTransition ft = new FadeTransition(Duration.seconds(2),imageViewPlayer1);
			ft.setFromValue(0);
			ft.setToValue(1);
			ft.setCycleCount(1);
			ft.setAutoReverse(false);
			ft.play();
		});

		PauseTransition pausePlayerCard3 = new PauseTransition(Duration.seconds(5));
		pausePlayerCard3.setOnFinished(e-> {
			Image playerCard1 = new Image(playerHand.get(2).value+playerHand.get(2).suite+".jpg");
			ImageView imageViewPlayer1 = new ImageView(playerCard1);
			imageViewPlayer1.setPreserveRatio(true);
			imageViewPlayer1.setFitHeight(130);

			centerBox.setMargin(playerCards, new Insets(0,0,0,0));
			playerCards.getChildren().addAll(imageViewPlayer1);

			imageViewPlayer1.setOpacity(0);
			FadeTransition ft = new FadeTransition(Duration.seconds(2),imageViewPlayer1);
			ft.setFromValue(0);
			ft.setToValue(1);
			ft.setCycleCount(1);
			ft.setAutoReverse(false);
			ft.play();
		});

		bankerBidButton.setOnAction(new EventHandler<ActionEvent>() { // bid on Banker
			public void handle(ActionEvent event){
				shuffleButton.setDisable(true);
				centerBox.setMargin(bankerCards, new Insets(0,0,130,0));
				centerBox.setMargin(playerCards, new Insets(130,0,0,0));

				if(playerCards.getChildren().size() == 3) { // clear cards at start of each game
					playerCards.getChildren().remove(0);
					playerCards.getChildren().remove(0);
					playerCards.getChildren().remove(0);
				} else if(playerCards.getChildren().size() == 2) {
					playerCards.getChildren().remove(0);
					playerCards.getChildren().remove(0);
				}

				if(bankerCards.getChildren().size() == 3) {
					bankerCards.getChildren().remove(0);
					bankerCards.getChildren().remove(0);
					bankerCards.getChildren().remove(0);
				} else if(bankerCards.getChildren().size() == 2) {
					bankerCards.getChildren().remove(0);
					bankerCards.getChildren().remove(0);
				}

				bankerCards.setOpacity(1);
				playerCards.setOpacity(1);

				bettingOn = "Banker";

				String inputtedValue = bidTextField.getText();
				double actualValue = -99;

				if(inputtedValue.charAt(0) == '$'){ // check if $ was included
					StringBuilder sb = new StringBuilder(inputtedValue);
					sb.deleteCharAt(0);
					String actualString = sb.toString();
					actualValue = Integer.parseInt(actualString);
				} else{
					boolean isNumeric = inputtedValue.chars().allMatch(Character::isDigit); // check that input is a number
					if(isNumeric){
						actualValue = Integer.parseInt(inputtedValue);
					} else {
						listView.getItems().add("Error, you can only input a positive whole number!");
						return;
					}
				}

				if(actualValue >= 0){ // check that number isn't negative
					listView.getItems().add("");
					listView.getItems().add("Current bet is $" + actualValue + " on " + bettingOn+ ".");
					listView.scrollTo(listView.getItems().size());
					currentBet = actualValue;
					bankerBidButton.setDisable(true);
					drawBidButton.setDisable(true);
					playerBidButton.setDisable(true);
					bidTextField.setDisable(true);

					playerHand = theDealer.dealHand();
					bankerHand = theDealer.dealHand();
					pauseBankerCard1.play();
					pausePlayerCard1.play();
					pauseBankerCard2.play();
					pausePlayerCard2.play();


					boolean playerDrew = false;
					if(gameLogic.evaluatePlayerDraw(playerHand) == true) { // should player draw 3rd card?
						playerHand.add(theDealer.drawOne());
						playerDrew = true;
						pausePlayerCard3.play();
					}
					if(playerDrew == true){ // if player drew, then see if banker draws too
						if(gameLogic.evaluateBankerDraw(bankerHand,playerHand.get(2))){
							bankerHand.add(theDealer.drawOne());
							pauseBankerCard3.play();
						}

					} else if(playerDrew == false){ // if player didn't draw
						if(gameLogic.evaluateBankerDraw(bankerHand,null)){
							bankerHand.add(theDealer.drawOne());
							pauseBankerCard3.play();
						}
					}

					String winner = gameLogic.whoWon(bankerHand,playerHand);

					PauseTransition pause1 = new PauseTransition(Duration.seconds(8));
					pause1.setOnFinished(e-> {
						listView.getItems().add("");
						listView.getItems().add("Player Total: " + gameLogic.handTotal(playerHand) + "  Banker Total: " + gameLogic.handTotal(bankerHand));
						listView.scrollTo(listView.getItems().size());
					});
					pause1.play();

					PauseTransition pause2 = new PauseTransition(Duration.seconds(10));
					pause2.setOnFinished(e-> {
						listView.getItems().add(winner + " wins!");
						listView.scrollTo(listView.getItems().size());
					});
					pause2.play();

					PauseTransition pause3 = new PauseTransition(Duration.seconds(12));
					pause3.setOnFinished(e-> {
						if(bettingOn == winner){
							listView.getItems().add("Congrats, you bet " + winner + "! You won your bet!");
						} else{
							listView.getItems().add("Sorry, you bet " + bettingOn + "! You lost your bet!");
						}
						listView.scrollTo(listView.getItems().size());
						totalWinnings += evaluateWinnings();
						winningsAmount.setText("$" + Double.toString(totalWinnings));
					});
					pause3.play();

					PauseTransition pause4 = new PauseTransition(Duration.seconds(15));
					pause4.setOnFinished(e-> {
						listView.getItems().add("");
						listView.getItems().add("Want to play again? Make another bid!");
						listView.scrollTo(listView.getItems().size());

						FadeTransition ft = new FadeTransition(Duration.seconds(2),playerCards);
						ft.setFromValue(1);
						ft.setToValue(0.5);
						ft.setCycleCount(1);
						ft.setAutoReverse(false);
						ft.play();
						FadeTransition ft2 = new FadeTransition(Duration.seconds(2),bankerCards);
						ft2.setFromValue(1);
						ft2.setToValue(0.5);
						ft2.setCycleCount(1);
						ft2.setAutoReverse(false);
						ft2.play();

						PauseTransition pause5 = new PauseTransition(Duration.seconds(3));
						pause5.setOnFinished(w-> {
							bankerBidButton.setDisable(false);
							drawBidButton.setDisable(false);
							playerBidButton.setDisable(false);
							bidTextField.setDisable(false);
							shuffleButton.setDisable(false);
						});
						pause5.play();
					});
					pause4.play();

				} else{
					listView.getItems().add("Error, you can only input a positive whole number!");
				}

			}
		});

		playerBidButton.setOnAction(new EventHandler<ActionEvent>() { // bid on Player
			public void handle(ActionEvent event){
				shuffleButton.setDisable(true);
				centerBox.setMargin(bankerCards, new Insets(0,0,130,0));
				centerBox.setMargin(playerCards, new Insets(130,0,0,0));

				if(playerCards.getChildren().size() == 3) { // clear cards at start of each game
					playerCards.getChildren().remove(0);
					playerCards.getChildren().remove(0);
					playerCards.getChildren().remove(0);
				} else if(playerCards.getChildren().size() == 2) {
					playerCards.getChildren().remove(0);
					playerCards.getChildren().remove(0);
				}

				if(bankerCards.getChildren().size() == 3) {
					bankerCards.getChildren().remove(0);
					bankerCards.getChildren().remove(0);
					bankerCards.getChildren().remove(0);
				} else if(bankerCards.getChildren().size() == 2) {
					bankerCards.getChildren().remove(0);
					bankerCards.getChildren().remove(0);
				}

				bankerCards.setOpacity(1);
				playerCards.setOpacity(1);

				bettingOn = "Player";

				String inputtedValue = bidTextField.getText();
				double actualValue = -99;

				if(inputtedValue.charAt(0) == '$'){ // check if $ was included
					StringBuilder sb = new StringBuilder(inputtedValue);
					sb.deleteCharAt(0);
					String actualString = sb.toString();
					actualValue = Integer.parseInt(actualString);
				} else{
					boolean isNumeric = inputtedValue.chars().allMatch(Character::isDigit); // check that input is a number
					if(isNumeric){
						actualValue = Integer.parseInt(inputtedValue);
					} else {
						listView.getItems().add("Error, you can only input a positive whole number!");
						return;
					}
				}

				if(actualValue >= 0){ // check that number isn't negative
					listView.getItems().add("");
					listView.getItems().add("Current bet is $" + actualValue + " on " + bettingOn+ ".");
					listView.scrollTo(listView.getItems().size());
					currentBet = actualValue;
					bankerBidButton.setDisable(true);
					drawBidButton.setDisable(true);
					playerBidButton.setDisable(true);
					bidTextField.setDisable(true);

					playerHand = theDealer.dealHand();
					bankerHand = theDealer.dealHand();
					pauseBankerCard1.play();
					pausePlayerCard1.play();
					pauseBankerCard2.play();
					pausePlayerCard2.play();


					boolean playerDrew = false;
					if(gameLogic.evaluatePlayerDraw(playerHand) == true) { // should player draw 3rd card?
						playerHand.add(theDealer.drawOne());
						playerDrew = true;
						pausePlayerCard3.play();
					}
					if(playerDrew == true){ // if player drew, then see if banker draws too
						if(gameLogic.evaluateBankerDraw(bankerHand,playerHand.get(2))){
							bankerHand.add(theDealer.drawOne());
							pauseBankerCard3.play();
						}

					} else if(playerDrew == false){ // if player didn't draw
						if(gameLogic.evaluateBankerDraw(bankerHand,null)){
							bankerHand.add(theDealer.drawOne());
							pauseBankerCard3.play();
						}
					}

					String winner = gameLogic.whoWon(bankerHand,playerHand);

					PauseTransition pause1 = new PauseTransition(Duration.seconds(8));
					pause1.setOnFinished(e-> {
						listView.getItems().add("");
						listView.getItems().add("Player Total: " + gameLogic.handTotal(playerHand) + "  Banker Total: " + gameLogic.handTotal(bankerHand));
						listView.scrollTo(listView.getItems().size());
					});
					pause1.play();

					PauseTransition pause2 = new PauseTransition(Duration.seconds(10));
					pause2.setOnFinished(e-> {
						listView.getItems().add(winner + " wins!");
						listView.scrollTo(listView.getItems().size());
					});
					pause2.play();

					PauseTransition pause3 = new PauseTransition(Duration.seconds(12));
					pause3.setOnFinished(e-> {
						if(bettingOn == winner){
							listView.getItems().add("Congrats, you bet " + winner + "! You won your bet!");
						} else{
							listView.getItems().add("Sorry, you bet " + bettingOn + "! You lost your bet!");
						}
						listView.scrollTo(listView.getItems().size());
						totalWinnings += evaluateWinnings();
						winningsAmount.setText("$" + Double.toString(totalWinnings));
					});
					pause3.play();

					PauseTransition pause4 = new PauseTransition(Duration.seconds(15));
					pause4.setOnFinished(e-> {
						listView.getItems().add("");
						listView.getItems().add("Want to play again? Make another bid!");
						listView.scrollTo(listView.getItems().size());

						FadeTransition ft = new FadeTransition(Duration.seconds(2),playerCards);
						ft.setFromValue(1);
						ft.setToValue(0.5);
						ft.setCycleCount(1);
						ft.setAutoReverse(false);
						ft.play();
						FadeTransition ft2 = new FadeTransition(Duration.seconds(2),bankerCards);
						ft2.setFromValue(1);
						ft2.setToValue(0.5);
						ft2.setCycleCount(1);
						ft2.setAutoReverse(false);
						ft2.play();

						PauseTransition pause5 = new PauseTransition(Duration.seconds(3));
						pause5.setOnFinished(w-> {
							bankerBidButton.setDisable(false);
							drawBidButton.setDisable(false);
							playerBidButton.setDisable(false);
							bidTextField.setDisable(false);
							shuffleButton.setDisable(false);
						});
						pause5.play();
					});
					pause4.play();

				} else{
					listView.getItems().add("Error, you can only input a positive whole number!");
				}

			}
		});

		drawBidButton.setOnAction(new EventHandler<ActionEvent>() { // bid on Draw
			public void handle(ActionEvent event){
				shuffleButton.setDisable(true);
				centerBox.setMargin(bankerCards, new Insets(0,0,130,0));
				centerBox.setMargin(playerCards, new Insets(130,0,0,0));

				if(playerCards.getChildren().size() == 3) { // clear cards at start of each game
					playerCards.getChildren().remove(0);
					playerCards.getChildren().remove(0);
					playerCards.getChildren().remove(0);
				} else if(playerCards.getChildren().size() == 2) {
					playerCards.getChildren().remove(0);
					playerCards.getChildren().remove(0);
				}

				if(bankerCards.getChildren().size() == 3) {
					bankerCards.getChildren().remove(0);
					bankerCards.getChildren().remove(0);
					bankerCards.getChildren().remove(0);
				} else if(bankerCards.getChildren().size() == 2) {
					bankerCards.getChildren().remove(0);
					bankerCards.getChildren().remove(0);
				}

				bankerCards.setOpacity(1);
				playerCards.setOpacity(1);

				bettingOn = "Draw";

				String inputtedValue = bidTextField.getText();
				double actualValue = -99;

				if(inputtedValue.charAt(0) == '$'){ // check if $ was included
					StringBuilder sb = new StringBuilder(inputtedValue);
					sb.deleteCharAt(0);
					String actualString = sb.toString();
					actualValue = Integer.parseInt(actualString);
				} else{
					boolean isNumeric = inputtedValue.chars().allMatch(Character::isDigit); // check that input is a number
					if(isNumeric){
						actualValue = Integer.parseInt(inputtedValue);
					} else {
						listView.getItems().add("Error, you can only input a positive whole number!");
						return;
					}
				}

				if(actualValue >= 0){ // check that number isn't negative
					listView.getItems().add("");
					listView.getItems().add("Current bet is $" + actualValue + " on " + bettingOn+ ".");
					listView.scrollTo(listView.getItems().size());
					currentBet = actualValue;
					bankerBidButton.setDisable(true);
					drawBidButton.setDisable(true);
					playerBidButton.setDisable(true);
					bidTextField.setDisable(true);

					playerHand = theDealer.dealHand();
					bankerHand = theDealer.dealHand();
					pauseBankerCard1.play();
					pausePlayerCard1.play();
					pauseBankerCard2.play();
					pausePlayerCard2.play();


					boolean playerDrew = false;
					if(gameLogic.evaluatePlayerDraw(playerHand) == true) { // should player draw 3rd card?
						playerHand.add(theDealer.drawOne());
						playerDrew = true;
						pausePlayerCard3.play();
					}
					if(playerDrew == true){ // if player drew, then see if banker draws too
						if(gameLogic.evaluateBankerDraw(bankerHand,playerHand.get(2))){
							bankerHand.add(theDealer.drawOne());
							pauseBankerCard3.play();
						}

					} else if(playerDrew == false){ // if player didn't draw
						if(gameLogic.evaluateBankerDraw(bankerHand,null)){
							bankerHand.add(theDealer.drawOne());
							pauseBankerCard3.play();
						}
					}

					String winner = gameLogic.whoWon(bankerHand,playerHand);

					PauseTransition pause1 = new PauseTransition(Duration.seconds(8));
					pause1.setOnFinished(e-> {
						listView.getItems().add("");
						listView.getItems().add("Player Total: " + gameLogic.handTotal(playerHand) + "  Banker Total: " + gameLogic.handTotal(bankerHand));
						listView.scrollTo(listView.getItems().size());
					});
					pause1.play();

					PauseTransition pause2 = new PauseTransition(Duration.seconds(10));
					pause2.setOnFinished(e-> {
						listView.getItems().add(winner + " wins!");
						listView.scrollTo(listView.getItems().size());
					});
					pause2.play();

					PauseTransition pause3 = new PauseTransition(Duration.seconds(12));
					pause3.setOnFinished(e-> {
						if(bettingOn == winner){
							listView.getItems().add("Congrats, you bet " + winner + "! You won your bet!");
						} else{
							listView.getItems().add("Sorry, you bet " + bettingOn + "! You lost your bet!");
						}
						listView.scrollTo(listView.getItems().size());
						totalWinnings += evaluateWinnings();
						winningsAmount.setText("$" + Double.toString(totalWinnings));
					});
					pause3.play();

					PauseTransition pause4 = new PauseTransition(Duration.seconds(15));
					pause4.setOnFinished(e-> {
						listView.getItems().add("");
						listView.getItems().add("Want to play again? Make another bid!");
						listView.scrollTo(listView.getItems().size());

						FadeTransition ft = new FadeTransition(Duration.seconds(2),playerCards);
						ft.setFromValue(1);
						ft.setToValue(0.5);
						ft.setCycleCount(1);
						ft.setAutoReverse(false);
						ft.play();
						FadeTransition ft2 = new FadeTransition(Duration.seconds(2),bankerCards);
						ft2.setFromValue(1);
						ft2.setToValue(0.5);
						ft2.setCycleCount(1);
						ft2.setAutoReverse(false);
						ft2.play();

						PauseTransition pause5 = new PauseTransition(Duration.seconds(3));
						pause5.setOnFinished(w-> {
							bankerBidButton.setDisable(false);
							drawBidButton.setDisable(false);
							playerBidButton.setDisable(false);
							bidTextField.setDisable(false);
							shuffleButton.setDisable(false);
						});
						pause5.play();
					});
					pause4.play();

				} else{
					listView.getItems().add("Error, you can only input a positive whole number!");
				}

			}
		});


		borderPane.setStyle("-fx-background-color: green;");

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();


		//scene = new Scene(borderPane,width,height);


		//primaryStage.setScene(scene);
		//primaryStage.show();

		//pause.setOnFinished(e->primaryStage.setScene(sceneMap.get("gameScene")));

		borderPane.setOpacity(1);

		/*FadeTransition ft6 = new FadeTransition(Duration.millis(6000),borderPane);
		ft6.setFromValue(0);
		ft6.setToValue(1);
		ft6.setCycleCount(1);
		ft6.setAutoReverse(true);
		ft6.play();*/

		return new Scene(borderPane,width,800);
	}
}

/*
Notes:
						   (wire frame)
					 when a player picks a bet
								|
							check input
							  |Y    |N		 (correct or not)
						user msg	user msg: error
					 disable btn
			  draw card & render
			  get value per hand									| add pauses where necessary
			     eval extra card
			      |Y         |N
	   draw card & rend      |
				  v			 v
					   win?
					 msg usr
	    update winnings/losings (required)
		     	  disable stuff
			      enable start


 */