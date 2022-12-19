package com.fmss.cokdahakolayik.reposity;

import com.fmss.cokdahakolayik.model.entity.Leave;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

    List<Leave> findAllByEmployee_Id(Long employeeId, Pageable pageable);

//    @Query(value = "update Leave as l set l.type =: #{#leave.type}, l.description =: #{#leave.description}," +
//            "l.startDate =: #{#leave.startDate}, l.endDate =: #{#leave.endDate}, l.totalDay =: #{#leave.totalDay} " +
//            "where l.id =: #{#leaveId}", nativeQuery = true)
//    Leave updateLeaveById(@Param("leaveId") Long leaveId, @Param("leave") Leave leave);

}