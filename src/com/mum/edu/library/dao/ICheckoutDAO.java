package com.mum.edu.library.dao;

import java.util.List;
import com.mum.edu.library.model.CheckoutRecord;

public interface ICheckoutDAO {
	public void save(CheckoutRecord checkoutRecord);
	public List<CheckoutRecord> read();
}
