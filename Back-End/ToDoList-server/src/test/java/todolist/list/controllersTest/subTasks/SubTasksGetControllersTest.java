package todolist.list.controllersTest.subTasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@WebMvcTest(SubTasksGetControllersTest.class)
class SubTasksGetControllersTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    SubTasksGetControllersTest subTasksGetControllersTest;
}