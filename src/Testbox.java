import com.phidgets.AnalogPhidget;
import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;
import com.phidgets.event.InputChangeEvent;
import com.phidgets.event.InputChangeListener;

import java.io.IOException;




public class Testbox {


    public static void out(String s){
        System.out.println(s);
    }
    public static void pout(String s){
        System.out.print("                                             \r");
        System.out.print(s+"\r");
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        ErrorListener ErrorListener = null;
        
        try {
            final InterfaceKitPhidget ifk = new InterfaceKitPhidget();

            ifk.openAny();
            out("Waiting for the Phidget Analog to be attached...");
            ifk.waitForAttachment();

            ifk.addErrorListener(new ErrorListener() {

            public void error(ErrorEvent ee) {
                    out("\n--->Error: " + ee.getException());
                }
            });

            out("Phidget Information");
            out("====================================");
            out("Library Version: "+ ifk.getLibraryVersion());
            out("Version: " + ifk.getDeviceVersion());
            out("Name: " + ifk.getDeviceName());
            out("Serial #: " + ifk.getSerialNumber());
            out("# Analog Inputs: " + ifk.getInputCount());

            System.out.print("Setting all enabled states to true\n");

            Thread.sleep(100);

            //keep displaying input;
            ifk.addInputChangeListener(new InputChangeListener() {
                @Override
                public void inputChanged(InputChangeEvent arg0) {
                    try {
                        out("input changed:"+arg0.getIndex()+" "+ifk.getSensorRawValue(0));
                    } catch (PhidgetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
            
            while(true) {
                Thread.sleep(200);
                pout("input value:"+ifk.getSensorRawValue(0));
            }
            
//            ifk.removeErrorListener(ErrorListener);
//            ifk.close();
        } catch (PhidgetException ex) {
            out("Exception: " + "Phidget Error: " + ex.getDescription());
        }
    }
}
