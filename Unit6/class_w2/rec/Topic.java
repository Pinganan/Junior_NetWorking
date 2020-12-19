
import java.util.ArrayList;
import java.util.*;
import java.text.*;

public class Topic {
    public final SimpleDateFormat format = new SimpleDateFormat("MM.dd 'at' hh:mm");

    private String editor;
    private String title;
    private String mainContent;
    private String date;
    private String reply;

    public Topic(String editor, String title, String content) {
        this.editor = editor;
        this.title = title;
        this.mainContent = content;
        this.date = format.format(new Date());
    }

    public String getRep() {
        return reply;
    }

    public String getEditor() {
        return this.editor;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMainContent() {
        return this.mainContent;
    }

    public String getDate() {
        return this.date;
    }

    public void setReply(String reply) {
        this.reply += reply;
    }
}
