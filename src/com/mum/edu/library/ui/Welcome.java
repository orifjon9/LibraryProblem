package com.mum.edu.library.ui;

import java.util.Set;

import javax.swing.JOptionPane;


import com.mum.edu.library.dao.MemberDAO;
import com.mum.edu.library.dao.impl.MemberDAOImpl;
import com.mum.edu.library.model.Member;

import com.mum.edu.library.model.Role;
import com.mum.edu.library.ui.checkout.CheckoutBookWindow;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Welcome extends Stage {
	public static final Welcome INSTANCE = new Welcome();
	Stage primaryStage;
	private Menu homeMenu;
	private Menu adminMenu;
	private Menu librarianMenu;
	
	private Welcome() {
	}

	public void setStage(Stage ps, Set<Role> roles) {
		primaryStage = ps;
		primaryStage.setTitle("Welcome Page");

		VBox topContainer = new VBox();

		MenuBar mainMenu = new MenuBar();

		Text label = new Text("NEW BOOK");
		label.setFont(Font.font("700 24px/24px 'Lora',sans-serif", FontWeight.BOLD, 30));
		label.setId("new-book");
		
		HBox labelBox = new HBox(10);
		labelBox.setAlignment(Pos.CENTER);
		labelBox.getChildren().add(label);

		GridPane gridpane = new GridPane();
		gridpane.setPadding(new Insets(5, 5, 5, 30));
		gridpane.setHgap(10);
		gridpane.setVgap(10);

		// --------------New Book------------------
		HBox habitBox = new HBox(10);
		ImageView habit = new ImageView();
		Image habitImage = new Image(Welcome.class.getResourceAsStream("habit.jpg"));
		habit.setImage(habitImage);
		habitBox.getChildren().add(habit);
		gridpane.add(habitBox, 1, 1);

		HBox fahrenheitBox = new HBox(10);
		ImageView fahrement = new ImageView();
		Image fahrementImage = new Image(Welcome.class.getResourceAsStream("fahrenheit.jpg"));
		fahrement.setImage(fahrementImage);
		fahrenheitBox.getChildren().add(fahrement);
		gridpane.add(fahrenheitBox, 2, 1);

		HBox oceanBox = new HBox(10);
		ImageView ocean = new ImageView();
		Image oceanImage = new Image(Welcome.class.getResourceAsStream("ocean.jpg"));
		ocean.setImage(oceanImage);
		oceanBox.getChildren().add(ocean);
		gridpane.add(oceanBox, 3, 1);

		HBox theFaultBox = new HBox(10);
		ImageView theFault = new ImageView();
		Image theFaultImage = new Image(Welcome.class.getResourceAsStream("theFault.jpg"));
		theFault.setImage(theFaultImage);
		theFaultBox.getChildren().add(theFault);
		gridpane.add(theFaultBox, 1, 2);

		HBox quietBox = new HBox(10);
		ImageView quiet = new ImageView();
		Image quietImage = new Image(Welcome.class.getResourceAsStream("quiet.jpg"));
		quiet.setImage(quietImage);
		quietBox.getChildren().add(quiet);
		gridpane.add(quietBox, 2, 2);

		HBox agreementBox = new HBox(10);
		ImageView agreement = new ImageView();
		Image agreementImage = new Image(Welcome.class.getResourceAsStream("agreement.jpg"));
		agreement.setImage(agreementImage);
		agreementBox.getChildren().add(agreement);
		gridpane.add(agreementBox, 3, 2);

		topContainer.getChildren().add(mainMenu);
		topContainer.getChildren().add(labelBox);
		topContainer.getChildren().add(gridpane);

		homeMenu = new Menu("Home");
		MenuItem logout = new MenuItem("Logout");
		MenuItem exitApp = new MenuItem("Exit");
		homeMenu.getItems().addAll(logout, exitApp);
		exitApp.setOnAction(evt -> Platform.exit());
		logout.setOnAction(evt -> {
			//Login login = Login.INSTANCE;
			//login.start(primaryStage);
		});

		librarianMenu = new Menu("Librarian");
		MenuItem checkoutBook = new MenuItem("Checkout Book");
		librarianMenu.getItems().addAll(checkoutBook);
		checkoutBook.setOnAction(evt -> {
			try {
				CheckoutBookWindow.getInstance().start(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			};
		});

		// did not add menu item for menu edit
		adminMenu = new Menu("Administrator");
		MenuItem addBook = new MenuItem("Add Book");
		MenuItem addCopy = new MenuItem("Add Copy");
		
		addBook.setOnAction(evt -> {
			AddBook addBookWindow = AddBook.INSTANCE;
			addBookWindow.setStage(primaryStage);
		});

		
		addCopy.setOnAction(evt -> {
			AddCopy addCopyWindow = AddCopy.INSTANCE;
			addCopyWindow.setStage(primaryStage);
		});

		/*MenuItem createLibraryMember = new MenuItem("Create Member");
		createLibraryMember.setOnAction(evt -> {
			AddLibraryMember libraryMember = AddLibraryMember.INSTANCE;
			libraryMember.setStage(primaryStage, roles);
		});

		MenuItem libraryMemberManage = new MenuItem("Member");
		
		libraryMemberManage.setOnAction(evt -> {
			ManageMemberScreen manageMember = ManageMemberScreen.INSTANCE;
			manageMember.setStage(primaryStage, roles);
			// we must load data from member xml
			MemberDAO memberDAO = new MemberDAOImpl();
			ObservableList<Member> members = FXCollections.observableArrayList(memberDAO.loadMembers());
			manageMember.setData(members);
		});
		
		adminMenu.getItems().addAll(addBook, addCopy, createLibraryMember, libraryMemberManage);
		mainMenu.getMenus().addAll(homeMenu, librarianMenu, adminMenu);
		*/
		authority(roles);
		// must have to show
		Scene scene = new Scene(topContainer, 1000, 520);
		primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("welcome.css").toExternalForm());
		primaryStage.show();
	}

	private void authority(Set<Role> roles) {
		if (roles.size() == 2) {
			return;
		}
		for(Role role : roles) {
			if ("LIBRARIAN".equals(role.getRoleName())) {
				adminMenu.setDisable(true);
				librarianMenu.setDisable(false);
			} else {
				librarianMenu.setDisable(true);
				adminMenu.setDisable(false);
			}
		}
	}
}
