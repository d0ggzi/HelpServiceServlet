package ru.dggz;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheeringServletTest {

    @InjectMocks
    private CheeringServlet servlet;
    @Mock
    private CheeringManager manager;

    HttpServletRequest req;
    HttpServletResponse resp;
    StringWriter responseWriter;

    @BeforeEach
    void setup() throws IOException {
        manager = mock(CheeringManager.class);
        servlet = new CheeringServlet(manager);

        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);

        responseWriter = new StringWriter();

        PrintWriter writer = new PrintWriter(responseWriter);
        when(resp.getWriter()).thenReturn(writer);
    }


    @Test
    void HandleDoGet() throws ServletException, IOException {
        String test_phrase = "тест";
        doReturn(test_phrase).when(manager).getRandomPhrase();

        servlet.doGet(req, resp);

        verify(manager, times(1)).getRandomPhrase();
        Assertions.assertEquals(test_phrase+"\n", responseWriter.toString());
    }

    @Test
    void HandleDoPost() throws ServletException, IOException {
        String test_phrase = "тест";
        String test_response = "Successfully added: тест";

        doReturn(test_phrase).when(req).getParameter("phrase");
        doReturn(test_response).when(manager).addPhrase(test_phrase);

        servlet.doPost(req, resp);

        verify(manager, times(1)).addPhrase(test_phrase);
        Assertions.assertEquals(test_response+"\n", responseWriter.toString());
    }
}
