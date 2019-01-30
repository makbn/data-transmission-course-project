package io.github.makbn.datatransmission.common;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//Sample

/*
        comPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                    return;
                byte[] newData = new byte[comPort.bytesAvailable()];
                int numRead = comPort.readBytes(newData, newData.length);
                System.out.println("Read " + numRead + " bytes.");
            }
});*/
public class SerialCommHelper {
    private static HashMap<SerialPort, SerialPortDataListener> listeners = new HashMap<>();
    private static HashMap<SerialPort, List<SerialPortDataListener>> broadcastListeners = new HashMap<>();

    public static void init(){
        List<SerialPort> comPorts = Arrays.asList(SerialPort.getCommPorts());

        for(SerialPort port : comPorts){
            if(!port.isOpen()){
                port.openPort();

                SerialPortDataListener spdl = new SerialPortDataListener() {
                    @Override
                    public int getListeningEvents() {
                        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                    }

                    @Override
                    public void serialEvent(SerialPortEvent serialPortEvent) {
                        broadcastListeners.get(serialPortEvent.getSerialPort())
                                .forEach( l  -> l.serialEvent(serialPortEvent));
                    }
                };

                port.addDataListener(spdl);
            }
        }

    }


    public static void registerListener(SerialPort port, SerialPortDataListener listener){
        broadcastListeners.get(port).add(listener);
    }

    public static void  removeListener(SerialPort port, SerialPortDataListener listener){
        broadcastListeners.get(port).remove(listener);
    }

    public static List<SerialPort> ports(){
        return new ArrayList<>(listeners.keySet());
    }
}
