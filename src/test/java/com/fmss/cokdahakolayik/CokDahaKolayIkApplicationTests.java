package com.fmss.cokdahakolayik;

import com.fmss.cokdahakolayik.service.EmployeeService;
import com.fmss.cokdahakolayik.service.ExpenseService;
import com.fmss.cokdahakolayik.service.LeaveService;
import com.fmss.cokdahakolayik.service.OvertimeService;
import com.fmss.cokdahakolayik.util.EmployeeUtil;
import com.fmss.cokdahakolayik.util.ExpenseUtil;
import com.fmss.cokdahakolayik.util.LeaveUtil;
import com.fmss.cokdahakolayik.util.OvertimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class CokDahaKolayIkApplicationTests {

    @Test
    void contextLoads() {
    }

    @Bean
    CommandLineRunner run(EmployeeService employeeService, LeaveService leaveService, ExpenseService expenseService,
                          OvertimeService overtimeService) {
        return args -> {
            employeeService.createEmployee(EmployeeUtil.generateCreateEmployeeRequest());

            leaveService.addLeave(LeaveUtil.generateCreateLeaveRequest(1L));
            leaveService.addLeave(LeaveUtil.generateCreateLeaveRequest(1L));
            leaveService.addLeave(LeaveUtil.generateCreateLeaveRequest(1L));
            leaveService.addLeave(LeaveUtil.generateCreateLeaveRequest(1L));
            leaveService.addLeave(LeaveUtil.generateCreateLeaveRequest(1L));
            leaveService.addLeave(LeaveUtil.generateCreateLeaveRequest(1L));

            expenseService.addExpense(ExpenseUtil.generateCreateExpenseRequest(1L));
            expenseService.addExpense(ExpenseUtil.generateCreateExpenseRequest(1L));
            expenseService.addExpense(ExpenseUtil.generateCreateExpenseRequest(1L));
            expenseService.addExpense(ExpenseUtil.generateCreateExpenseRequest(1L));
            expenseService.addExpense(ExpenseUtil.generateCreateExpenseRequest(1L));
            expenseService.addExpense(ExpenseUtil.generateCreateExpenseRequest(1L));

            overtimeService.addOvertime(OvertimeUtil.generateCreateOvertimeRequest(1L));
            overtimeService.addOvertime(OvertimeUtil.generateCreateOvertimeRequest(1L));
            overtimeService.addOvertime(OvertimeUtil.generateCreateOvertimeRequest(1L));
            overtimeService.addOvertime(OvertimeUtil.generateCreateOvertimeRequest(1L));
            overtimeService.addOvertime(OvertimeUtil.generateCreateOvertimeRequest(1L));
            overtimeService.addOvertime(OvertimeUtil.generateCreateOvertimeRequest(1L));
        };
    }
}
