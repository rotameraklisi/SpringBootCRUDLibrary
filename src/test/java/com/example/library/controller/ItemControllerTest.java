package com.example.library.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.example.library.TestUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.example.library.entity.Item;
import com.example.library.service.ItemService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(controllers = ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    final Item item = TestUtils.generateItem();

    private MockHttpServletResponse performCommon(String uri) throws Exception {
        return mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
    }

    @Test
    @Disabled
    public void when_FindAll_Expect_Success() throws Exception {
        List<Item> itemList = TestUtils.generateItems();

        when(itemService.findAll()).thenReturn(itemList);

        MockHttpServletResponse response = performCommon("/items/all");
        MockHttpServletResponse response1 = performCommon("/book/all");
        MockHttpServletResponse response2 = performCommon("/newspaper/all");
        MockHttpServletResponse response3 = performCommon("/documentary/all");

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response1.getStatus(), HttpStatus.OK.value());
        assertEquals(response2.getStatus(), HttpStatus.OK.value());
        assertEquals(response3.getStatus(), HttpStatus.OK.value());

        verify(itemService, times(1)).findAll();
    }

    @Test
    public void when_SaveItem_Expect_Success() throws Exception {
        when(itemService.save(item)).thenReturn(item);

        MockHttpServletResponse response = performCommon("/book/save");
        MockHttpServletResponse response1 = performCommon("/newspaper/save");
        MockHttpServletResponse response2 = performCommon("/documentary/save");

        assertEquals(response.getStatus(), HttpStatus.CREATED.value());
        assertEquals(response1.getStatus(), HttpStatus.CREATED.value());
        assertEquals(response2.getStatus(), HttpStatus.CREATED.value());
        assertNotNull(itemService.findById(1L));
    }

    @Test
    @Disabled
    public void when_GetItemTest_Expect_Succes() throws Exception {
        when(itemService.findByIdAndType(1L, 0)).thenReturn(item);

        MockHttpServletResponse response = performCommon("/book/1");
        MockHttpServletResponse response1 = performCommon("/newspaper/1");
        MockHttpServletResponse response2 = performCommon("/documentary/1");

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response1.getStatus(), HttpStatus.OK.value());
        assertEquals(response2.getStatus(), HttpStatus.OK.value());

    }

    @Test
    @Disabled
    public void when_UpdateItem_Expect_Success() throws Exception {
        when(itemService.findByIdAndType(1L, 0)).thenReturn(item);
        item.setName("com");
        when(itemService.updateById(item)).thenReturn(item);

        MockHttpServletResponse response = performCommon("/book/update/1");
        MockHttpServletResponse response1 = performCommon("/newspaper/update/1");
        MockHttpServletResponse response2 = performCommon("/documentary/update/1");

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response1.getStatus(), HttpStatus.OK.value());
        assertEquals(response2.getStatus(), HttpStatus.OK.value());
    }

    @Test
    @Disabled
    public void when_DeleteItem_Expect_Success() throws Exception {
//        itemsService.deleteById(1L);
//
//        MockHttpServletResponse response = performCommon("/book/delete/1");
//        MockHttpServletResponse response1 = performCommon("/newspaper/delete/1");
//        MockHttpServletResponse response2 = performCommon("/documentary/delete/1");
//
//        assertEquals(response.getStatus(), HttpStatus.ACCEPTED.value());
//        assertEquals(response1.getStatus(), HttpStatus.ACCEPTED.value());
//        assertEquals(response2.getStatus(), HttpStatus.ACCEPTED.value());

//        Mockito.doThrow(EntityNotFoundException.class).
//                when(itemService).deleteById(1L);
//
//        // then
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @Disabled
    public void when_FindCount_Expect_Success() throws Exception {
        List<Item> itemList = TestUtils.generateItems();

        when(itemService.findAllByType(0)).thenReturn(itemList);

        MockHttpServletResponse response = performCommon("/items/count");
        MockHttpServletResponse response1 = performCommon("/book/count");
        MockHttpServletResponse response2 = performCommon("/newspaper/count");
        MockHttpServletResponse response3 = performCommon("/documentary/count");

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response1.getStatus(), HttpStatus.OK.value());
        assertEquals(response2.getStatus(), HttpStatus.OK.value());
        assertEquals(response3.getStatus(), HttpStatus.OK.value());
    }

    @Test
    @Disabled
    public void when_FindStatus_Expect_Success() throws Exception {

        when(itemService.findByTypeAndName(0, "book1")).thenReturn(item);

        MockHttpServletResponse response = performCommon("/book/book1/status");

        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

    @Test
    @Disabled
    public void when_FindFlag_Expect_Success() throws Exception {
        List<Item> itemList = TestUtils.generateItems();

        when(itemService.findByTypeAndFlag(0, true)).thenReturn(itemList);

        MockHttpServletResponse response = performCommon("/items");

        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }
}
