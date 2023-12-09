// This class is created to show how we can create json data from pojo class and vice-versa.

package Tute6;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.TestInstance;
import org.testng.annotations.Test;

import java.util.Arrays;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SerializationAndDeserializationTest {
    @Test
    void convertPojo2Json() throws JsonProcessingException {
        String[] courses = {"c","c++","data structures", "networking"};

        Student student = new Student();
        student.setName("Jake");
        student.setLocation("New York");
        student.setPhoneNumber("0987654321");
        student.setCourses(courses);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);

        System.out.println(jsonData);
    }
    @Test
    void convertJson2Pojo() throws JsonProcessingException {
        String jsonData = "{\n" +
            "  \"name\" : \"Jake\",\n" +
            "  \"location\" : \"New York\",\n" +
            "  \"phoneNumber\" : \"0987654321\",\n" +
            "  \"courses\" : [ \"c\", \"c++\", \"data structures\", \"networking\" ]\n" +
            "}";
        ObjectMapper objectMapper = new ObjectMapper();
        Student student = objectMapper.readValue(jsonData, Student.class);
        System.out.println(student.getName());
        System.out.println(student.getLocation());
        System.out.println(student.getPhoneNumber());
        System.out.println(Arrays.toString(student.getCourses()));

    }
}







