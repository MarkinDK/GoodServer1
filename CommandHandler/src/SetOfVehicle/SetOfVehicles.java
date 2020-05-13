package SetOfVehicle;

import vehicle.FuelType;
import vehicle.Vehicle;
import vehicle.VehicleType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.HashSet;

/**
 * Реализация коллекции HashSet с объектами типа com.vehicle.Vehicle
 */
@XmlRootElement(name = "Vehicles")
public class SetOfVehicles {
    /**
     * Коллекция HashSet с объектами типа com.vehicle.Vehicle
     */
    @XmlElement(name = "com.vehicle.Vehicle")
    @XmlElementWrapper(name = "Vehicles")
    private HashSet<Vehicle> vehicles = new HashSet<>();

    /**
     * Заполняет коллекцию типа com.vehicle.SetOfVehicle.SetOfVehicles данными из файла
     *
     * @param filename Имя файла с данными
     */
    public void initialize(String filename) {
        if (filename != null)
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                JAXBContext jaxbContext = JAXBContext.newInstance(this.getClass());
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                SetOfVehicles newOne = (SetOfVehicles) unmarshaller.unmarshal(reader);
                this.vehicles = newOne.vehicles;
            } catch (JAXBException | IOException e) {
                ///e.printStackTrace();
            }
    }

    /**
     * Получение информации о коллекции
     *
     * @return Строковое представление информации о коллекции
     */
    public String getInfo() {
        StringBuilder info = new StringBuilder(150);
        info.append("Тип: HashSet<com.vehicle.Vehicle>\n");
        info.append("Количество элементов: ");
        info.append(vehicles.stream().count() + "\n");
        return info.toString();
    }

    /**
     * Возвращает строковое представление коллекции
     *
     * @return Строковое представление коллекции
     */
    @Override
    public String toString() {
        return vehicles.stream().sorted()
                .reduce(
                        "",
                        (previous, next) -> previous + next.toString(),
                        (previous, next) -> previous + next);
    }

    /**
     * Добавляет заданный элемент в коллекцию
     *
     * @param v Добавляемый элемент
     * @return True в случае успешного выполнения, False в противном случае
     */
    public boolean add(Vehicle v) {
        return vehicles.add(v);
    }


    /**
     * Содержится ли элемент с заданным id в коллекции
     *
     * @param id id, по которому осуществляется проверка
     * @return Признак наличия такого элемента в коллекции
     */
    public boolean contains(Long id) {
        return getById(id) != null;
    }

    /**
     * Получить элемент из коллекции по его id
     *
     * @param id id, по которому осуществляется поиск
     * @return Ссылка на элемент, если таковой найден, null в противном случае
     */
    /*private Vehicle getById(Long id) {
        Iterator<Vehicle> iterator = vehicles.iterator();
        Vehicle v;
        while (iterator.hasNext())
            if ((v = iterator.next()).getId().equals(id))
                return v;
        return null;
    }*/
    private Vehicle getById(Long id) {
        return vehicles
                .stream()
                .filter(v -> v.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    /**
     * Удаляет элемент из коллекции по заданному id
     *
     * @param id id, по которому происходит удаление
     * @return Признак успешности выполнения
     */
    public boolean remove_by_id(Long id) {
        Vehicle v = getById(id);
        if (v != null)
            return vehicles.remove(v);
        return false;
    }

    /**
     * Очищает коллекцию
     */
    public void clear() {
        vehicles.clear();
    }

    /**
     * Записывает коллекцию в файл
     */
    public void saveToXML() {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("objects.xml"))) {
            JAXBContext jaxbContext = JAXBContext.newInstance(this.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, writer);
        } catch (JAXBException | IOException e) {
            //e.printStackTrace();
        }
    }

    /**
     * Возвращает коллекцию типа HashSet с объектами типа com.vehicle.Vehicle
     *
     * @return Коллекцию типа HashSet с объектами типа com.vehicle.Vehicle
     */
    protected HashSet<Vehicle> show() {
        return vehicles;
    }

    /**
     * Добавляет элемент, если он больше всех элементов в коллекции
     *
     * @param v Добавляемый элемент
     * @return True в случае успешного выполнения, False в противном случае
     */
    /*public boolean add_if_max(Vehicle v) {
        Iterator<Vehicle> i = vehicles.iterator();
        boolean flag = true;
        while (i.hasNext())
            if (v.compareTo(i.next()) <= 0)
                flag = false;
        if (flag)
            vehicles.add(v);
        return flag;
    }*/
    public boolean add_if_max(Vehicle v) {
        if (vehicles.stream().noneMatch(e -> e.compareTo(v) >= 0)) {
            vehicles.add(v);
            return true;
        }
        return false;
    }

    /**
     * Удаляет все элементы, превышающие заданный
     *
     * @param v Граничный элемент
     * @return True в случае успешного выполнения, False в противном случае
     */
    public boolean remove_greater(Vehicle v) {
        return vehicles.removeIf(
                vehicle -> vehicle.compareTo(v) > 0);
    }

    /**
     * Удаляет все элементы, которые меньше заданного
     *
     * @param v Граничный элемент
     * @return True в случае успешного выполнения, False в противном случае
     */
    public boolean remove_lower(Vehicle v) {
        return vehicles.removeIf(vehicle -> vehicle.compareTo(v) < 0);
    }

    /**
     * Возвращает среднее значение поля enginePower для всех элементов коллекции
     *
     * @return Среднее значение поля enginePower для всех элементов коллекции
     */
    /*public Double average_of_engine_power() {
        Iterator<Vehicle> i = vehicles.iterator();
        double power = 0;
        int quantity = vehicles.size();
        while (i.hasNext())
            try {
                power += i.next().getEnginePower();
            } catch (NullPointerException ignored) {
                quantity--;
            }
        return power / quantity;
    }*/
    public Double average_of_engine_power() {
        long quantity = vehicles.stream()
                .filter(vehicle -> {
                    try {
                        return vehicle.getEnginePower() > 0;
                    } catch (NullPointerException ignored) {
                        return false;
                    }
                })
                .count();
        return vehicles.stream()
                .filter(vehicle -> {
                    try {
                        return vehicle.getEnginePower() > 0;
                    } catch (NullPointerException ignored) {
                        return false;
                    }
                })
                .reduce(0.0,
                        (prevRes, nextEl) -> prevRes + nextEl.getEnginePower(),
                        Double::sum) / quantity;
    }

    /*private String setToString(Set<Vehicle> set) {
        StringBuilder sb = new StringBuilder(5000);
        Iterator<Vehicle> i = set.iterator();
        while (i.hasNext())
            sb.append(i.next());
        return sb.toString();
    }*/

    /**
     * Возвращает строковое представление уникальных
     * элементов для поля type
     *
     * @param type Имя поля type
     * @return Строковое представление уникальных
     * элементов для поля type
     */
    /*public String getUniqueByType(VehicleType type) {
        HashSet<Vehicle> set = new HashSet<>();
        Iterator<Vehicle> i = vehicles.iterator();
        Vehicle v;
        while (i.hasNext()) {
            if ((v = i.next()).getType().equals(type))
                set.add(v);
        }
        return setToString(set);
    }*/
    public String getUniqueByType(VehicleType type) {
        return vehicles
                .stream()
                .filter(v -> v.getType().equals(type))
                .sorted()
                .reduce("",
                        (previous, next) -> previous + next.toString(),
                        (previous, next) -> previous + next);
    }

    /**
     * Возвращает строковое представление уникальных
     * элементов для поля fuelType
     *
     * @param fuelType Имя поля fuelType
     * @return Строковое представление уникальных
     * элементов для поля fuelType
     */
    /*public String getAscendingByFuelType(FuelType fuelType) {
        TreeSet<Vehicle> set = new TreeSet<>();
        Iterator<Vehicle> i = this.vehicles.iterator();
        Vehicle v;
        while (i.hasNext()) {
            try {
                if ((v = i.next()).getFuelType().equals(fuelType))
                    set.add(v);
            } catch (NullPointerException ignored) {
            }
            ;
        }
        return setToString(set);
    }*/
    public String getAscendingByFuelType(FuelType fuelType) {
        return vehicles
                .stream()
                .filter(v -> {
                    try {
                        return v.getFuelType().equals(fuelType);
                    } catch (NullPointerException ignored) {
                        return false;
                    }
                })
                .sorted()
                .reduce("",
                        (previous, next) -> previous + next.toString(),
                        (previous, next) -> previous + next);

    }

}
