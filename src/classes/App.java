/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import jktv20.boots.store.JKTV20BootsStore;
import keeper.File_Keeper;
import entity.Boot;
import entity.Client;
import entity.History;
import entity.Manufactor;
import interfaces.Keeping;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import keeper.BaseKeeper;

/**
 *
 * @author pupil
 */
public class App {
    public static boolean isBase;
    private Scanner scanner = new Scanner(System.in);
    private List<Boot> boots = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private List<History> histories = new ArrayList<>();
    private List<Manufactor> manufactors = new ArrayList<>();
    private Keeping keeper;
    
    public App() {
        if(App.isBase){
            keeper = new BaseKeeper();
        }else{
            keeper = new File_Keeper();
        }
        boots = keeper.loadBoots();
        clients = keeper.loadClients();
        histories = keeper.loadHistories();
        manufactors = keeper.loadManufactors();
    }
    
    public void run() {
        String repeat = "y";
        do{
            System.out.println("Выберите задачу:");
            System.out.println("0: Закончить задачу");
            System.out.println("1: Добавление ботинка");
            System.out.println("2: Список ботинок");
            System.out.println("3: Добавить клиента");
            System.out.println("4: Список кдиентов");
            System.out.println("5: Продажа ботинка");
            System.out.println("6: Список проданных ботинок и история нокапления");
            System.out.println("7: добавить денег клиенту");
            System.out.println("8: добавить производителя");
            System.out.println("9: список производителей");
            System.out.println("10: изменить имя производителя");
            System.out.println("11: изменить страну производителя");
            System.out.println("12: изменить город производителя");
            System.out.println("13: изменить имя у клиента");
            System.out.println("14: изменить фамилию у клиента");
            System.out.println("15: изменить телефон клиента");
            System.out.println("16: изменить назвние обуви");
            System.out.println("17: изменить цену обуви");
            System.out.println("18: доход за месяц");
            int task = scanner.nextInt();
            scanner.nextLine();
            switch (task){
                case 0:
                    repeat = "q";
                    System.out.println("Программа закончена");
                    break;
                case 1:                    
                    addBoot();
                    break; 
                case 2:
                    printAllBoots();
                    break;
                case 3:
                    addClient();
                    break;
                case 4:
                    printAllClients();
                    break;
                case 5:
                    System.out.println("Продажа ботинка");
                    addHistory();            
                    break;
                case 6:
                    printSoldBoot();
                    break;
                case 7:
                    addMoney();
                    keeper.saveClients(clients);
                    break;
                case 8:
                    addManufactor();
                    break;
                case 9:
                    printListManufactors();
                    break;
                case 10:
                    changeManufactorName();
                    break;
                case 11:
                    changeManufactorCountry();
                    break;
                case 12:
                    changeManufactorCity();
                    break;
                case 13:
                    changeClientName();
                    break;
                case 14:
                    changeClientLastName();
                    break;
                case 15:
                    changeClientPhone();
                    break;
                case 16:
                    changeBootName();
                    break;
                case 17:
                    changeBootPrice();
                    break;
                case 18:
                    getMonthSalary();
                    break;
        } 
    }while("y".equals(repeat));
        
    
}
    
    private void addBoot() {
        System.out.println("Добавление ботинка");
        if(quit()) return;
        Set<Integer> setNumbersManufactors = printListManufactors();
        if(setNumbersManufactors.isEmpty()){
            System.out.println("Добавьте хотя бы одного производителя");
            return;
        }
        System.out.println("Если есть производители в списке нажмите 1");
        if(getNumber() != 1){
            System.out.println("Добавьте производителя");
            return;
        }
        System.out.print("Сколько производителей у ботинка: ");
        Boot boot = new Boot();
        List<Manufactor> manufactorsBoot=new ArrayList<>();
        int countManufactors=getNumber();
        for (int i = 0; i < countManufactors; i++) {
            System.out.print("Введите номер производителя "+(i+1)+": ");
            int numberManufactor = insertNumber(setNumbersManufactors);
            manufactorsBoot.add(manufactors.get(numberManufactor-1));
        }
        boot.setManufactor(manufactorsBoot);
        System.out.print("Введите название обуви: ");
        boot.setName(scanner.nextLine());
        System.out.print("Введите день изготовления: ");
        boot.setDay_done(getNumber());
        System.out.print("Введите месяц изготовления: ");
        boot.setMonth_done(getNumber());
        System.out.print("Введите год изготовления: ");
        boot.setYear_done(getNumber());
        System.out.print("Введите цену: ");
        boot.setPrice(getNumber());
        boots.add(boot);
        keeper.saveBoots(boots);
    }
    
