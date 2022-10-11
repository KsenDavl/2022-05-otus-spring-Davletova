package ru.otus.spring.davlks.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.davlks.security.config.SecurityConfiguration;
import ru.otus.spring.davlks.service.AuthorService;
import ru.otus.spring.davlks.service.BookService;
import ru.otus.spring.davlks.service.GenreService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
@ContextConfiguration(classes = {BookController.class, SecurityConfiguration.class})
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;

//    @WithMockUser(
//            username = "admin"
//    )
//    @Test
//    public void shouldGetOk() throws Exception {
//        mockMvc.perform(get("/book/all"))
//                .andExpect(status().isOk());
//    }

    //todo negative tests
}
