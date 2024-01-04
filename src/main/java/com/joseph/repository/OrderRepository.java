package com.joseph.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.joseph.Model.rapportData;
import com.joseph.entity.Order;

@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("SELECT NEW com.joseph.Model.rapportData(o.dateorder, SUM(o.totalorder)) " + "FROM Order o "
			+ "WHERE o.dateorder BETWEEN :startDate AND :endDate " + "GROUP BY o.dateorder " + "ORDER BY o.dateorder")
	List<rapportData> getTotalOrdersBetweenDates(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);
}
