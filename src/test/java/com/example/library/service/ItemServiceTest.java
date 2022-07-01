package com.example.library.service;

import com.example.library.TestUtils;
import com.example.library.entity.Item;
import com.example.library.exceptions.ItemNotFoundException;
import com.example.library.repository.ItemRepository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ItemServiceTest {

    @Mock
    private ItemRepository itemsRepository;

    @InjectMocks
    private ItemService itemService = new ItemServiceImpl();

    final Item item = TestUtils.generateItem();

    @Test
    public void when_SaveItem_Expect_Success() {
        when(itemsRepository.save(item)).thenReturn(item);

        Item entity = null;
        entity = itemService.save(item);

        assertEquals(entity.getId(), item.getId());
        assertEquals(entity.getName(), item.getName());
        assertEquals(entity.getType(), item.getType());
        assertEquals(entity.isFlag(), item.isFlag());

        verify(itemsRepository, times(1)).save(item);
    }

    @Test
    public void when_SaveItem_Expect_Fail() {
        when(itemsRepository.save(any())).thenReturn(Optional.empty());

        assertThrows(ClassCastException.class, () -> {
            itemService.save(item);
        });

        verify(itemsRepository, times(1)).save(item);
    }

    @Test
    public void when_FindAllItem_Expect_Success() {
        List<Item> items = TestUtils.generateItems();

        when(itemsRepository.findAll()).thenReturn(items);

        List<Item> getItems = itemService.findAll();

        assertNotNull(getItems);
        assertEquals(2, getItems.size());

        verify(itemsRepository, times(1)).findAll();

    }

    @Test
    @Disabled
    public void when_FindAllItem_Expect_Fail() {

        when(itemsRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(ItemNotFoundException.class, () -> itemService.findAll());

        verify(itemsRepository, times(1)).findAll();
    }

    @Test
    public void when_FindByTypeAndFlag_Expect_Success() {
        List<Item> itemList = TestUtils.generateItems();
        when(itemsRepository.findByTypeAndFlag(item.getType(), item.isFlag())).thenReturn(itemList);

        List<Item> entity = itemService.findByTypeAndFlag(item.getType(), item.isFlag());

        assertNotNull(entity);
        assertEquals(itemList, entity);

        verify(itemsRepository, times(1)).findByTypeAndFlag(item.getType(), item.isFlag());
    }

    @Test
    @Disabled
    public void when_FindByTypeAndFlag_Expect_Fail() {
        when(itemsRepository.findByTypeAndFlag(anyInt(), anyBoolean())).thenReturn(Collections.emptyList());

        assertThrows(ItemNotFoundException.class, () -> itemService.findByTypeAndFlag(item.getType(), item.isFlag()));

        verify(itemsRepository, times(1)).findById(item.getId());
    }

    @Test
    public void when_FindByType_Expect_Success() throws ItemNotFoundException {
        List<Item> itemList = TestUtils.generateItems();

        when(itemsRepository.findAllByType(item.getType())).thenReturn(itemList);

        List<Item> entityList = itemService.findAllByType(item.getType());

        assertNotNull(entityList);
        assertEquals(itemList.get(0), entityList.get(0));

        verify(itemsRepository, times(1)).findAllByType(item.getType());

    }

    @Test
    @Disabled
    public void when_FindByType_Expect_Fail() {
        when(itemsRepository.findAllByType(anyInt())).thenReturn(Collections.emptyList());

        assertThrows(ItemNotFoundException.class, () -> itemService.findAllByType(item.getType()));

        verify(itemsRepository, times(1)).findAllByType(item.getType());
    }


    @Test
    public void when_FindById_Expect_Success() throws ItemNotFoundException {
        when(itemsRepository.findById(anyLong())).thenReturn(Optional.of(item));

        Item entity = itemService.findById(item.getId());

        assertEquals(entity.getId(), item.getId());
        assertEquals(entity.getName(), item.getName());
        assertEquals(entity.getType(), item.getType());
        assertEquals(entity.isFlag(), item.isFlag());

        verify(itemsRepository, times(1)).findById(item.getId());
    }

    @Test
    public void when_FindById_Expect_Fail_ItemNotFound() {
        when(itemsRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> itemService.findById(item.getId()));

        verify(itemsRepository, times(1)).findById(item.getId());
    }


    @Test
    public void when_DeleteById_Expect_Success() {
        itemService.deleteById(item.getId());
        verify(itemsRepository, times(1)).deleteById(item.getId());
    }

    @Test
    @Disabled
    public void when_DeleteById_Expect_Fail() {
        Mockito.doThrow(EntityNotFoundException.class).
                when(itemsRepository).deleteById(1L);

        assertThrows(Exception.class, () -> itemService.deleteById(item.getId()));

        verify(itemsRepository, times(1)).deleteById(item.getId());

    }

    @Test
    public void when_UpdateById_Expect_Success() throws ItemNotFoundException {
        when(itemsRepository.findById(item.getId())).thenReturn(Optional.of(item));
        item.setName("updateName");
        when(itemsRepository.save(item)).thenReturn(item);

        Item entity = itemService.findById(item.getId());

        assertEquals(item.getName(), entity.getName());

        verify(itemsRepository, times(1)).findById(item.getId());
    }

    @Test
    public void when_UpdateById_Expect_Fail() {
        when(itemsRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> itemService.findById(item.getId()));

        verify(itemsRepository, times(1)).findById(item.getId());

    }

    @Test
    public void when_FindByIdAndType_Expect_Success() {
        when(itemsRepository.findByIdAndType(item.getId(), item.getType())).thenReturn(item);

        Item entity = itemService.findByIdAndType(item.getId(), item.getType());

        assertNotNull(entity);
        assertEquals(item, entity);

        verify(itemsRepository, times(1)).findByIdAndType(item.getId(), item.getType());

    }

    @Test
    public void when_FindByTypeAndName_Expect_Success() {
        when(itemsRepository.findByTypeAndName(item.getType(), item.getName())).thenReturn(item);

        Item entity = itemService.findByTypeAndName(item.getType(), item.getName());

        assertNotNull(entity);
        assertEquals(item, entity);

        verify(itemsRepository, times(1)).findByTypeAndName(item.getType(), item.getName());

    }

    @Test
    @Disabled
    public void when_FindByTypeAndName_Expect_Fail() {
        when(itemsRepository.findByTypeAndName(anyInt(), any())).thenReturn(new Item());

        assertThrows(ItemNotFoundException.class, () -> itemService.findByTypeAndName(item.getType(), item.getName()));

        verify(itemsRepository, times(1)).findByTypeAndName(item.getType(), item.getName());

    }
}
