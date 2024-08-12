package com.forkalau.lmsjava.services;


import com.forkalau.lmsjava.domain.Book;
import com.forkalau.lmsjava.domain.Loan;
import com.forkalau.lmsjava.domain.User;
import com.forkalau.lmsjava.repositories.IBookRepository;
import com.forkalau.lmsjava.repositories.ILoanRepository;
import com.forkalau.lmsjava.repositories.IUserRepository;
import com.forkalau.lmsjava.services.iservices.IFactory;
import com.forkalau.lmsjava.services.iservices.ILoanService;
import com.forkalau.lmsjava.services.iservices.IWriteLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class LoanService implements ILoanService {

    @Autowired
    private ILoanRepository loanRepository;

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IWriteLog writeLog;

    @Autowired
    private IFactory factory;


    @Override
    public Loan saveOrUpdateLoan (Long bookID, Long userID) {
        try {
            Optional<Book> bookOption = bookRepository.findById(bookID);
            Optional<User> userOption = userRepository.findById(userID);
            if (bookOption != null && userOption != null) {
                Book book = bookOption.get();
                User user = userOption.get();
                Date dueDate = factory.NewDate();
                Loan loan = factory.CreateLoan();
                loan.setDueDate(dueDate);
                loan.setBook(book);
                loan.setUser(user);
                Loan loan1 = loanRepository.save(loan);
                writeLog.saveLog("done");
                return loan1;

            } else {
                return null;
            }
        } catch (Exception e) {throw factory.ThrowException("Loan BookID:" + bookID + ", UserId: " + userID + " already exist");}
    }


}
