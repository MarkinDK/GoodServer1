package commands;
import SetOfVehicle.SetOfVehicles;


/**
 * Класс, возвращающий строковое представление коллекции
 */
public class showCommand extends Command {
    /**
     * Метод, возвращающий строковое представление коллекции
     * @param o Игнорируется
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return Строковое представление коллекции
     */
    @Override
    public String execute(Object o, SetOfVehicles set) {
        /*try {
            //Thread.sleep(5000);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }*/
        return  set.toString();
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
