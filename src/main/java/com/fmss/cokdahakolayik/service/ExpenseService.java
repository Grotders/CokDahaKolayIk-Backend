package com.fmss.cokdahakolayik.service;

import com.fmss.cokdahakolayik.client.dto.request.CreateExpenseRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateExpenseRequest;
import com.fmss.cokdahakolayik.client.dto.response.ExpenseDto;

import java.util.List;

public interface ExpenseService {

    ExpenseDto addExpense(CreateExpenseRequest expenseRequest);
    List<ExpenseDto> getExpenses(Long employeeId, Integer page);
    ExpenseDto getExpense(Long expenseId);
    ExpenseDto updateExpense(Long expenseId, UpdateExpenseRequest expenseRequest);
    void deleteExpense(Long expenseId);
}
