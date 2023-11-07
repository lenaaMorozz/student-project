package com.mer.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mer.dto.AvgGradeStudentDTO;
import com.mer.service.JDBCStorageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import static java.util.Objects.isNull;

@WebServlet(name = "GetAvgGradesServlet", value = "/groups/{group_id}/students/avg-grades")
public class GetAvgGradesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        JDBCStorageService jdbcStorageService = (JDBCStorageService) session.getAttribute("jdbcStorageService");
        if (isNull(jdbcStorageService)) {
            jdbcStorageService = new JDBCStorageService();
            session.setAttribute("JDBCStorageService", jdbcStorageService);
        }

        int groupId = extractGroupId(req.getPathInfo());

        List<AvgGradeStudentDTO> dtoList = jdbcStorageService.getAvgGradeBy(groupId);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResult = objectMapper.writeValueAsString(dtoList);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().write(jsonResult);

    }

    private int extractGroupId(String pathInfo) {
        String groupIdString = pathInfo.split("/")[1];
        return Integer.parseInt(groupIdString);

    }


}
