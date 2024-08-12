package com.forkalau.lmsjava.services.iservices;

import com.forkalau.lmsjava.domain.Loan;

public interface ILoanService {
    Loan saveOrUpdateLoan (Long bookID, Long userID);

}
