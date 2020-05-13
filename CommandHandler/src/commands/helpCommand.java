package commands;
import SetOfVehicle.SetOfVehicles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс, осуществляющий вызов справки по доступным командам
 */
public class helpCommand extends Command {
    /**
     * Выводит справку по доступным командам
     * @param o Игнорируется
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return Справки по доступным командам
     */
    public String execute(Object o, SetOfVehicles set){
        StringBuilder sb = new StringBuilder(1000);
        sb.append("Справка по доступным командам:\n");
        try (BufferedReader br = new BufferedReader(new FileReader(
                "help.txt"))) {
            String c;
            while ((c = br.readLine()) != null) {
                sb.append(c + "\n");
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return sb.toString();
    }
    @Override
    protected String isCreative() {
        return "false";
    }

    @Override
    protected String getArgumentType() {
        return "none";
    }
}
