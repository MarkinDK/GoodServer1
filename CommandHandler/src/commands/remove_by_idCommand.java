package commands;
import SetOfVehicle.SetOfVehicles;

/**
 * Класс, объект которого осуществляет удаление элемента по его id
 */
public class remove_by_idCommand extends Command {
    /**
     * Удаление элемента по его id
     * @param o Строка с id элемента
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return Результат выполнения команды
     */
    @Override
    public String execute(Object o, SetOfVehicles set) {
        String arg = (String) o;
        if (arg != null && arg.length() != 0) {
            try {
                long id = Long.parseLong(arg);
                if (id <= 0)
                    return "id должен быть положительным";
                if (set.contains(id)) {
                    set.remove_by_id(id);
                    return "Успешно";
                }
                else return "Нет элемента с таким id";
            } catch (NumberFormatException ignored) {
            }
        }
        return "Введите заново";
    }
    @Override
    protected String isCreative() {
        return "false";
    }

    @Override
    protected String getArgumentType() {
        return "String";
    }
}

