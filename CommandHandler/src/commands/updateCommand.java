package commands;

import SetOfVehicle.SetOfVehicles;
//import com.Main;
import javafx.util.Pair;
import vehicle.Vehicle;


/**
 * Класс, осуществляющий обновление элемента по вводимому id,
 * передаваемому в качестве первого параметра. В случае, если элемент с таким id содержится в коллекции, удаляет его
 * и затем добавляет заданный. Если элемента с таким id  нет, добавляет заданный элемент в коллекцию.
 */
public class updateCommand extends Command {
    /**
     * Обновление элемента по вводимому id
     *
     * @param o   Строка или объект типа com.vehicle.Vehicle
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return Результат выполнения команды
     */
    @Override
    @Creative
    public String execute(Object o, SetOfVehicles set) {
        Pair pair = (Pair) o;
        String stringId = (String) pair.getKey();
        Vehicle newVehicle = (Vehicle) pair.getValue();
        if (stringId != null && stringId.length() != 0) {
            try {
                long id = Long.parseLong(stringId);
                if (id <= 0)
                    return "id должен быть положительным";
                if (set.contains(id)) {
                    set.remove_by_id(id);
                    set.add(newVehicle);
                    return "Выполнено";
                } else return "Нет элемента с таким id";
            } catch (NumberFormatException ignored) {
                return "Неверный id";
            }
        }
        return "Неверные данные";
    }


    @Override
    protected String isCreative() {
        return "true";
    }

    @Override
    protected String getArgumentType() {
        return "Pair";
    }
}
