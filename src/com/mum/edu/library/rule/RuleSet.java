package com.mum.edu.library.rule;

import javafx.application.Application;
import javafx.stage.Stage;

public interface RuleSet {
	void applyRule(Application application) throws RuleException;

	void applyRule(Stage stage) throws RuleException; 
	
	void applyRule(Object object) throws RuleException;
	

}
