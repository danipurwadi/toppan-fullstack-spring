package com.dani.controller;

import com.dani.service.Top3BooksService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(Top3ReadBooksController.class)
public class Top3ReadBooksControllerTest {

    @MockBean
    private Top3BooksService top3BooksService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void GetTop3ReadBooks_When_APICalled_Expect_ServiceCalled() throws Exception {
        mvc.perform(get("/getTop3ReadBooks?country_code=SG").contentType(MediaType.APPLICATION_JSON));
        verify(top3BooksService).getTop3Books("SG");
    }
}
