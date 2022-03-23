package it.unibo.radarSystem22.interfaces;

public interface Interaction {
    public void forward(String msg) throws Exception;
    public String request(String msg) throws Exception;
    public String receiveMsg() throws Exception;
    public void close() throws Exception;
}
