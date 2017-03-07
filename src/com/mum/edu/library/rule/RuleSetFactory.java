package com.mum.edu.library.rule;

import java.util.HashMap;

import com.mum.edu.library.ui.AddLibraryMember;
import com.mum.edu.library.ui.Login;

import javafx.application.Application;
import javafx.stage.Stage;

public class RuleSetFactory {
	private RuleSetFactory() {
	}

	static HashMap<Class<? extends Application>, RuleSet> maps = new HashMap<>();
	static HashMap<Class<? extends Stage>, RuleSet> mapStages = new HashMap<>();

	static {
		maps.put(Login.class, new LoginRuleSet());
		mapStages.put(AddLibraryMember.class, new MemberRuleSet());
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
}
