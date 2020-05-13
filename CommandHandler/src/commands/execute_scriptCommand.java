package commands;

import SetOfVehicle.SetOfVehicles;
import vehicle.Coordinates;
import vehicle.Vehicle;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Класс, осуществляющий выполнение команд из файла
 * и предоставляющий метод для создания новых объектов типа com.vehicle.Vehicle
 * на основе данных из файла
 */
public class execute_scriptCommand extends Command {
    private BufferedReader reader;

    /**
     * Создаёт новый объект типа Vehicle на основе данных из файла
     *
     * @return Новый объект типа Vehicle либо null в случае, если данные для ввода
     * указаны неверно.
     */
    private Vehicle createVehicleFromFile() {
        Vehicle v = new Vehicle();
        try {
            boolean addOrNot;
            String s;
            addOrNot = v.setName(reader.readLine());
            Coordinates coordinates = new Coordinates();
            addOrNot = addOrNot && coordinates.setX(reader.readLine());
            s = reader.readLine();
            addOrNot = addOrNot && coordinates.setY(s);
            if (addOrNot) v.setCoordinates(coordinates.toString());
            addOrNot = addOrNot && v.setEnginePower(reader.readLine());
            addOrNot = addOrNot && v.setFuelConsumption(reader.readLine());
            addOrNot = addOrNot && v.setType(reader.readLine());
            addOrNot = addOrNot && v.setFuelType(reader.readLine());
            if (addOrNot)
                return v;
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    /**
     * Осуществляет выполнение команд из файла
     *
     * @param o   Имя файла с командами для выполнения
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return Результат выполнения команд или сообщение об ошибке
     */
    @Override
    public String execute(Object o, SetOfVehicles set) {

        byte[] fileBytes = (byte[]) o;
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        try {
            String filename = getFileFromBytes(fileBytes);
            reader = new BufferedReader(new FileReader(filename));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    line=line.trim();
                    if (line.equals(""))
                        continue;
                    //System.out.println(line);
                    String argument = null;
                    String[] data = line.split(" ", 2);
                    if (data.length != 1)
                        argument = data[1];
                    try {
                        Command c = (Command) Class.forName("commands." + data[0] + "Command").newInstance();
                        if (c.getClass().getDeclaredMethod("execute", Object.class, set.getClass()).isAnnotationPresent(Creative.class)) {
                            builder.append(c.execute(createVehicleFromFile(), set));
                            builder.append("\n");
                        } else {
                            builder.append(c.execute(argument, set));
                            builder.append("\n");
                        }
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | SecurityException ignored) {
                    }
                }
            } catch (IOException e) {
                return builder.toString();
            }
            reader.close();
            File file = new File(filename);
            file.delete();
            return builder.toString();
        } catch (IOException | NoClassDefFoundError ex) {
            //ex.printStackTrace();
            return "Ошибка";
        }
    }

    /*private String getFileFromBytes(byte[] bytes) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        FileChannel channel = (FileChannel)
                Files.newByteChannel(Paths.get("execute.txt"),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE);
        int j;
        for (int i=0; i<bytes.length; i++){
            j=buffer.get(i);
            System.out.print(j+" ");
            if (j=='\n') System.out.println();
        }
        channel.write(buffer, bytes.length);
        channel.close();
        return "execute.txt";
    }*/

    private String getFileFromBytes(byte[] bytes) throws IOException {
        BufferedWriter fileToSend = new BufferedWriter(new FileWriter("execute.txt"));
        for (byte b:bytes){
            fileToSend.write(b);
        }
        fileToSend.flush();
        fileToSend.close();
        return "execute.txt";
    }


    @Override
    protected String isCreative() {
        return "false";
    }

    @Override
    protected String getArgumentType() {
        return "File";
    }
}