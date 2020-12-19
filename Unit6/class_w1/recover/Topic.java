
import java.util.ArrayList;
import java.util.*;
import java.text.*;

public class Topic {
    public final SimpleDateFormat format = new SimpleDateFormat("MM.dd 'at' hh:mm");

    private String editor;
    private String title;
    private String mainContent;
    private String date;
    private ArrayList<Topic> reply = new ArrayList<Topic>();

    public Topic(String editor, String title, String content) {
        this.editor = editor;
        this.title = title;
        this.mainContent = content;
        this.date = format.format(new Date());
    }

    public Topic(String editor, String content) {
        this.editor = editor;
        this.mainContent = content;
        this.date = format.format(new Date());
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

    public void setReply(Topic reply) {
        this.reply.add(reply);
    }
}
