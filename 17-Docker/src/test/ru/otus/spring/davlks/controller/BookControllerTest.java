package ru.otus.spring.davlks.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.davlks.security.config.SecurityConfiguration;
import ru.otus.spring.davlks.service.AuthorService;
import ru.otus.spring.davlks.service.BookService;
import ru.otus.spring.davlks.service.GenreService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
@ContextConfiguration(classes = {BookController.class, SecurityConfiguration.class})
@DisplayName("Класс BookController должен ")
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;

    @WithMockUser(
            username = "user",
            roles = "USER"
    )
    @Test
    @DisplayName("допускать пользователя с ролью USER до получения списка всех книг")
    public void shouldGetOkWhenUser() throws Exception {
        mockMvc.perform(get("/book/all"))
                .andExpect(status().isOk());
    }


    @WithMockUser(
            username = "user",
            roles = "USER"
    )
    @Test
    @DisplayName("не допускать пользователя с ролью USER до операций с книгами")
    public void shouldGet403WhenUser() throws Exception {
        mockMvc.perform(get("/book/save"))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(
            username = "admin",
            roles = "ADMIN"
    )
    @Test
    @DisplayName("допускать пользователя с ролью ADMIN до операций с книгами")
    public void shouldGetOkWhenAdmin() throws Exception {
        mockMvc.perform(get("/book/save"))
                .andExpect(status().isOk());
    }

    @WithMockUser(
            username = "user",
            roles = "USER"
    )
    @Test
    @DisplayName("не допускать пользователя с ролью USER до операций с комментариями")
    public void shouldGet403WhenUserGetsComments() throws Exception {
        mockMvc.perform(get("/comment/all/1"))
                .andExpect(status().is4xxClientError());
    }


    @Test
    @DisplayName("перенаправлять неаутентифицированного пользователя на страницу логина")
    public void shouldGetRedirectionWhenUserNotAuthenticated() throws Exception {
        mockMvc.perform(get("/book/all"))
                .andExpect(status().is3xxRedirection());
    }

}
