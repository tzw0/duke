import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Storage {
    private String file_content;
    private String file_path;
    public Storage (String filePath){
        file_path = filePath;
        file_content = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line_X;
            while ((line_X = br.readLine()) != null) {
                file_content += line_X + "\n";
            }
        } catch (IOException e) {
            System.out.println(filePath + " file not found, creating file...");
        }
    }
    public String load () throws DukeException {
        if (file_content.isBlank()) {
            throw new DukeException("file_not_found");
        }
        return file_content;
    }
    public String get_file_path() {
        return file_path;
    }
}
