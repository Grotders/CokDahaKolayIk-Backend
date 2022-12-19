package com.fmss.cokdahakolayik.unit.expense;

import com.fmss.cokdahakolayik.client.dto.request.CreateExpenseRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateExpenseRequest;
import com.fmss.cokdahakolayik.client.dto.response.ExpenseDto;
import com.fmss.cokdahakolayik.mapper.ExpenseMapperImpl;
import com.fmss.cokdahakolayik.model.entity.Expense;
import com.fmss.cokdahakolayik.unit.BaseUnitTest;
import com.fmss.cokdahakolayik.util.EmployeeUtil;
import com.fmss.cokdahakolayik.util.ExpenseUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExpenseConverterTest extends BaseUnitTest {

    @InjectMocks
    ExpenseMapperImpl expenseMapper;


    @Test
    void toExpenseFromCreateExpenseRequestTest() {
        CreateExpenseRequest expenseRequest = ExpenseUtil.generateCreateExpenseRequest(EMPLOYEE_ID);

        Expense expected = ExpenseUtil.generateExpense(null, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        Expense result = expenseMapper.toExpenseFromCreateExpenseRequest(expenseRequest);

        assertAll(
                () -> assertNull(result.getId()),
                () -> assertEquals(expected.getType(), result.getType()),
                () -> assertEquals(expected.getAmount(), result.getAmount()),
                () -> assertEquals(expected.getReceiptDate(), result.getReceiptDate()),
                () -> assertEquals(expected.getDescription(), result.getDescription()),
                () -> assertEquals(expected.getEmployee().getId(), result.getEmployee().getId())
        );
    }

    @Test
    void toExpenseDto() {
        Expense expense = ExpenseUtil.generateExpense(EXPENSE_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));

        ExpenseDto expected = ExpenseUtil.generateExpenseDto(EXPENSE_ID);
        ExpenseDto result = expenseMapper.toExpenseDto(expense);

        assertAll(
                () -> assertEquals(expected.id(), result.id()),
                () -> assertEquals(expected.type(), result.type()),
                () -> assertEquals(expected.amount(), result.amount()),
                () -> assertEquals(expected.receiptDate(), result.receiptDate()),
                () -> assertEquals(expected.description(), result.description())
        );
    }

    @Test
    void updateExpense() {
        Expense expense = ExpenseUtil.generateExpense(EXPENSE_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        UpdateExpenseRequest updateExpenseRequest = ExpenseUtil.generateUpdateExpenseRequest(EMPLOYEE_ID);

        Expense expected = ExpenseUtil.generateExpense2(EXPENSE_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        Expense result = expenseMapper.updateExpense(expense, updateExpenseRequest);

        assertAll(
                () -> assertEquals(expected.getId(), result.getId()),
                () -> assertEquals(expected.getType(), result.getType()),
                () -> assertEquals(expected.getAmount(), result.getAmount()),
                () -> assertEquals(expected.getReceiptDate(), result.getReceiptDate()),
                () -> assertEquals(expected.getDescription(), result.getDescription()),
                () -> assertEquals(expected.getEmployee().getId(), result.getEmployee().getId())
        );
    }
}
