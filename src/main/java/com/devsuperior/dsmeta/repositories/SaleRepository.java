package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import com.devsuperior.dsmeta.dto.SaleSumaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSellerDTO(obj.id, obj.date, obj.amount, obj.seller.name) " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :beginDate AND :finalDate " +
            "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<SaleSellerDTO> searchReport(String name, LocalDate beginDate, LocalDate finalDate, Pageable pageable);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSumaryDTO(obj.name, SUM(s.amount)) " +
            "FROM Seller obj " +
            "INNER JOIN obj.sales s " +
            "WHERE s.date BETWEEN :beginDate AND :finalDate " +
            "GROUP BY obj.name")
    Page<SaleSumaryDTO> searchSumary(LocalDate beginDate, LocalDate finalDate, Pageable pageable);
}
