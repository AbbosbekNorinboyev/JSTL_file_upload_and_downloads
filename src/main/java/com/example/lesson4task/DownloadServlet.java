package com.example.lesson4task;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(name = "com.example.lesson4task.DownloadServlet",
        value = "/download")
public class DownloadServlet extends HttpServlet {
    private static final Path path = Path.of("C:\\Abbos\\Spring Project\\Jakarta EE tasks\\Lesson4Task\\src\\main\\webapp\\upload");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/files/download.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("file");
        // Resolve the file path
        Path filePath = path.resolve(fileName);
        if (Files.exists(filePath)) {
            // Set the response headers for downloading the file
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

            // Write the file content to the response output stream
            try (InputStream inputStream = new FileInputStream(filePath.toFile());
                 OutputStream outputStream = response.getOutputStream()) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println("File not found: " + fileName);
        }
    }
}