    private Set<Integer> printAllBoots(){
        System.out.println("Список ботинков: ");
        Set<Integer> setNumbersBoots = new HashSet<>();
        for (int i = 0; i < boots.size(); i++) {
            StringBuilder cbManufactors = new StringBuilder();
            for (int j = 0; j < boots.get(i).getManufactor().size(); j++) {
                cbManufactors.append(boots.get(i).getManufactor().get(j).getName())
                        .append(" ");
            }
            if(boots.get(i) != null){
                System.out.printf("%d. %s. %s Цена: %d%n"
                        ,i+1
                        ,boots.get(i).getName()
                        ,cbManufactors.toString()
                        ,boots.get(i).getPrice()
                );
                setNumbersBoots.add(i+1);
            }
        }
        return setNumbersBoots;
    }
    
    private void changeBootName(){
        if(quit()) return;
        Boot boot = new Boot();
        Set<Integer> setNumbersBoots = printAllBoots();
        if(setNumbersBoots.isEmpty()){
            return;
        }
        System.out.print("Введите номер обуви из списка: ");
        int bootNumber = insertNumber(setNumbersBoots);
        boot = boots.get(bootNumber-1);
        System.out.print("Введите правильное название обуви: ");
        boot.setName(scanner.nextLine());
        keeper.saveBoots(boots);
        System.out.println("ok");
    }
    
    private void changeBootPrice(){
        if(quit()) return;
        Boot boot = new Boot();
        Set<Integer> setNumbersBoots = printAllBoots();
        if(setNumbersBoots.isEmpty()){
            return;
        }
        System.out.print("Введите номер обуви из списка: ");
        int bootNumber = insertNumber(setNumbersBoots);
        boot = boots.get(bootNumber-1);
        System.out.print("Введите новую цену: ");
        boot.setPrice(getNumber());
        keeper.saveBoots(boots);
        System.out.println("ok");
    }

    private void addClient() {
        System.out.println("Добавление клиента");
        Client client = new Client();
        System.out.print("Введите имя покупателя: ");
        client.setFirstname(scanner.nextLine());
        System.out.print("Введите фамилию покупателя: ");
        client.setLastname(scanner.nextLine());
        System.out.print("Введите телеофон покупателя: ");
        client.setPhone(scanner.nextLine());
        System.out.print("Введите стартовый капитал пользователя: ");
        client.setMoney(getNumber());
        clients.add(client);
        keeper.saveClients(clients);
    }
    
    private void changeClientName(){
        if(quit()) return;
        Client client = new Client();
        Set<Integer> setNumbersClients = printAllClients();
        if(setNumbersClients.isEmpty()){
            return;
        }
        System.out.print("Введите номер клиента из списка: ");
        int clientNumber = insertNumber(setNumbersClients);
        client = clients.get(clientNumber-1);
        System.out.print("Введите новое имя клиента: ");
        client.setFirstname(scanner.nextLine());
        keeper.saveClients(clients);
        System.out.println("ok");
    }
    
    private void changeClientLastName(){
        if(quit()) return;
        Client client = new Client();
        Set<Integer> setNumbersClients = printAllClients();
        if(setNumbersClients.isEmpty()){
            return;
        }
        System.out.print("Введите номер клиента из списка: ");
        int clientNumber = insertNumber(setNumbersClients);
        client = clients.get(clientNumber-1);
        System.out.print("Введите новую фамилию клиента: ");
        client.setLastname(scanner.nextLine());
        keeper.saveClients(clients);
        System.out.println("ok");
    }
    
