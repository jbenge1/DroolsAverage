package com.techgap.DroolsAverage.DroolsAverage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = System.getProperty("java.io.tmpdir")+"/";


    @GetMapping("/load")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload") 
    public String singleFileUpload(@RequestParam("file1") MultipartFile file1,
    							   @RequestParam("file2") MultipartFile file2,	
    							   @RequestParam("month") String month,
    							   @RequestParam("year")  String year,
                                   RedirectAttributes redirectAttributes) {
    	
        if (file1.isEmpty() || file2.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes1 = file1.getBytes();
            byte[] bytes2 = file2.getBytes();
            Path path1 = Paths.get(UPLOADED_FOLDER + file1.getOriginalFilename());
            Path path2 = Paths.get(UPLOADED_FOLDER + file2.getOriginalFilename());
            
            Files.write(path1, bytes1);
            Files.write(path2, bytes2);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file1.getOriginalFilename() + "' and '"+
            				file2.getOriginalFilename() +"'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/Employees?fileName1=" + file1.getOriginalFilename() +"&fileName2="+file2.getOriginalFilename()
        										+"&month=" + month + "&year=" + year;
    }

}
