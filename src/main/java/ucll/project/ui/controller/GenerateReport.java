package ucll.project.ui.controller;

import ucll.project.domain.model.GenerateCSV;
import ucll.project.domain.model.VdabStudent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GenerateReport extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> errors = new ArrayList<>();
        Timestamp vanTime = null;
        Timestamp toTime = null;
        String van = null;
        String to = null;


        try {
            van = request.getParameter("from");
            vanTime = Timestamp.valueOf(van + " 00:00:00");
        } catch (Exception exc) {
            errors.add("Geef een geldige startdatum.");
        }

        try {
            to = request.getParameter("to");
            toTime = Timestamp.valueOf(to + " 00:00:00");
        } catch (Exception exc) {
            errors.add("Geef een geldige einddatum.");
        }

        if (vanTime != null && toTime != null && vanTime.after(toTime)) errors.add("Je startdatum moet voor je einddatum liggen.");

        if (errors.size() == 0) {
            List<VdabStudent> students = service.getAantalUrenVanVdabStudentenTussenDates(vanTime, toTime);
            try {
                File file = GenerateCSV.save(students, van, to, request);
                if (file == null) {
                    errors.add("Fout in genereren van report");
                } else {
                    response.setContentType("text/csv");
                    response.setHeader("Content-Disposition", "attachment; filename=Report_"+van+"_"+to+".csv");
                    FileInputStream fileInputStream = null;
                    OutputStream outputStream = null;

                    try {
                        fileInputStream = new FileInputStream(file);
                        outputStream = response.getOutputStream();

                        int byteread;
                        while((byteread = fileInputStream.read()) != -1) {
                            outputStream.write(byteread);
                        }
                        outputStream.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            outputStream.close();
                            fileInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    request.setAttribute("confirmation", "Success, CSV correct gegenereerd");
                    return "generateReport.jsp";
                }

            } catch (Exception exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "generateReport.jsp";
    }
}
