package com.mum.edu.library.rule;

import com.mum.edu.library.ui.checkout.CheckoutOverViewController;

import javafx.application.Application;
import javafx.stage.Stage;

public class CheckoutOverViewRuleSet implements RuleSet {
	private CheckoutOverViewController controller;

	@Override
	public void applyRule(Application application) throws RuleException {
		// TODO Auto-generated method stub
	}

	private void isMemberIdNumber() throws RuleException{
		try{
			String memberId = controller.getMemberIdValue();
			if(memberId.trim().length() > 0){
				Integer.parseInt(memberId);
			}
		}
		catch(Exception ex){
			///throw new RuleException(ex.getMessage());
			throw new RuleException("MemberId is not number");
		}
	}
	
		
	@Override
	public void applyRule(Stage stage) throws RuleException {
		
	}

	@Override
	public void applyRule(Object object) throws RuleException {
		// TODO Auto-generated method stub
		controller =  (CheckoutOverViewController)object;
		isMemberIdNumber();
	}
}
