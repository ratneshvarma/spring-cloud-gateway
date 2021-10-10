package com.item.service;
import java.util.List;

import com.item.dto.ItemDTO;
import com.item.exception.ItemNotFoundException;


public interface ItemService {
	public void createItem(ItemDTO item);
	public void updateItem(String productCode, ItemDTO item) throws ItemNotFoundException;
	public List<ItemDTO> getItems() throws ItemNotFoundException;
	public ItemDTO getItem(String productCode) throws ItemNotFoundException;
	public void deleteItem(String productCode) throws ItemNotFoundException;

}
