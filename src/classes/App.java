/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import File_Keeper.File_Keeper;
import entity.Boot;
import entity.Client;
import entity.History;
import entity.Manufactor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pupil
 */
public class App {

    Scanner scanner = new Scanner(System.in);
    List<Boot> boots = new ArrayList<>();
    List<Client> clients = new ArrayList<>();
    List<History> histories = new ArrayList<>();
    File_Keeper file_keeper = new File_Keeper();
    
    public App() {
        File_Keeper file_keeper = new File_Keeper();
        boots = file_keeper.loadBoots();
        clients = file_keeper.loadClients();
        histories = file_keeper.loadHistories();
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
            int task = scanner.nextInt();
            scanner.nextLine();
            switch (task){
                case 0:
                    repeat = "q";
                    System.out.println("Программа закончена");
                    break;
                case 1:
                    System.out.println("Добавление книги");
                            boots.add(addBoot());
                            file_keeper.saveBoots(boots);
                            break; 
                case 2:
                    printAllBoots();
                    break;
                case 3:
                    System.out.println("Добавление читателя");
                            clients.add(addClient());
                            file_keeper.saveClients(clients);
                            break;
                case 4:
                    System.out.println("Список читателей");
                    printAllClients();
                    break;
                case 5:
                    System.out.println("Продажа ботинка");
                            histories.add(addHistory());
                            if (histories != null){ 
                                file_keeper.saveHistories(histories);
                            }
                            break;
                case 6:
                    printSoldBoot();
                    break;
        } 
    }while("y".equals(repeat));
        
    
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
        scanner.nextLine();
        System.out.print("Сколько производителей у данного продукта: ");
        
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
        System.out.println("Список книг: ");
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
        System.out.print("Введите номер покупателя: ");        
        int clientNumber = scanner.nextInt(); scanner.nextLine();
        if (clients.get(clientNumber-1).getMoney() > boots.get(bootNumber-1).getPrice()){
           history.setBoot(boots.get(bootNumber-1));
           history.setClient(clients.get(clientNumber-1));
           Calendar c = new GregorianCalendar();
           history.setSellingDate(c.getTime());
           history.setCapital(history.getCapital()+history.getBoot().getPrice());
           history.getClient().setMoney(clients.get(clientNumber-1).getMoney()-history.getBoot().getPrice());
        }
        
        
        return history;
    }

    private void printSoldBoot() {
        System.out.println("Список проданных товаров");
            for (int i = 0; i < histories.size(); i++){
                if(histories.get(i) != null){
                    System.out.printf("%d. Товар: %s купил %s %s%n",
                            i+1,
                            histories.get(i).getBoot().getName(),
                            histories.get(i).getClient().getFirstname(),
                            histories.get(i).getClient().getLastname()
                    );
                }
            }
    }
    
}
