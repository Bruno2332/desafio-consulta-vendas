package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleSellerDTO;

import com.devsuperior.dsmeta.dto.SaleSumaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleSellerDTO>> getReport(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "minDate", required = false) String beginDate,
			@RequestParam(name = "maxDate", required = false) String finalDate,
			Pageable pageable) {
		Page<SaleSellerDTO> dto = service.searchReport(name, beginDate, finalDate, pageable);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SaleSumaryDTO>> getSummary(
			@RequestParam(name = "minDate", required = false) String beginDate,
			@RequestParam(name = "maxDate", required = false) String finalDate,
			Pageable pageable) {
		Page<SaleSumaryDTO> dto = service.searchSumary(beginDate, finalDate, pageable);
		return ResponseEntity.ok(dto);
	}
}