    private void changeClientPhone(){
        if(quit()) return;
        Client client = new Client();
        Set<Integer> setNumbersClients = printAllClients();
        if(setNumbersClients.isEmpty()){
            return;
        }
        System.out.print("Введите номер клиента из списка: ");
        int clientNumber = insertNumber(setNumbersClients);
        client = clients.get(clientNumber-1);
        System.out.print("Введите новый телефон клиента: ");
        client.setPhone(scanner.nextLine());
        keeper.saveClients(clients);
        System.out.println("ok");
    }
    
    private void addMoney(){
        System.out.println("Перевод денег клиенту");
        if(quit()) return;
        Client client = new Client();
        Set<Integer> setNumbersClients = printAllClients();
        if(setNumbersClients.isEmpty()){
            return;
        }
        System.out.print("Введите номер клиента из списка: ");
        int clientNumber = insertNumber(setNumbersClients);
        client = clients.get(clientNumber-1);
        System.out.print("Сколько денег занести на счет клиента: ");
        client.setMoney(client.getMoney()+getNumber());
        keeper.saveClients(clients);
        System.out.println("ok");
    }
    
    private Set<Integer> printAllClients(){
        Set<Integer> setNumbersClients = new HashSet<>();
        System.out.println("Список клиентов");
        for (int i = 0; i < clients.size(); i++) {
                        if(clients.get(i) != null){
                            System.out.printf("%d. %s%n",i+1,clients.get(i));
                            setNumbersClients.add(i+1);
                        }                        
        }
        return setNumbersClients;
    }
    
    
    private void addHistory() {
        System.out.println("Продажа ботинка");
        if(quit()) return;
         History history = new History();
        /**
         * Вывести нумерованный список товаров
         * получить от пользователя номер товара
         * вывести список клиентов покупателей
         * в history инициировать поле product объектом, который лежит
         *                          в массиве products[productNuber-1]
         * в history инициировать поле buyer объектом, который лежит
         *                          в массиве products[buyerNuber-1]
         * получить текущую дату и положить ее в поле history.givenDate
         */
        Set<Integer> setNumbersBoots = printAllBoots();
        if(setNumbersBoots.isEmpty()){
            return;
        }
        System.out.print("Введите номер ботинка из списка: ");
        int bootNumber = insertNumber(setNumbersBoots);
        
        history.setBoot(boots.get(bootNumber-1));
        
        Set<Integer> setNumbersClients = printAllClients();
        if(setNumbersClients.isEmpty()){
            return;
        }
        
        int clientNumber = insertNumber(setNumbersClients);
        history.setClient(clients.get(clientNumber-1));
        
        int historyNumber = 0;
        for (int i = 0; i < histories.size(); i++){           
                historyNumber = historyNumber + 1;
        }
        System.out.println(historyNumber);
        if (history.getClient().getMoney() > boots.get(bootNumber-1).getPrice()){
           System.out.println("Заказ принят");
           Calendar c = new GregorianCalendar();
           history.setSellingDate(c.getTime());
           if(historyNumber == 0){
               history.setCapital(history.getBoot().getPrice());
           }else{
               history.setCapital(histories.get(historyNumber-1).getCapital()+history.getBoot().getPrice());
           }
           history.getClient().setMoney(clients.get(clientNumber-1).getMoney()-history.getBoot().getPrice());
           history.setStatus(1);
           histories.add(history);
           keeper.saveHistories(histories);
           keeper.saveClients(clients);
        }else{
           System.out.println("Заказ отменен. У пользователя не хватает денег.");
           return;            
        }
    }
    
    
    private Set<Integer> printSoldBoot() {
        System.out.println("Список проданных товаров");
        histories = keeper.loadHistories();
        Set<Integer> setNumbersSoldBoots = new HashSet<>();
            for (int i = 0; i < histories.size(); i++){
                
                if(histories.get(i) != null && histories.get(i).getStatus() == 1){
                    System.out.printf("%d. Товар: %s купил %s %s Сумма на счету магазина %d %s %n",
                            i+1,
                            histories.get(i).getBoot().getName(),
                            histories.get(i).getClient().getFirstname(),
                            histories.get(i).getClient().getLastname(),
                            histories.get(i).getCapital(),
                            getSellingDate(histories.get(i))
                    );
                    
                }
                setNumbersSoldBoots.add(i+1);
            }
            return setNumbersSoldBoots;
    }
    
