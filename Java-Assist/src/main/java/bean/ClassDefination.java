package bean;

import java.util.List;

/**
 * @author kangkang
 */
public class ClassDefination {
    private String className;

    private List<FileDefination> files;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<FileDefination> getFiles() {
        return files;
    }

    public void setFiles(List<FileDefination> files) {
        this.files = files;
    }
}
