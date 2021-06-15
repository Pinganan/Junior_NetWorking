package Basic;
import java.io.InputStream;
import com.fazecast.jSerialComm.*;

public class readArduino {
  public static int ByteToInt(byte[] buf) {
    int i, value = 0;
    for (i = 0; i < 1; i++) {
      value = value * 256 + (buf[i] & 0x7F + ((buf[i] & 0x80) >> 7) * 128);
    }
    return value;
  }
  
  public boolean isSoundTrigger;

  public readArduino() {
	  
	  isSoundTrigger = false; 
	  SerialPort comPort = SerialPort.getCommPort("COM3");
	  comPort.openPort();

	  comPort.addDataListener(new SerialPortDataListener() {

	      @Override
	      public int getListeningEvents() {
	        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
	      }
	
	      @Override
	      public void serialEvent(SerialPortEvent event) {
	        if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
	        	return;
	        }
	        byte[] newData = new byte[comPort.bytesAvailable()];
	        int numRead = comPort.readBytes(newData, newData.length);
	        // System.out.println("Read " + numRead + " bytes.\n" + ByteToInt(newData));
	        isSoundTrigger = true;
	      }
	   });
  }
}