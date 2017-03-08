package com.mum.edu.library.rule;

import org.apache.commons.lang.StringUtils;

import com.mum.edu.library.ui.LibraryMemberActionScreen;

import javafx.application.Application;
import javafx.stage.Stage;

public class MemberRuleSet implements RuleSet {

	@Override
	public void applyRule(Application application) {
		// Do not thing
	}

	@Override
	public void applyRule(Stage stage) throws RuleException {
		LibraryMemberActionScreen libraryMember = (LibraryMemberActionScreen) stage;
		if (StringUtils.isBlank(libraryMember.getMemberIdValue())
				|| StringUtils.isBlank(libraryMember.getFirstNameValue())
				|| StringUtils.isBlank(libraryMember.getLastNameValue())
				|| StringUtils.isBlank(libraryMember.getStreetValue())
				|| StringUtils.isBlank(libraryMember.getCityValue())
				|| StringUtils.isBlank(libraryMember.getStateValue())
				|| StringUtils.isBlank(libraryMember.getZipValue())
				|| StringUtils.isBlank(libraryMember.getPhoneNumberValue())) {
			throw new RuleException("All fields must be input");
		}

		numberic(libraryMember.getMemberIdValue(), libraryMember.getZipValue());

		if (StringUtils.equals(libraryMember.getMemberIdValue(), libraryMember.getZipValue())) {
			throw new RuleException("ID field may not equal zip field");
		}
	}

	private void numberic(String memberIdValue, String zipValue) throws RuleException {
		String regex = "\\d{5}";
		try {
			Integer.parseInt(memberIdValue);
			Integer.parseInt(zipValue);
			if (!zipValue.matches(regex)) {
				throw new RuleException("Zip must be numeric with exactly 5 digits");
			}
		} catch (NumberFormatException e) {
			throw new RuleException("ID or Zip field must be numeric");
		}
	}

}
