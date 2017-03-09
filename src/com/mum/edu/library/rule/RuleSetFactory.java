package com.mum.edu.library.rule;

import java.util.HashMap;

import com.mum.edu.library.ui.AddBookScreen;
import com.mum.edu.library.ui.LibraryMemberActionScreen;
import com.mum.edu.library.ui.LoginScreen;
import com.mum.edu.library.ui.checkout.CheckoutBookController;
import com.mum.edu.library.ui.checkout.CheckoutBookWindow;

import javafx.application.Application;
import javafx.stage.Stage;

public class RuleSetFactory {
	private RuleSetFactory() {
	}

	static HashMap<Class<? extends Application>, RuleSet> maps = new HashMap<>();
	static HashMap<Class<? extends Stage>, RuleSet> mapStages = new HashMap<>();
	static HashMap<Class<? extends Object>, RuleSet> mapObject = new HashMap<>();

	static {
		maps.put(LoginScreen.class, new LoginRuleSet());
		maps.put(CheckoutBookWindow.class, new CheckoutRuleSet());
		
		mapStages.put(LibraryMemberActionScreen.class, new MemberRuleSet());
		mapStages.put(AddBookScreen.class, new AddBookRuleSet());
		mapObject.put(CheckoutBookController.class, new CheckoutRuleSet());
	}

	public static RuleSet getRuleSet(Application app) {
		Class<? extends Application> c1 = app.getClass();
		if (!maps.containsKey(c1)) {
			throw new IllegalArgumentException("No RuleSet found for this Component");
		}
		return maps.get(c1);
	}
	
	public static RuleSet getRuleSet(Stage app) {
		Class<? extends Stage> c1 = app.getClass();
		if (!mapStages.containsKey(c1)) {
			throw new IllegalArgumentException("No RuleSet found for this Component");
		}
		return mapStages.get(c1);
	}
	
	public static RuleSet getRuleSet(Object app) {
		Class<? extends Object> c1 = app.getClass();
		if (!mapObject.containsKey(c1)) {
			throw new IllegalArgumentException("No RuleSet found for this Component");
		}
		return mapObject.get(c1);
	}
}
