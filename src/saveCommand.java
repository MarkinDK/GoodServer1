import SetOfVehicle.SetOfVehicles;
import commands.Command;
//Исключительно чтобы не потерять этот класс

/**
 * Класс, объект которого сохраняет коллекцию в файл xml
 */
public class saveCommand extends Command {

    /**
     * Сохраняет коллекцию в файл xml
     * @param o игнорируется
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return Результат выполнения команды
     */
    @Override
    public String execute(Object o, SetOfVehicles set) {
        set.saveToXML();
        return "Сохранено";
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
