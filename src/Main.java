import shop.models.Shop;
import shop.models.Users;
import shop.service.crud.CrudService;
import shop.service.crud.impl.CrudServiceImpl;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        CrudService crudService = new CrudServiceImpl();

        while (true){
            System.out.println("Выберите сервис");
            System.out.println("1.Магазины");
            System.out.println("2.Категории");
            System.out.println("3.Продукты");
            System.out.println("4.Работники");
            System.out.println("5.Чеки");
            System.out.println("6.Магазин/категория");
            System.out.println("7.Чек/продукт");


            switch (scn.nextInt()){
                case 1:
                    crudService.getShopControl();
                    break;
                case 2:
                    crudService.getCategoryControl();
                    break;
                case 3:
                    crudService.getProductControl();
                    break;
                case 4:
                    crudService.getUserControl();
                    break;
                case 5:
                    crudService.getCheckControl();
                    break;
                case 6:
                    crudService.getShopCategoriesControl();
                    break;
                case 7:
                    crudService.getCheckProductControl();
                    break;
            }
            System.out.println("Продолжить?");
            System.out.println("1.Да");
            System.out.println("2.Нет");

            if (scn.nextInt() == 2){
                break;
            }

        }


    }
}