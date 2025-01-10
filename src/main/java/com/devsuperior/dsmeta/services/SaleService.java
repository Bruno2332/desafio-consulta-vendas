package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;


import com.devsuperior.dsmeta.dto.SaleSellerDTO;

import com.devsuperior.dsmeta.dto.SaleSumaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleSellerDTO> searchReport(String name, String beginDate, String finalDate, Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate pastDate = today.minusYears(1L);
		LocalDate bDate = (beginDate != null) ? LocalDate.parse(beginDate) : pastDate;
		LocalDate fDate = (finalDate != null) ? LocalDate.parse(finalDate) : today;

		return repository.searchReport(name, bDate, fDate, pageable);
	}

	public Page<SaleSumaryDTO> searchSumary(String beginDate, String finalDate, Pageable pageable){
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate pastDate = today.minusYears(1L);
		LocalDate bDate = (beginDate != null) ? LocalDate.parse(beginDate) : pastDate;
		LocalDate fDate = (finalDate != null) ? LocalDate.parse(finalDate) : today;

		return repository.searchSumary(bDate, fDate, pageable);
	}



}
