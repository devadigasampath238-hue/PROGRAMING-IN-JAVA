import java.util.Scanner;

interface SmartDevice {
    void control(String state);
}

class SmartFan implements SmartDevice {
    public void control(String state) {
        System.out.println("Smart Fan is turned " + state);
    }
}

class SmartLight implements SmartDevice {
    public void control(String state) {
        System.out.println("Smart Light is turned " + state);
    }
}

class SmartAC implements SmartDevice {
    public void control(String state) {
        System.out.println("Smart AC is turned " + state);
    }
}

public class SmartHomeDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                String device = sc.next();
                String state = sc.next();
                SmartDevice d = null;
                
                if (device.equals("Fan")) {
                    d = new SmartFan();
                } else if (device.equals("Light")) {
                    d = new SmartLight();
                } else if (device.equals("AC")) {
                    d = new SmartAC();
                }
                
                if (d != null) {
                    d.control(state);
                }
            }
        }
        sc.close();
    }
}
