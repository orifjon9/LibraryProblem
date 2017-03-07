package com.mum.edu.library.rule;

import com.mum.edu.library.ui.Login;

import javafx.application.Application;
import javafx.stage.Stage;

public class LoginRuleSet implements RuleSet {

	@Override
	public void applyRule(Application application) throws RuleException {
		Login login = (Login) application;
		String userNameValue = login.getUserNameValue();
		try {
			Integer.parseInt(userNameValue);
		} catch(NumberFormatException ex) {
			throw new RuleException("UserName or Password is wrong");
		}
	}

	@Override
	public void applyRule(Stage stage) {
		// Do not thing
	}

}
