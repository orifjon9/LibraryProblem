package com.mum.edu.library.rule;

import org.apache.commons.lang.StringUtils;

import com.mum.edu.library.dao.BookDAO;
import com.mum.edu.library.dao.impl.BookDAOImpl;
import com.mum.edu.library.ui.AddBookScreen;

import javafx.application.Application;
import javafx.stage.Stage;

public class AddBookRuleSet implements RuleSet {

	private BookDAO BookDAO = new BookDAOImpl();

	@Override
	public void applyRule(Stage stage) throws RuleException {
		AddBookScreen mainScreen = (AddBookScreen) stage;
		String isbn = mainScreen.getIsbn().getText();
		String title = mainScreen.getBookTitle().getText();
		String maxCheckout = mainScreen.getBorrowAbleDate().getText();
		String numberOfCopy = mainScreen.getNumberOfCopy().getText();
		String fistName = mainScreen.getFirstName().getText();
		String lastName = mainScreen.getLastName().getText();
		String phone = mainScreen.getPhone().getText();
		String credentials = mainScreen.getCredentials().getText();
		String street = mainScreen.getStreet().getText();
		String city = mainScreen.getCity().getText();
		String zip = mainScreen.getZip().getText();
		String shortBio = mainScreen.getShortBio().getText();
		String state = mainScreen.getStateCb().getValue();

		if (StringUtils.isBlank(isbn) || StringUtils.isBlank(title) || StringUtils.isBlank(maxCheckout)
				|| StringUtils.isBlank(numberOfCopy) || StringUtils.isBlank(fistName) || StringUtils.isBlank(lastName)
				|| StringUtils.isBlank(phone) || StringUtils.isBlank(credentials) || StringUtils.isBlank(street)
				|| StringUtils.isBlank(city) || StringUtils.isBlank(zip) || StringUtils.isBlank(shortBio)
				|| StringUtils.isBlank(state)) {
			throw new RuleException("All fields must be input! Please...");
		}

		try {
			if (BookDAO.searchBook(isbn) != null) {
				throw new RuleException("This book is existing in library, Please choose another ISBN");
			}
		} catch (ApplicationException e) {
			throw new RuleException(e.getMessage());
		}
		numberic(isbn, maxCheckout, numberOfCopy, zip);
	}

	private void numberic(String isbn, String maxCheckout, String numberOfCopy, String zip) throws RuleException {
		String regex = "\\d{5}";
		try {
			Integer.parseInt(isbn);
			Integer.parseInt(maxCheckout);
			Integer.parseInt(numberOfCopy);
			Integer.parseInt(zip);
			if (!zip.matches(regex)) {
				throw new RuleException("Zip must be numeric with exactly 5 digits");
			}
		} catch (NumberFormatException e) {
			throw new RuleException("ISBN, zipcode, maxCheckout and numberOfCopy field must be numeric");
		}
	}

	@Override
	public void applyRule(Object object) throws RuleException {
		// Do not thing
	}

	@Override
	public void applyRule(Application application) throws RuleException {
		// Do not thing

	}

}
