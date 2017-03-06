package com.mum.edu.library.rule;

import com.mum.edu.library.ui.Login;

import javafx.application.Application;

public class LoginRuleSet implements RuleSet {

	@Override
	public void applyRule(Application application) {
		Login login = (Login) application;
	}

}
