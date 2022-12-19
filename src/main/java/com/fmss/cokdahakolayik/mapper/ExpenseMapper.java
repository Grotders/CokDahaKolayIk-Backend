package com.fmss.cokdahakolayik.mapper;

import com.fmss.cokdahakolayik.client.dto.request.CreateExpenseRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateExpenseRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.response.ExpenseDto;
import com.fmss.cokdahakolayik.model.entity.Expense;
import com.fmss.cokdahakolayik.model.entity.Leave;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(implementationName = "ExpenseMapperImpl", componentModel = "spring", imports = {Expense.class})
public interface ExpenseMapper {

    @Mapping(target = "employee.id", source = "employeeId")
    Expense toExpenseFromCreateExpenseRequest(CreateExpenseRequest expenseRequest);

    @Mapping(target = "id", source = "id")
    ExpenseDto toExpenseDto(Expense expense);

    Expense updateExpense(@MappingTarget Expense expense, UpdateExpenseRequest expenseRequest);
}
