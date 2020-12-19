
import java.rmi.Remote;

public interface FeatureInterface extends Remote {
    // login
    public abstract boolean userLogin(String name, String pass) throws java.rmi.RemoteException;

    // unique user register
    public abstract boolean userRegister(String name, String pass) throws java.rmi.RemoteException;

    // create topic
    public abstract boolean topicCreate(String editor, String title, String content) throws java.rmi.RemoteException;

    // all info about one topic
    public abstract void topicSubject() throws java.rmi.RemoteException;

    // user response for topic
    public abstract void userReply(Topic topic, String editor, String content) throws java.rmi.RemoteException;

    // simple info for all topic
    public abstract void AllSubject(String name) throws java.rmi.RemoteException;

    // delete topic
    public abstract boolean topicDelete(Topic topic) throws java.rmi.RemoteException;
}
