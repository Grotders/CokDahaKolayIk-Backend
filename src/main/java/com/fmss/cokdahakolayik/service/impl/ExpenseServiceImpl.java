package com.fmss.cokdahakolayik.service.impl;

import com.fmss.cokdahakolayik.client.dto.request.CreateExpenseRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateExpenseRequest;
import com.fmss.cokdahakolayik.client.dto.response.ExpenseDto;
import com.fmss.cokdahakolayik.configuration.aspect.ToLog;
import com.fmss.cokdahakolayik.exception.GeneralException;
import com.fmss.cokdahakolayik.mapper.ExpenseMapper;
import com.fmss.cokdahakolayik.model.entity.Expense;
import com.fmss.cokdahakolayik.reposity.EmployeeRepository;
import com.fmss.cokdahakolayik.reposity.ExpenseRepository;
import com.fmss.cokdahakolayik.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;
    private final EmployeeRepository employeeRepository;

    @ToLog
    @Override
    public ExpenseDto addExpense(CreateExpenseRequest expenseRequest) {
        boolean doesExist = this.employeeRepository.existsById(expenseRequest.employeeId());
        if (!doesExist)
            throw new GeneralException("Harcama eklenecek çalışan bulunamadı.");

        return this.expenseMapper.toExpenseDto(
                this.expenseRepository.save(
                        this.expenseMapper.toExpenseFromCreateExpenseRequest(expenseRequest)
                ));
    }

    @Override
    public List<ExpenseDto> getExpenses(Long employeeId, Integer page) {
        List<ExpenseDto> expenses = this.expenseRepository.findAllByEmployee_Id(employeeId, PageRequest.of(page-1, 5))
                .stream()
                .map(this.expenseMapper::toExpenseDto)
                .toList();

        if (expenses.isEmpty())
            throw new GeneralException("Harcamalar bulunamadı.");
        return expenses;
    }

    @ToLog
    @Override
    public ExpenseDto getExpense(Long expenseId) {
        return this.expenseRepository.findById(expenseId)
                    .map(this.expenseMapper::toExpenseDto)
                .orElseThrow(() -> new GeneralException("Harcama bulunamadı."));
    }

    @ToLog
    @Override
    public ExpenseDto updateExpense(Long expenseId, UpdateExpenseRequest expenseRequest) {
        Expense updatedExpense = this.expenseRepository.findById(expenseId)
                .map(expense -> this.expenseMapper.updateExpense(expense, expenseRequest))
                .orElseThrow(() -> new GeneralException("Harcama güncellenemedi."));
//        updatedExpense = this.expenseRepository.updateExpenseById(expenseId, updatedExpense);
        updatedExpense = this.expenseRepository.save(updatedExpense);
        return this.expenseMapper.toExpenseDto(updatedExpense);
    }

    @ToLog
    @Override
    public void deleteExpense(Long expenseId) {
        boolean doesExist = this.expenseRepository.existsById(expenseId);
        if (!doesExist)
            throw new GeneralException("Silinecek harcama bulunamadı.");
        this.expenseRepository.deleteById(expenseId);
    }
}
