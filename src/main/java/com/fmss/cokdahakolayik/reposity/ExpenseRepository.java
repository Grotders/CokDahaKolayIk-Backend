package com.fmss.cokdahakolayik.reposity;

import com.fmss.cokdahakolayik.model.entity.Expense;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByEmployee_Id(Long employeeId, Pageable pageable);

//    @Query(value = "UPDATE Expense as e set e.amount=:#{#expense.amount}, e.description =:#{#expense.description}" +
//            ", e.receiptDate =:#{#expense.receiptDate}, e.type =:#{#expense.type} where e.id =:#{#expenseId}", nativeQuery = true)
//    Expense updateExpenseById(@Param("expenseId") Long expenseId, @Param("expense") Expense expense);
}
