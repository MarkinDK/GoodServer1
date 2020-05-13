package sender;

import answer.Answer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class AnswerSender {
    public void sendAnswer(SelectionKey key) throws IOException {
        try {
            Answer answer = (Answer) key.attachment();
            SocketChannel channel = (SocketChannel) key.channel();
            //System.out.println("Value of an answer is: " + answer.getResult());
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(output);
            outputStream.writeObject(answer);
            outputStream.flush();
            output.flush();
            channel.write(ByteBuffer.wrap(output.toByteArray()));
            key.interestOps(SelectionKey.OP_READ);
            outputStream.close();
            output.close();
            //System.out.println("answerSender is finished" + "\n");
        } catch (IOException e) {
            //e.printStackTrace();
            key.cancel();
            throw new IOException();
        }
    }
}
