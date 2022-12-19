package com.fmss.cokdahakolayik.reposity;

import com.fmss.cokdahakolayik.model.entity.Leave;
import com.fmss.cokdahakolayik.model.entity.Overtime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OvertimeRepository extends JpaRepository<Overtime, Long> {

    List<Overtime> findAllByEmployee_Id(Long employeeId, Pageable pageable);

//    @Query(value = "update Overtime as o set o.description =: #{#overtime.description}, o.overtimeDate =: #{#overtime.overtimeDate}, " +
//            "o.amountOvertime =: #{#overtime.amountOvertime} where o.id =: #{#overtime.id}", nativeQuery = true)
//    Overtime updateOvertimeById(@Param("overtimeId") Long overtimeId, @Param("overtime") Overtime overtime);
}
