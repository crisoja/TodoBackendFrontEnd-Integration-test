package com.example.todolist.integration;

import com.example.todolist.model.Todo;
import com.example.todolist.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TodoRepository todoRepository;

    @AfterEach
    public void clearTodos(){
        todoRepository.deleteAll();
    }

    @Test
    void should_return_all_todos_when_findTodos_is_called() throws Exception {
        todoRepository.save(new Todo(1,"This todo is for test", false));
        todoRepository.save(new Todo(2,"This todo is for test again", false));

        mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].text").value("This todo is for test"))
                .andExpect(jsonPath("$[1].text").value("This todo is for test again"));
    }

    @Test
    void should_add_todo_when_addTodo_is_called() throws Exception {
        String todo = "    {\n" +
                "        \"id\": 1,\n" +
                "        \"text\": \"This todo is for integration test for add to do\",\n" +
                "        \"done\": true\n" +
                "    }";

        mockMvc.perform(MockMvcRequestBuilders.post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(todo))
                .andExpect(jsonPath("$.text").value("This todo is for integration test for add to do"));
    }
}
