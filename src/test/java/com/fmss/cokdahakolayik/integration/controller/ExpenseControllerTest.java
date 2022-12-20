package com.fmss.cokdahakolayik.integration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fmss.cokdahakolayik.client.dto.request.CreateExpenseRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateExpenseRequest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                .andExpect(status().isCreated())
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
                .andExpect(content().string("Harcama eklenecek çalisan bulunamadi."))
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
        mockMvc.perform(get(EXPENSE_API_ENDPOINT +"?employeeId=" + EMPLOYEE_ID + "&page=" + "1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    void getExpense_ValidExpenseId() throws Exception {
        ExpenseDto expected = ExpenseUtil.generateExpenseDto(null);

        mockMvc.perform(get(EXPENSE_API_ENDPOINT +"/" + EXPENSE_ID))
                .andDo(System.out::println)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.type", is(expected.type())))
                .andExpect(jsonPath("$.amount", is(expected.amount())))
//                .andExpect(jsonPath("$.receiptDate", is(expenseDto.receiptDate())))
                .andExpect(jsonPath("$.description", is(expected.description())))
                .andReturn();
    }

    @Test
    void getExpense_InvalidExpenseId() throws Exception {
        mockMvc.perform(get(EXPENSE_API_ENDPOINT +"/" + Long.MAX_VALUE))
                .andDo(System.out::println)
                .andExpect(status().isNotAcceptable())
                .andExpect(content().string("Harcama bulunamadi."))
                .andReturn();
    }

    @Test
    void updateExpense() throws Exception {
        UpdateExpenseRequest request = ExpenseUtil.generateUpdateExpenseRequest(EMPLOYEE_ID+1);
        ExpenseDto expected = ExpenseUtil.generateExpenseDto2(EXPENSE_ID+1);

        mockMvc.perform(put(EXPENSE_API_ENDPOINT + "/" + EXPENSE_ID+1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.type", is(expected.type())))
                .andExpect(jsonPath("$.amount", is(expected.amount())))
//                .andExpect(jsonPath("$.receiptDate", is(expenseDto.receiptDate())))
                .andExpect(jsonPath("$.description", is(expected.description())))
                .andReturn();    }


    @Test
    void deleteExpense_ExpenseExistsInDatabase() throws Exception {
        mockMvc.perform(delete(EXPENSE_API_ENDPOINT + "/" + INSTANCE_WITH_THIS_ID_WILL_BE_DELETED_IN_TEST ))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void deleteExpense_ExpenseDoesNotExistsInDatabase() throws Exception {
        mockMvc.perform(delete(EXPENSE_API_ENDPOINT + "/" + Long.MAX_VALUE ))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().string("Silinecek harcama bulunamadi."))
                .andReturn();
    }
}