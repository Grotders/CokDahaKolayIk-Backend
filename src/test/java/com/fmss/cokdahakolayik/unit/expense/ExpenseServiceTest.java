package com.fmss.cokdahakolayik.unit.expense;

import com.fmss.cokdahakolayik.client.dto.request.CreateExpenseRequest;
import com.fmss.cokdahakolayik.client.dto.request.CreateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateExpenseRequest;
import com.fmss.cokdahakolayik.client.dto.response.ExpenseDto;
import com.fmss.cokdahakolayik.exception.GeneralException;
import com.fmss.cokdahakolayik.mapper.ExpenseMapper;
import com.fmss.cokdahakolayik.model.entity.Expense;
import com.fmss.cokdahakolayik.reposity.EmployeeRepository;
import com.fmss.cokdahakolayik.reposity.ExpenseRepository;
import com.fmss.cokdahakolayik.service.impl.ExpenseServiceImpl;
import com.fmss.cokdahakolayik.unit.BaseUnitTest;
import com.fmss.cokdahakolayik.util.EmployeeUtil;
import com.fmss.cokdahakolayik.util.ExpenseUtil;
import com.fmss.cokdahakolayik.util.LeaveUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest extends BaseUnitTest {

    @InjectMocks
    ExpenseServiceImpl expenseService;

    @Mock
    ExpenseMapper expenseMapper;

    @Mock
    ExpenseRepository expenseRepository;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    void addExpense_EmployeeExistInDatabase() {
        // given / Arrange
        CreateExpenseRequest createExpenseRequest = ExpenseUtil.generateCreateExpenseRequest(EMPLOYEE_ID);
        Expense preSaveExpense = ExpenseUtil.generateExpense(null, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        Expense postSaveExpense = ExpenseUtil.generateExpense(EXPENSE_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        ExpenseDto expected = ExpenseUtil.generateExpenseDto(EXPENSE_ID);

        // when / Action
        when(this.employeeRepository.existsById(any())).thenReturn(true);
        when(this.expenseMapper.toExpenseFromCreateExpenseRequest(any())).thenReturn(preSaveExpense);
        when(this.expenseRepository.save(any())).thenReturn(postSaveExpense);
        when(this.expenseMapper.toExpenseDto(any())).thenReturn(expected);

        // then / Assertion
        ExpenseDto result = expenseService.addExpense(createExpenseRequest);

        assertSame(expected, result);

        verify(employeeRepository).existsById(EMPLOYEE_ID);
        verify(expenseMapper).toExpenseFromCreateExpenseRequest(createExpenseRequest);
        verify(expenseRepository).save(preSaveExpense);
        verify(expenseMapper).toExpenseDto(postSaveExpense);
    }

    @Test
    void addExpense_EmployeeDoesNotExistInDatabase_GeneralException() {
        CreateExpenseRequest createExpenseRequest = ExpenseUtil.generateCreateExpenseRequest(EMPLOYEE_ID);
        when(employeeRepository.existsById(any())).thenReturn(false);
        assertThrows(GeneralException.class, () -> expenseService.addExpense(createExpenseRequest));
        verify(employeeRepository).existsById(EMPLOYEE_ID);
        verifyNoInteractions(expenseMapper);
    }

    // Pagination var bu yüzden sayfada gösterilecek harcama sayısı azaltılırsa bu test fail edecektir
    @Test
    void getExpenses_PageDoesHaveExpense() {
        Integer page = 1;
        Expense expense = ExpenseUtil.generateExpense(EXPENSE_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        List<Expense> expenses = List.of(expense);
        ExpenseDto expenseDto = ExpenseUtil.generateExpenseDto(EXPENSE_ID);
        List<ExpenseDto> expenseDtos = List.of(expenseDto);

        when(expenseRepository.findAllByEmployee_Id(any(), any())).thenReturn(expenses);
        when(expenseMapper.toExpenseDto(any())).thenReturn(expenseDto);

        List<ExpenseDto> result = expenseService.getExpenses(EMPLOYEE_ID, page);

        assertEquals(expenseDtos, result);

        verify(expenseRepository).findAllByEmployee_Id(EMPLOYEE_ID, PageRequest.of(0, 5));
        verify(expenseMapper).toExpenseDto(expense);
    }

    @ParameterizedTest
    @ValueSource(ints = {2,3,4})
    void getExpenses_PageDoesNotHaveAnyExpense_GeneralException(Integer page) {
        List<Expense> expenses = List.of();
        when(expenseRepository.findAllByEmployee_Id(any(), any())).thenReturn(expenses);
        assertThrows(GeneralException.class, () -> expenseService.getExpenses(EMPLOYEE_ID, page));
        verify(expenseRepository).findAllByEmployee_Id(EMPLOYEE_ID, PageRequest.of(page-1, 5));
        verifyNoInteractions(expenseMapper);
    }

    @Test
    void getExpense_ExpenseFoundInDatabase() {
        Expense expense = ExpenseUtil.generateExpense(EXPENSE_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        ExpenseDto expenseDto = ExpenseUtil.generateExpenseDto(EMPLOYEE_ID);

        when(expenseRepository.findById(any())).thenReturn(Optional.of(expense));
        when(expenseMapper.toExpenseDto(any())).thenReturn(expenseDto);

        ExpenseDto result = expenseService.getExpense(EXPENSE_ID);

        assertEquals(expenseDto, result);

        verify(expenseRepository).findById(EXPENSE_ID);
        verify(expenseMapper).toExpenseDto(expense);
    }

    @Test
    void getExpense_ExpenseDoesNotFindInDatabase_GeneralException() {
        assertThrows(GeneralException.class, () -> expenseService.getExpense(EXPENSE_ID));
        verify(expenseRepository).findById(EXPENSE_ID);
        verifyNoInteractions(expenseMapper);
    }

    @Test
    void updateExpense_ValidInput() {
        UpdateExpenseRequest updateExpenseRequest = ExpenseUtil.generateUpdateExpenseRequest(EMPLOYEE_ID);
        Expense expense = ExpenseUtil.generateExpense(EXPENSE_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        Expense updatedExpense = ExpenseUtil.generateExpense2(EXPENSE_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        ExpenseDto expenseDto = ExpenseUtil.generateExpenseDto2(EXPENSE_ID);

        when(expenseRepository.findById(any())).thenReturn(Optional.of(expense));
        when(expenseMapper.updateExpense(any(), any())).thenReturn(updatedExpense);
        when(expenseRepository.updateExpenseById(EXPENSE_ID, updatedExpense)).thenReturn(updatedExpense);
        when(expenseMapper.toExpenseDto(any())).thenReturn(expenseDto);

        ExpenseDto result = expenseService.updateExpense(EXPENSE_ID, updateExpenseRequest);
        assertEquals(expenseDto, result);

        verify(expenseRepository).findById(EXPENSE_ID);
        verify(expenseMapper).updateExpense(expense, updateExpenseRequest);
        verify(expenseRepository).updateExpenseById(EXPENSE_ID, updatedExpense);
        verify(expenseMapper).toExpenseDto(updatedExpense);
    }

    @Test
    void updateExpense_DoesNotFindInDatabase_GeneralException() {
        UpdateExpenseRequest updateExpenseRequest = ExpenseUtil.generateUpdateExpenseRequest(EMPLOYEE_ID);
        assertThrows(GeneralException.class, () -> expenseService.updateExpense(EXPENSE_ID, updateExpenseRequest));
        verify(expenseRepository).findById(EXPENSE_ID);
        verifyNoInteractions(expenseMapper);
        verify(expenseRepository, never()).updateExpenseById(any(), any());
    }

    @Test
    void deleteExpense_ExpenseFoundInDatabase() {
        when(expenseRepository.existsById(EXPENSE_ID)).thenReturn(true);
        expenseService.deleteExpense(EXPENSE_ID);
        verify(expenseRepository).existsById(EXPENSE_ID);
        verify(expenseRepository).deleteById(EXPENSE_ID);
    }

    @Test
    void deleteExpense_ExpenseDoesNotFindInDatabase_GeneralException() {
        when(expenseRepository.existsById(EXPENSE_ID)).thenReturn(false);
        assertThrows(GeneralException.class, () -> expenseService.deleteExpense(EXPENSE_ID));
        verify(expenseRepository).existsById(EXPENSE_ID);
        verify(expenseRepository, never()).deleteById(EXPENSE_ID);
    }
}
