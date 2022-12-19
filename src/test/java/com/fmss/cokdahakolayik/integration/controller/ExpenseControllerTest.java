package com.fmss.cokdahakolayik.integration.controller;

import com.fmss.cokdahakolayik.client.dto.request.CreateExpenseRequest;
import com.fmss.cokdahakolayik.client.dto.response.ExpenseDto;
import com.fmss.cokdahakolayik.controller.ExpenseController;
import com.fmss.cokdahakolayik.integration.BaseIntegrationTest;
import com.fmss.cokdahakolayik.reposity.EmployeeRepository;
import com.fmss.cokdahakolayik.reposity.ExpenseRepository;
import com.fmss.cokdahakolayik.util.EmployeeUtil;
import com.fmss.cokdahakolayik.util.ExpenseUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ExpenseControllerTest extends BaseIntegrationTest {

    @Autowired
    ExpenseController expenseController;

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;



    @BeforeEach
    void setUp() {
        IntStream.iterate(0, i -> ++i).limit(NUMBER_OF_INSTANCE)
                .forEach((i) -> {
                    employeeRepository.save(EmployeeUtil.generateEmployee(null));
                    expenseRepository.save(ExpenseUtil.generateExpense(null, EmployeeUtil.generateEmployee(EMPLOYEE_ID)));
                });
    }

    @Test
    void addExpense_ValidInput_Create() throws Exception {
        CreateExpenseRequest request = ExpenseUtil.generateCreateExpenseRequest(EMPLOYEE_ID);
        ExpenseDto expected = ExpenseUtil.generateExpenseDto(EXPENSE_ID);

        this.mockMvc.perform(post(EXPENSE_API_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.type", is(expected.type())))
                .andExpect(jsonPath("$.amount", is(expected.amount())))
//                .andExpect(jsonPath("$.receiptDate", is(expenseDto.receiptDate())))
                .andExpect(jsonPath("$.description", is(expected.description())))
                .andReturn();
    }

    @Test
    void addExpense_InvalidEmployeeId_Create() throws Exception {
        CreateExpenseRequest request = ExpenseUtil.generateCreateExpenseRequest(Long.MAX_VALUE);

        this.mockMvc.perform(post(EXPENSE_API_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().string("Harcama eklenecek çal??an bulunamad?."))
                .andReturn();
    }

    @Test
    void addExpense_InvalidInput_BADREQUEST() throws Exception {
        CreateExpenseRequest request = CreateExpenseRequest.builder().build();

        this.mockMvc.perform(post(EXPENSE_API_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    void getExpenses() throws Exception {
        mockMvc.perform(get(EXPENSE_API_ENDPOINT + "/" + EMPLOYEE_ID))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(5)));
    }

    @Test
    void getExpense() throws Exception {
        ExpenseDto expected = ExpenseUtil.generateExpenseDto(EXPENSE_ID);

        mockMvc.perform(get(EXPENSE_API_ENDPOINT +"/" + EXPENSE_ID))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.type", is(expected.type())))
                .andExpect(jsonPath("$.amount", is(expected.amount())))
//                .andExpect(jsonPath("$.receiptDate", is(expenseDto.receiptDate())))
                .andExpect(jsonPath("$.description", is(expected.description())))
                .andReturn();
    }
//    @Disabled
//    @Test
//    void updateExpense() {
//    }
//    @Disabled
//    @Test
//    void deleteExpense() {
//    }
}