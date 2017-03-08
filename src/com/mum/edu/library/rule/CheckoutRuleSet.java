package com.mum.edu.library.rule;

import com.mum.edu.library.ui.checkout.CheckoutBookWindow;

import javafx.application.Application;
import javafx.stage.Stage;

public class CheckoutRuleSet implements RuleSet {
	private CheckoutBookWindow checkoutBookWindow;

	@Override
	public void applyRule(Application application) throws RuleException {
		// TODO Auto-generated method stub
		checkoutBookWindow = (CheckoutBookWindow)application;
	}

	@Override
	public void applyRule(Stage stage) throws RuleException {
		// TODO Auto-generated method stub
		
	}

}
