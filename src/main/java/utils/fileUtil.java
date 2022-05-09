package utils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class fileUtil {

    public static File saveFileUpload(String nameFolder, Part part) {
        File folderUpload = new File(nameFolder);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }

        File file = new File(folderUpload, part.getSubmittedFileName());
        System.out.println(file.getAbsolutePath());
        try {
            part.write(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }
}