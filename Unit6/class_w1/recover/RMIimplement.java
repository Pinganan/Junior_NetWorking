
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

public class RMIimplement extends UnicastRemoteObject implements FeatureInterface {

    private ArrayList<UserAccount> accountArray = new ArrayList<UserAccount>();
    private ArrayList<Topic> mainTopic = new ArrayList<Topic>();

    public RMIimplement() throws java.rmi.RemoteException {
        super();
    }
    // override, throws

    @Override
    public boolean userLogin(String name, String pass) throws java.rmi.RemoteException {
        UserAccount user = new UserAccount(name, pass);
        for (UserAccount account : accountArray) {
            System.out.println(account.equals(user));
            if(account.getName().equals(name) && account.getPassword().equals(pass)) {
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
        Topic reply = new Topic(editor, content);
        topic.setReply(reply);
    }

    @Override
    public BoardGUI AllSubject(String name) throws java.rmi.RemoteException {
        BoardGUI gui = new BoardGUI(name, mainTopic);
        return gui;
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
