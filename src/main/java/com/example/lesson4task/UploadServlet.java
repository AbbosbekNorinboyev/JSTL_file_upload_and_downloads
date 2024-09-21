package com.example.lesson4task;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@WebServlet(
        name = "com.example.lesson4task.UploadServlet",
        urlPatterns = "/upload"
)
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private static final Path path = Path.of("C:\\Abbos\\Spring Project\\Jakarta EE tasks\\Lesson4Task\\src\\main\\webapp\\upload");

    @Override
    public void init(ServletConfig config) throws ServletException {
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/files/upload.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part upload = req.getPart("file");
        String submittedFileName = upload.getSubmittedFileName();
        System.out.println(submittedFileName);
        String newName = UUID.randomUUID() + submittedFileName.substring(submittedFileName.lastIndexOf("."));
        InputStream inputStream = upload.getInputStream();
        Files.copy(inputStream, path.resolve(newName), StandardCopyOption.REPLACE_EXISTING);
    }
}
