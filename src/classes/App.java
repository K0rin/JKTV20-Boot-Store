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
            int task = scanner.nextInt();
            scanner.nextLine();
            switch (task){
                case 0:
                    repeat = "q";
                    System.out.println("Программа закончена");
                    break;
                case 1:
                    System.out.println("Добавление продукта");
                            boots.add(addBoot());
                            keeper.saveBoots(boots);
                            break; 
                case 2:
                    printAllBoots();
                    break;
                case 3:
                    System.out.println("Добавление клиента");
                            clients.add(addClient());
                            keeper.saveClients(clients);
                            break;
                case 4:
                    System.out.println("Список клиентов");
                    printAllClients();
                    break;
                case 5:
                    System.out.println("Продажа ботинка");
                            histories.add(addHistory());
                            if (histories != null){ 
                                keeper.saveHistories(histories);
                                keeper.saveClients(clients);
//                                for (int i = 0; i < histories.size(); i++){
//                                    if(i == histories.size()){
//                                        int clientnumber = histories.get(i).getIdClient();
//                                        clients.get(clientnumber).setMoney(histories.get(i).getClient().getMoney);
//                    
//                                    }
//                                }
                            }
                            
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
        } 
    }while("y".equals(repeat));
        
    
}

    private History addMoney(){
        History client = new History();
        
        System.out.println("Список клиентов покупателей");
        for (int i = 0; i < clients.size(); i++){
            if(clients.get(i) != null){
                System.out.printf("%d. %s%n", i+1,clients.get(i).toString());
            }
        }
        
        System.out.print("Введите номер покупателя: ");        
        int clientNumber = scanner.nextInt(); scanner.nextLine();
        System.out.print("Сколько денег занести на счет? ");
        client.setClient(clients.get(clientNumber-1));
        client.getClient().setMoney(clients.get(clientNumber-1).getMoney()+scanner.nextInt());
        System.out.println("ok");
        return client;
    }
    
    private Boot addBoot() {
        Boot boot = new Boot();
        
        System.out.print("Введите название продукта: ");
        boot.setName(scanner.nextLine());
        System.out.print("Введите год изготовления: ");
        boot.setYear_done(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Введите месяц изготовления: ");
        boot.setMonth_done(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Введите день изготовления: ");
        boot.setDay_done(scanner.nextInt());
        System.out.print("Введите цену: ");
        boot.setPrice(scanner.nextInt());
        scanner.nextLine();
        List<Manufactor> manufactors = new ArrayList<>();
        
            System.out.println("Добавление производителя ");
            Manufactor manufactor = new Manufactor();
            System.out.print("Имя производителя: ");
            manufactor.setName(scanner.nextLine());
            System.out.print("Страна производителя: ");
            manufactor.setCountry(scanner.nextLine());
            System.out.print("Город производителя: ");
            manufactor.setCity(scanner.nextLine());
            manufactors.add(manufactor);
            
        
        boot.setManufactor(manufactors);
            
        return boot;
    }
    
    private void printAllBoots(){
        System.out.println("Список ботинков: ");
            for (int i = 0; i < boots.size(); i++) {
                if(boots.get(i) != null){
                    System.out.println(boots.get(i));
                }
            }
    }

    private Client addClient() {
        Client client = new Client();
        System.out.print("Введите имя покупателя: ");
        client.setFirstname(scanner.nextLine());
        System.out.print("Введите фамилию покупателя: ");
        client.setLastname(scanner.nextLine());
        System.out.print("Введите телеофон покупателя: ");
        client.setPhone(scanner.nextLine());
        System.out.print("Введите стартовый капитал пользователя: ");
        client.setMoney(scanner.nextInt());
        scanner.nextLine();
        
        return client;
    }
    
    private void printAllClients(){
        for (int i = 0; i < clients.size(); i++) {
                        if(clients.get(i) != null){
                            System.out.println(clients.get(i));
                        }
                        
                    }
    }
    
    
    private History addHistory() {
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
        System.out.println("Список товаров");
        for (int i = 0; i < boots.size(); i++){
            if(boots.get(i) != null){
                System.out.printf("%d. %s%n",i+1,boots.get(i).toString());
            }
        }
        System.out.print("Введите номер товара: ");
        int bootNumber = scanner.nextInt(); scanner.nextLine();
        System.out.println();
        System.out.println("Список клиентов покупателей");
        for (int i = 0; i < clients.size(); i++){
            if(clients.get(i) != null){
                System.out.printf("%d. %s%n", i+1,clients.get(i).toString());
            }
        }
        int historyNumber = 0;
        for (int i = 0; i < histories.size(); i++){           
                historyNumber = historyNumber + 1;
        }
        System.out.println(historyNumber);
        System.out.print("Введите номер покупателя: ");        
        int clientNumber = scanner.nextInt(); scanner.nextLine();
        if (clients.get(clientNumber-1).getMoney() > boots.get(bootNumber-1).getPrice()){
           System.out.println("Заказ принят");
           history.setBoot(boots.get(bootNumber-1));
           history.setClient(clients.get(clientNumber-1));
           Calendar c = new GregorianCalendar();
           history.setSellingDate(c.getTime());
           if(historyNumber == 0){
               history.setCapital(history.getBoot().getPrice());
           }else{
               history.setCapital(histories.get(historyNumber-1).getCapital()+history.getBoot().getPrice());
           }
           history.getClient().setMoney(clients.get(clientNumber-1).getMoney()-history.getBoot().getPrice());
           history.setStatus(1);
        }else{
           System.out.println("Заказ отменен. У пользователя не хватает денег.");
           history.setBoot(boots.get(bootNumber-1));
           history.setClient(clients.get(clientNumber-1));
           Calendar c = new GregorianCalendar();
           history.setSellingDate(c.getTime());
           history.setCapital(histories.get(historyNumber-1).getCapital());
           history.getClient().setMoney(clients.get(clientNumber-1).getMoney());
           history.setStatus(2); 
            
        }
        
        
        return history;
    }
    
    
    private void printSoldBoot() {
        System.out.println("Список проданных товаров");
            for (int i = 0; i < histories.size(); i++){
                if(histories.get(i) != null && histories.get(i).getStatus() == 1){
                    System.out.printf("%d. Товар: %s купил %s %s Сумма на счету магазина %d %n",
                            i+1,
                            histories.get(i).getBoot().getName(),
                            histories.get(i).getClient().getFirstname(),
                            histories.get(i).getClient().getLastname(),
                            histories.get(i).getCapital()
                    );
                }
            }
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
        History manufactor = new History();
        Set<Integer> setNumbersManufactors = printListManufactors();
        if(setNumbersManufactors.isEmpty()){
            return;
        }
        System.out.print("Введите номер производителя из списка: ");
        int manufactorNumber = insertNumber(setNumbersManufactors);
    }
    
    private boolean quit(){
        System.out.println("Чтобы закончить операцию нажмите \"q\"," + "для продолжения введите любой символ ");
        String quit = scanner.nextLine();
        if("q".equals(quit)) return true;
        return false;
    }

    private int insertNumber(Set<Integer> setNumbers) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
