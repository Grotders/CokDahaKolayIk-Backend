package com.fmss.cokdahakolayik.util;

import com.fmss.cokdahakolayik.client.dto.request.CreateExpenseRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateExpenseRequest;
import com.fmss.cokdahakolayik.client.dto.response.ExpenseDto;
import com.fmss.cokdahakolayik.model.entity.Employee;
import com.fmss.cokdahakolayik.model.entity.Expense;

import java.time.LocalDate;

public class ExpenseUtil extends BaseUtil {


    // Expense ####################################################################################################
    // 2 Create, 1 Update, 2 entity, 2 Response

    public static CreateExpenseRequest generateCreateExpenseRequest(Long employeeId) {
        return CreateExpenseRequest.builder()
                .employeeId(employeeId)
                .type("bilgisayar tamiri")
                .amount(8000.0)
                .receiptDate(getDate.minusDays(5))
                .description("şirket bilgisayarı bozuldu.")
                .build();
    }

//    public CreateExpenseRequest generateCreateExpenseRequest2(Long employeeId) {
//        return CreateExpenseRequest.builder()
//                .employeeId(employeeId)
//                .type("yemek").amount(150.0).receiptDate(getDate)
//                .description("mesai yemeği")
//                .build();
//    }

    public static UpdateExpenseRequest generateUpdateExpenseRequest(Long employeeId) {
        return UpdateExpenseRequest.builder()
                .employeeId(employeeId)
                .type("bilgisayar tamiri(Updated)")
                .amount(7777.0)
                .receiptDate(getDate.minusDays(5))
                .description("şirket bilgisayarı bozuldu.")
                .build();
    }

    public static Expense generateExpense(Long id, Employee employee) {
        Expense expense = new Expense();
        expense.setId(id);
        expense.setType("bilgisayar tamiri");
        expense.setAmount(8000.0);
        expense.setReceiptDate(getDate.minusDays(5));
        expense.setDescription("şirket bilgisayarı bozuldu.");
        expense.setEmployee(employee);
        return expense;
    }

    public static Expense generateExpense2(Long id, Employee employee) {
        Expense expense = new Expense();
        expense.setId(id);
        expense.setType("bilgisayar tamiri(Updated)");
        expense.setAmount(7777.0);
        expense.setReceiptDate(getDate.minusDays(5));
        expense.setDescription("şirket bilgisayarı bozuldu.");
        expense.setEmployee(employee);
        return expense;
    }

    public static ExpenseDto generateExpenseDto(Long id) {
        return ExpenseDto.builder()
                .id(id)
                .type("bilgisayar tamiri")
                .amount(8000.0)
                .receiptDate(getDate.minusDays(5))
                .description("şirket bilgisayarı bozuldu.")
                .build();
    }

    public static ExpenseDto generateExpenseDto2(Long id) {
        return ExpenseDto.builder()
                .id(id)
                .type("bilgisayar tamiri(Updated)")
                .amount(7777.0)
                .receiptDate(getDate.minusDays(5))
                .description("şirket bilgisayarı bozuldu.")
                .build();
    }

    public static Employee generateEmployee(Long employeeId) {
        return EmployeeUtil.generateEmployee(employeeId);
    }
}
