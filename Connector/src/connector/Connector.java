package connector;

import SetProcessor.SetProcessor;
import answer.Answer;
import requestReader.RequestReader;
import sender.AnswerSender;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Connector implements Runnable {
    private ServerSocketChannel serverSocketChannel = null;
    private Selector selector = null;
    private SocketChannel socketChannel = null;
    private RequestReader requestReader = new RequestReader();
    private AnswerSender answerSender = new AnswerSender();
    private int port;
    private SetProcessor set;

    public Connector(int port, SetProcessor set) {
        this.port = port;
        this.set = set;
        new Thread(this, "Connector thread").start();
    }

    private void initialize() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        //System.out.println(port);
        //int port = 5000;
        serverSocketChannel
                .socket()
                .bind(new InetSocketAddress("localhost", port));
    }

    public void startConnection(SetProcessor setProcessor) throws IOException, ClassNotFoundException {
        initialize();
        SelectionKey acceptKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (acceptKey.selector().select() > 0) {

            Iterator<SelectionKey> i = selector.selectedKeys().iterator();
            //System.out.println("Amount of selected keys: " + selector.selectedKeys().size());

            while (i.hasNext()) {
                try {
                    SelectionKey key = i.next();
                    i.remove();
                    //System.out.println("isAcceptable: " + key.isAcceptable());
                    //System.out.println("isReadable: " + key.isReadable());
                    //System.out.println("isWritable: " + key.isWritable());
                    //System.out.println(key);
                    if (key.isAcceptable()) {
                        socketChannel = ((ServerSocketChannel) key.channel()).accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ, new Answer());
                        System.out.println("Connection ACCEPTED\n");
                    }
                    if (key.isReadable()) {
                        //System.out.println("Starts to read");
                        requestReader.readRequest(key, setProcessor);
                    }
                    if (key.isWritable()) {
                        //System.out.println("\nStarts to write");
                        answerSender.sendAnswer(key);
                    }
                } catch (IOException e) {
                    System.out.println("\nDisconnected");
                }
            }
        }
    }

    public void disconnect() {
        try {
            serverSocketChannel.close();
            Iterator<SelectionKey> i = selector.selectedKeys().iterator();
            while (i.hasNext()) {
                SelectionKey key = i.next();
                i.remove();
                key.channel().close();
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            startConnection(set);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
