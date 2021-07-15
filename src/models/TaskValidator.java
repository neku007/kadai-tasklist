package models;

import java.util.ArrayList;
import java.util.List;

public class TaskValidator {
    public static List<String> validate(Tasks m) {
        List<String> errors = new ArrayList<String>();

        String content_error = validateContent(m.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }

        return errors;
    }

    private static String validateContent(String content) {
        if(content == null || content.equals("")) {
            return "タスクを入力してください。";
        }

        return "";
    }
}
