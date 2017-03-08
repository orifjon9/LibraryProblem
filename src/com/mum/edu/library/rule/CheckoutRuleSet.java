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
		
		isMemberIdNumber();
		
		if(checkoutBookWindow.getController().getSelectedItem() == null){
			throw new RuleException("Book does not select");
		}
	}

	private void isMemberIdNumber() throws RuleException{
		try{
			String memberId = checkoutBookWindow.getController().getMemberIdValue();
			Integer.parseInt(memberId);
		}
		catch(Exception ex){
			throw new RuleException(ex.getMessage());
			//throw new RuleException("MemberId id not number");
		}
	}
	
	@Override
	public void applyRule(Stage stage) throws RuleException {
		// TODO Auto-generated method stub
		
	}

}
