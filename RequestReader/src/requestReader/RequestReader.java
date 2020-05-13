package requestReader;

import SetProcessor.SetProcessor;
import answer.Answer;
import instruction.Instruction;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class RequestReader {

    public void readRequest(SelectionKey key, SetProcessor setProcessor) throws IOException, ClassNotFoundException {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1048576);
            buffer.clear();
            Instruction ins = new Instruction();
            //System.out.println("Создан объект instruction");
            SocketChannel socketChannel = (SocketChannel) key.channel();
            int i = socketChannel.read(buffer);
            buffer.flip();
            //System.out.println("Quantity of read bytes from the channel is: i = " + i);
            ByteArrayInputStream byteInput = new ByteArrayInputStream(buffer.array(), 0, i);
            ObjectInputStream input2 = new ObjectInputStream(byteInput);
            Instruction instruction = (instruction.Instruction) input2.readObject();
            buffer.clear();
            input2.close();
            byteInput.close();
            Answer answer = (Answer) key.attachment();
            answer.setResult(setProcessor.invokeCommand(instruction.getNameOfCommand(), instruction.getArgument()));
            //System.out.println("requestReader is finished" + "\n");
            key.interestOps(SelectionKey.OP_WRITE);
        } catch (IOException e){
            //e.printStackTrace();
            key.cancel();
            throw new IOException();
        }
    }
}
