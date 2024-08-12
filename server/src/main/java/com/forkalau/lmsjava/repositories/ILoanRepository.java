package com.forkalau.lmsjava.repositories;

import com.forkalau.lmsjava.domain.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoanRepository extends CrudRepository<Loan, Long> {
}
