package ucll.project.domain.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GenerateCSV {
    public static File save(List<VdabStudent> list, String van, String tot, HttpServletRequest request) throws ModelException, IOException {
        String path = request.getServletContext().getRealPath("/WEB-INF/");
        File file = new File(path+"Report_"+van+"_"+tot+".csv");

        try{
            FileWriter fw = new FileWriter(file);
            fw.write("Userid; Reeks; Aanwezig; Totaal\n");
            for (VdabStudent student : list){
                char splitser = ';';
                String string = student.getUserId() + splitser;
                string += student.getReeks() + splitser;
                string += student.getAanwezig() + splitser;
                string += student.getTotaal() + "\n";
                fw.write(string);
            }
            fw.close();
            return file;
        }
        catch (Exception e){
            throw new ModelException(e.getMessage());
        }
    }
}
