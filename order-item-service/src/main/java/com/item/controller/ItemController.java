package com.item.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.item.dto.ItemResponse;
import com.item.dto.ItemDTO;
import com.item.exception.ItemNotFoundException;
import com.item.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {
	@Autowired
	private ItemService itemService;
	static final String SUCCESS = "Success";

	@PostMapping
	public ResponseEntity<ItemResponse> createItem(@RequestBody @Valid ItemDTO item) {
		itemService.createItem(item);
		ItemResponse response = new ItemResponse(HttpStatus.OK.value(), SUCCESS);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/{productCode}")
	public ResponseEntity<ItemResponse> updateItem(@PathVariable String productCode,
			@RequestBody @Valid ItemDTO item) throws ItemNotFoundException {
		itemService.updateItem(productCode, item);
		ItemResponse response = new ItemResponse(HttpStatus.OK.value(), SUCCESS);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<ItemDTO>> getItems() throws ItemNotFoundException {
		List<ItemDTO> items = itemService.getItems();
		return new ResponseEntity<>(items, HttpStatus.OK);
	}

	@GetMapping("/{productCode}")
	public ResponseEntity<ItemDTO> getItem(@PathVariable String productCode) throws ItemNotFoundException {
		ItemDTO item = itemService.getItem(productCode);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	@DeleteMapping("/{productCode}")
	public ResponseEntity<ItemResponse> deleteItem(@PathVariable String productCode) throws ItemNotFoundException {
		itemService.deleteItem(productCode);
		ItemResponse response = new ItemResponse(HttpStatus.OK.value(), SUCCESS);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
