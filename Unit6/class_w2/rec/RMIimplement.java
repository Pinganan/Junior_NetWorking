
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

public class RMIimplement extends UnicastRemoteObject implements FeatureInterface {

    public ArrayList<UserAccount> accountArray = new ArrayList<UserAccount>();
    public ArrayList<Topic> mainTopic = new ArrayList<Topic>();

    Topic one = new Topic("editor1", "title1", "content1");
    Topic two = new Topic("editor2", "title2", "content2");
    Topic three = new Topic("editor3", "title3", "content3");

    public RMIimplement() throws java.rmi.RemoteException {
        super();
    }
    // override, throws

    @Override
    public boolean userLogin(String name, String pass) throws java.rmi.RemoteException {
        UserAccount user = new UserAccount(name, pass);
        for (UserAccount account : accountArray) {
            System.out.println(account.equals(user));
            if (account.getName().equals(name) && account.getPassword().equals(pass)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean userRegister(String name, String pass) throws java.rmi.RemoteException {
        int i = 0;
        UserAccount user = new UserAccount(name, pass);
        System.out.println(name + "\t" + pass);
        if (user.getName().matches("[DMPT]{1}[0-9]{7}")) {
            for (i = 0; i < accountArray.size(); i++) {
                if (accountArray.get(i).getName().equals(user.getName())) {
                    return false;
                }
            }
            accountArray.add(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean topicCreate(String editor, String title, String content) throws java.rmi.RemoteException {
        Topic t = new Topic(editor, title, content);
        if (mainTopic.contains(t) == false) {
            mainTopic.add(t);
            return true;
        }
        return false;
    }

    @Override
    public void topicSubject() throws java.rmi.RemoteException {

    }

    @Override
    public void userReply(Topic topic, String editor, String content) throws java.rmi.RemoteException {
        
    }

    @Override
    public void AllSubject(String name) throws java.rmi.RemoteException {
        mainTopic.add(one);
        mainTopic.add(two);
        mainTopic.add(three);

        BoardGUI board = new BoardGUI(name, mainTopic);
        while (true) {
            System.out.println(board.posterNum);
            if (board.posterNum == 1) {
                board.setVisible(false);
                Create c = new Create();
                while (!c.checkFlag) {
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                    }
                }
                this.topicCreate(name, c.getTitle(), c.getContent());
                board = new BoardGUI(name, mainTopic);
                c.setVisible(false);
            } else if (board.posterNum == 2) {
                for (Topic ts : mainTopic) {
                    if (ts.getTitle().equals(board.getDeleted()) && name.equals(ts.getEditor())) {
                        mainTopic.remove(ts);
                        break;
                    }
                }
                board.setVisible(false);
                board = new BoardGUI(name, mainTopic);
                board.setVisible(true);
            } else if (board.posterNum == 3) {
                for (Topic st : mainTopic) {
                    if (st.getTitle().equals(board.getTopic())) {
                        ALLTopic ss = new ALLTopic();
                        ss.setResponse("Editor : " + st.getEditor() + "\n");
                        ss.setResponse("Time at " + st.getDate() + "\n");
                        ss.setResponse("Title : " + st.getTitle() + "\n");
                        ss.setResponse("Content \n" + st.getMainContent() + "\n");
                        ss.setResponse("-------------------------------------------\n");
                        ss.setResponse("-------------------------------------------\n");
                        ss.setResponse("-------------------------------------------\n");
                        ss.setResponse(st.getRep());
                        while(ss.state) {
                            if(ss.resState) {
                                ss.resState = false;
                                st.setReply(ss.getRes() + "\n");
                                ss.setVisible(false);
                                ss = new ALLTopic();
                                ss.setResponse("Editor : " + st.getEditor() + "\n");
                                ss.setResponse("Time at " + st.getDate() + "\n");
                                ss.setResponse("Title : " + st.getTitle() + "\n");
                                ss.setResponse("Content \n" + st.getMainContent() + "\n");
                                ss.setResponse("-------------------------------------------\n");
                                ss.setResponse("-------------------------------------------\n");
                                ss.setResponse("-------------------------------------------\n");
                                ss.setResponse(st.getRep());
                            }
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                        }
                        ss.setVisible(false);
                        break;
                    }
                }
            }
            board.posterNum = 0;
            try {
                Thread.sleep(500);
            } catch (Exception e) {

            }
        }
    }

    @Override
    public boolean topicDelete(Topic topic) throws java.rmi.RemoteException {
        if (mainTopic.contains(topic)) {
            mainTopic.remove(topic);
            return true;
        }
        return false;
    }
}
