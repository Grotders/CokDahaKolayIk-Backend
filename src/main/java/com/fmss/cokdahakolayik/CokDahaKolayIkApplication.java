package com.fmss.cokdahakolayik;


import com.fmss.cokdahakolayik.client.dto.request.CreateEmployeeRequest;
import com.fmss.cokdahakolayik.client.dto.request.CreateExpenseRequest;
import com.fmss.cokdahakolayik.client.dto.request.CreateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.request.CreateOvertimeRequest;
import com.fmss.cokdahakolayik.model.enums.Department;
import com.fmss.cokdahakolayik.model.enums.DeveloperLevel;
import com.fmss.cokdahakolayik.model.enums.DeveloperTier;
import com.fmss.cokdahakolayik.model.enums.DeveloperTitle;
import com.fmss.cokdahakolayik.service.EmployeeService;
import com.fmss.cokdahakolayik.service.ExpenseService;
import com.fmss.cokdahakolayik.service.LeaveService;
import com.fmss.cokdahakolayik.service.OvertimeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableCaching
public class CokDahaKolayIkApplication {

    public static void main(String[] args) {
        SpringApplication.run(CokDahaKolayIkApplication.class, args);
    }

    @Bean
    @Profile("!test")
    CommandLineRunner run(EmployeeService employeeService, LeaveService leaveService, ExpenseService expenseService,
                          OvertimeService overtimeService) {
        return args -> {
            CreateEmployeeRequest employeeRequest = CreateEmployeeRequest.builder()
                    .email("oguzcan.9@hotmail.com").firstname("oğuzcan")
                    .lastname("biçer").birthdate(LocalDate.now().minusYears(25))
                    .salary(42500.0).department(Department.ANALYST).startWorkDate(LocalDate.now())
                    .developerLevel(DeveloperLevel.MID).developerTier(DeveloperTier.J1).developerTitle(DeveloperTitle.JAVA)
                    .phoneNumber("05555555").addressLine("Cardak sokak").city("Istanbul").country("Turkey").postcode(1111).build();
            employeeService.createEmployee(employeeRequest);
            employeeService.createEmployee(employeeRequest);
            employeeService.createEmployee(employeeRequest);
            employeeService.createEmployee(employeeRequest);
            employeeService.createEmployee(employeeRequest);
            employeeService.createEmployee(employeeRequest);
            employeeService.createEmployee(employeeRequest);

            CreateLeaveRequest leaveRequest = CreateLeaveRequest.builder()
                    .type("saglik").startDate(LocalDate.now().minusDays(21))
                    .endDate(LocalDate.now()).description("karnim agriyo")
                    .employeeId(1L).build();
            leaveService.addLeave(leaveRequest);
            leaveService.addLeave(leaveRequest);
            leaveService.addLeave(leaveRequest);
            leaveService.addLeave(leaveRequest);
            leaveService.addLeave(leaveRequest);
            leaveService.addLeave(leaveRequest);
            leaveService.addLeave(leaveRequest);

            CreateExpenseRequest expenseRequest = CreateExpenseRequest.builder()
                    .type("kira").amount(2000.0).description("kiramı ödüyorum").employeeId(1L)
                    .receiptDate(LocalDate.now()).build();
            expenseService.addExpense(expenseRequest);
            expenseService.addExpense(expenseRequest);
            expenseService.addExpense(expenseRequest);
            expenseService.addExpense(expenseRequest);
            expenseService.addExpense(expenseRequest);
            expenseService.addExpense(expenseRequest);
            expenseService.addExpense(expenseRequest);

            CreateOvertimeRequest overtimeRequest = CreateOvertimeRequest.builder()
                    .amountOvertime(15.0).overtimeDate(LocalDate.now()).description("ekstra")
                    .employeeId(1L).build();
            overtimeService.addOvertime(overtimeRequest);
            overtimeService.addOvertime(overtimeRequest);
            overtimeService.addOvertime(overtimeRequest);
            overtimeService.addOvertime(overtimeRequest);
            overtimeService.addOvertime(overtimeRequest);
            overtimeService.addOvertime(overtimeRequest);
            overtimeService.addOvertime(overtimeRequest);
        };
    }
}
