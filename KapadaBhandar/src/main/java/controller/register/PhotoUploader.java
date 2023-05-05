package controller.register;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javax.servlet.http.Part;

public class PhotoUploader {
	String uploadPath = "C:/Users/Acer/OneDrive - London Metropolitan University/Second Year/WebDevlopmentWithJava/KapadaBhandar/src/main/webapp/images";
	String deletePath = "C:/Users/Acer/OneDrive - London Metropolitan University/Second Year/WebDevlopmentWithJava/KapadaBhandar/src/main/webapp/";
	public void uploadPhoto(Part filePart) {
     // Get the uploaded image file
        String fileName = getFileName(filePart);
        String uploadPath = "C:/Users/Acer/OneDrive - London Metropolitan University/Second Year/WebDevlopmentWithJava/KapadaBhandar/src/main/webapp/images";        
        InputStream fileContent;
		try {
			fileContent = filePart.getInputStream();
			 // Save the uploaded image file to the upload directory
	        Files.copy(fileContent, new File(uploadPath + File.separator + fileName).toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public void removePhoto(String relativePath) {
        File file = new File(deletePath, relativePath);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }
	public String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}