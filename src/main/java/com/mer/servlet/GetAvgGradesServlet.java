package com.mer.servlet;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mer.dto.AvgGradeStudentDTO;
import com.mer.dto.ChangeGradeByNameAndGroupDTO;
import com.mer.service.JDBCStorageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static java.util.Objects.isNull;

@WebServlet(name = "GetAvgGradesServlet", urlPatterns = {"/groups/avg-grades/*"})
public class GetAvgGradesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JDBCStorageService jdbcStorageService = setJDBCStorageService(req.getSession());

        int groupNum = extractGroupId(req.getPathInfo());

        List<AvgGradeStudentDTO> dtoList = jdbcStorageService.getAvgGradeBy(groupNum);
        if (dtoList.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("Студенты не найдены");

        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResult = objectMapper.writeValueAsString(dtoList);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            resp.getWriter().write(jsonResult);
        }

    }

    private int extractGroupId(String pathInfo) {
        String groupIdString = pathInfo.split("/")[1];
        return Integer.parseInt(groupIdString);

    }

    private JDBCStorageService setJDBCStorageService(HttpSession session) {
        JDBCStorageService jdbcStorageService = (JDBCStorageService) session.getAttribute("jdbcStorageService");
        if (isNull(jdbcStorageService)) {
            jdbcStorageService = new JDBCStorageService();
            session.setAttribute("jdbcStorageService", jdbcStorageService);
        }
        return (JDBCStorageService) session.getAttribute("jdbcStorageService");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JDBCStorageService jdbcStorageService = setJDBCStorageService(req.getSession());

        BufferedReader reader = req.getReader();
        StringBuilder jsonRequestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonRequestBody.append(line);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        int grade;
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonRequestBody.toString());
            grade = jsonNode.get("grade").asInt();

            ChangeGradeByNameAndGroupDTO studentDTO = ChangeGradeByNameAndGroupDTO.builder()
                    .firstName(req.getParameter("first-name"))
                    .lastName(req.getParameter("last-name"))
                    .subject(req.getParameter("subject"))
                    .group(extractGroupId(req.getPathInfo()))
                    .grade(grade)
                    .build();

            try {
                jdbcStorageService.updateGradeByNameAndGroup(studentDTO);

                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("Оценка успешно обновлена");
            } catch (RuntimeException e) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("Ученик с указанными ФИ, класс или предмет не найдены");
            }

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Неверный формат JSON-тела");
        }

    }
}
