package com.item.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.item.dto.ItemDTO;
import com.item.entity.Item;
import com.item.exception.ItemNotFoundException;
import com.item.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public void createItem(ItemDTO item) {
		itemRepository.saveAndFlush(itemAssembler(item));

	}

	@Override
	public void updateItem(String productCode, ItemDTO item) throws ItemNotFoundException {
		Item orderItem = itemRepository.findByProductCode(productCode);
		if (orderItem == null) {
			throw new ItemNotFoundException("Invalid product code " + productCode);
		}
		orderItem.setProductCode(productCode);
		orderItem.setProductName(item.getProductName());
		orderItem.setQuantity(item.getQuantity());
		orderItem.setUnitPrice(item.getUnitPrice());
		itemRepository.saveAndFlush(orderItem);
	}

	@Override
	public List<ItemDTO> getItems() throws ItemNotFoundException {
		List<Item> items = itemRepository.findAll();
		if (items.isEmpty()) {
			throw new ItemNotFoundException("No item found");
		}
		return itemListAssembler(items);
	}

	@Override
	public ItemDTO getItem(String productCode) throws ItemNotFoundException {
		Item item = itemRepository.findByProductCode(productCode);
		if (item == null) {
			throw new ItemNotFoundException("Invalid product code " + productCode);
		}
		return itemAssemblerDTO(item);
	}

	@Override
	public void deleteItem(String productCode) throws ItemNotFoundException {
		Item item = itemRepository.findByProductCode(productCode);
		if (item == null) {
			throw new ItemNotFoundException("Invalid product code " + productCode);
		}
		itemRepository.deleteById(productCode);

	}

	private Item itemAssembler(ItemDTO item) {
		return new Item(item.getProductCode(), item.getProductName(), item.getQuantity(), item.getUnitPrice());
	}

	private ItemDTO itemAssemblerDTO(Item item) {
		return new ItemDTO(item.getProductCode(), item.getProductName(), item.getQuantity(), item.getUnitPrice());
	}

	private List<ItemDTO> itemListAssembler(List<Item> items) {
		List<ItemDTO> list = new ArrayList<>();
		for (Item item : items) {
			list.add(new ItemDTO(item.getProductCode(), item.getProductName(), item.getQuantity(),
					item.getUnitPrice()));
		}
		return list;
	}

}