    private void getMonthSalary(){
        System.out.print("Введите год: ");
        int year = getNumber();
        System.out.print("Введите месяц: ");
        int month = getNumber();
        int salary = 0;
        for (int i = 0; i < histories.size(); i++) {
            if(histories.get(i).getSellingDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() == month
               && histories.get(i).getSellingDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() == year){
                salary = salary + histories.get(i).getBoot().getPrice();
            }
        }
        System.out.printf("Сумма за месяц = %d%n", salary);
    }
    
    private String getSellingDate(History history){
                LocalDate localGivenDate = history.getSellingDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();                
                return localGivenDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private void addManufactor() {
        System.out.println("Добавление производителя");
        Manufactor manufactor = new Manufactor();
        System.out.print("Название производителя: ");
        manufactor.setName(scanner.nextLine());
        System.out.print("Страна производителя: ");
        manufactor.setCountry(scanner.nextLine());
        System.out.print("Город производителя: ");
        manufactor.setCity(scanner.nextLine());
        manufactors.add(manufactor);
        keeper.saveManufactors(manufactors);
    }
    
    private Set<Integer> printListManufactors(){
       Set<Integer> setNumbersManufactors = new HashSet<>();
       System.out.println("список производителей: ");
        for (int i = 0; i < manufactors.size(); i++) {
            if(manufactors.get(i) != null){
                System.out.printf("%d. %s%n", i+1, manufactors.get(i).toString());
                setNumbersManufactors.add(i+1);
            }    
        }
        return setNumbersManufactors;
    }

    private void changeManufactorName() {
        if(quit()) return;
        Manufactor manufactor = new Manufactor();
        Set<Integer> setNumbersManufactors = printListManufactors();
        if(setNumbersManufactors.isEmpty()){
            return;
        }
        System.out.print("Введите номер производителя из списка: ");
        int manufactorNumber = insertNumber(setNumbersManufactors);
        manufactor = manufactors.get(manufactorNumber-1);
        System.out.print("Введите правильное название производителя: ");
        manufactor.setName(scanner.nextLine());
        keeper.saveManufactors(manufactors);
        System.out.println("ok");
    }
    
    private void changeManufactorCountry() {
        if(quit()) return;
        Manufactor manufactor = new Manufactor();
        Set<Integer> setNumbersManufactors = printListManufactors();
        if(setNumbersManufactors.isEmpty()){
            return;
        }
        System.out.print("Введите номер производителя из списка: ");
        int manufactorNumber = insertNumber(setNumbersManufactors);
        manufactor = manufactors.get(manufactorNumber-1);
        System.out.print("Укажите правильную страну производителя: ");
        manufactor.setCountry(scanner.nextLine());
        keeper.saveManufactors(manufactors);
        System.out.println("ok");
    }
    
    private void changeManufactorCity() {
        if(quit()) return;
        Manufactor manufactor = new Manufactor();
        Set<Integer> setNumbersManufactors = printListManufactors();
        if(setNumbersManufactors.isEmpty()){
            return;
        }
        System.out.print("Введите номер производителя из списка: ");
        int manufactorNumber = insertNumber(setNumbersManufactors);
        manufactor = manufactors.get(manufactorNumber-1);
        System.out.print("Укажите правильный город производителя: ");
        manufactor.setCity(scanner.nextLine());
        keeper.saveManufactors(manufactors);
        System.out.println("ok");
    }
    
    private boolean quit(){
        System.out.println("Чтобы закончить операцию нажмите \"q\"," + "для продолжения введите любой символ ");
        String quit = scanner.nextLine();
        if("q".equals(quit)) return true;
        return false;
    }

    private int insertNumber(Set<Integer> setNumbers) {
        do{
            int historyNumber = getNumber();
            if(setNumbers.contains(historyNumber)){
                return historyNumber;
            }
            System.out.println("Попробуй еще раз: ");
        }while(true);
    }

    private int getNumber() {
        do{
            try{
                String strNumber = scanner.nextLine();
                return Integer.parseInt(strNumber);
            }catch (Exception e){
                System.out.println("Попробуй еще раз: ");
            }
        }while(true);
    }
    
    
    
}
