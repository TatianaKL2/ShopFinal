package shop.service.crud.impl;

import shop.models.*;
import shop.service.*;
import shop.service.crud.CrudService;
import shop.service.impl.*;

import java.util.Scanner;

public class CrudServiceImpl implements CrudService {
    ShopService shopService = new ShopServiceImpl();
    CategoriesService categoriesService = new CategoriesServiceImpl();
    ProductService productService = new ProductServiceImpl();
    UsersService usersService = new UsersServiceImpl();
    CheckService checkService = new CheckServiceImpl();
    CheckProductService checkProductService = new CheckProductServiceImpl();
    ShopCategoriesService shopCategoriesService = new ShopCategoriesServiceImpl();

    Scanner scn = new Scanner(System.in);

    @Override
    public void getShopControl() {
        System.out.println("Выберите действие");
        System.out.println("1.Добавить магазин");
        System.out.println("2.Вывести весь список магазинов");
        System.out.println("3.Вывести магазин по ID");
        System.out.println("4.Редактировать данные магазина по его ID");
        System.out.println("5.Удалить магазин по ID");

        switch (scn.nextInt()) {
            case 1 -> {
                System.out.println("Введите название магазина");
                shopService.save(scn.next());
            }
            case 2 -> {
                System.out.println(shopService.findAll());
            }
            case 3 -> {
                System.out.println("Введите ID магазина");
                System.out.println(shopService.findById(scn.nextLong()));
            }
            case 4 -> {
                Shop updatedShop = new Shop();
                System.out.println("Введите ID магазина");
                updatedShop.setId(scn.nextLong());
                System.out.println("Введите обновленное название магазина");
                scn.nextLine();
                updatedShop.setName(scn.nextLine());
                System.out.println("Введите статус магазина true/false");
                updatedShop.setActive(scn.nextBoolean());
                shopService.update(updatedShop);
            }
            case 5 -> {
                System.out.println("Введите ID для удаления");
                shopService.delete(scn.nextLong());
                System.out.println("Магазин удален");
            }
        }
    }

    @Override
    public void getCategoryControl() {
        System.out.println("Выберите действие");
        System.out.println("1.Добавить категорию");
        System.out.println("2.Вывести весь список категорий");
        System.out.println("3.Вывести категорию по ID");
        System.out.println("4.Редактировать данные категории по его ID");
        System.out.println("5.Удалить категорию по ID");

        switch (scn.nextInt()){
            case 1:
                System.out.println("Введите название категории");
                categoriesService.save(scn.next());
                break;
            case 2:
                System.out.println(categoriesService.findAll());
                break;
            case 3:
                System.out.println("Введите ID категории");
                System.out.println(categoriesService.findById(scn.nextLong()));
                break;
            case 4:
                Categories categories = new Categories();
                System.out.println("Введите ID категории");
                categories.setId(scn.nextLong());
                System.out.println("Введите обновленное название категории");
                scn.nextLine();
                categories.setName(scn.nextLine());
                System.out.println("Введите статус категории true/false");
                categories.setActive(scn.nextBoolean());
                categoriesService.update(categories);
                break;
            case 5:
                System.out.println("Введите ID для удаления");
                categoriesService.delete(scn.nextLong());
                break;
        }
    }

    @Override
    public void getProductControl() {
        System.out.println("Выберите действие");
        System.out.println("1.Добавить продукт");
        System.out.println("2.Вывести весь список продуктов");
        System.out.println("3.Вывести продукт по ID");
        System.out.println("4.Редактировать данные продукта по его ID");
        System.out.println("5.Удалить продукт по ID");

        switch (scn.nextInt()){
            case 1:
                Product product = new Product();
                System.out.println("Введите название продукта");
                scn.nextLine();
                product.setName(scn.nextLine());
                System.out.println("Введите цену продукта");
                product.setPrice(scn.nextDouble());
                System.out.println("Введите сумму скидки на продукт");
                product.setDiscount(scn.nextInt());
                System.out.println("Введите ID категории");
                product.setCategories(scn.nextLong());
                productService.save(product);
                break;
            case 2:
                System.out.println(productService.findAll());
                break;
            case 3:
                System.out.println("Введите ID продукта");
                System.out.println(productService.findById(scn.nextLong()));
                break;
            case 4:
                Product updatedProduct = new Product();
                System.out.println("Введите ID продукта");
                updatedProduct.setId(scn.nextLong());
                System.out.println("Введите обновленное название продукта");
                scn.nextLine();
                updatedProduct.setName(scn.nextLine());
                System.out.println("Введите цену родукта");
                updatedProduct.setPrice(scn.nextDouble());
                System.out.println("Введите скидку на продукт");
                updatedProduct.setDiscount(scn.nextInt());
                System.out.println("Введите статус продукта true/false");
                updatedProduct.setActive(scn.nextBoolean());
                productService.update(updatedProduct);
                break;
            case 5:
                System.out.println("Введите ID для удаления");
                productService.delete(scn.nextLong());
                break;
        }
    }

    @Override
    public void getCheckControl() {
        System.out.println("Выберите действие");
        System.out.println("1.Добавить чек");
        System.out.println("2.Вывести весь список чеков");
        System.out.println("3.Вывести чек по ID");
        System.out.println("4.Редактировать данные чека по его ID");
        System.out.println("5.Удалить чек по ID");


        switch (scn.nextInt()){
            case 1:
                System.out.println("Введите сумму чека");
                checkService.save(scn.nextDouble());
                break;
            case 2:
                System.out.println(checkService.findAll());
                break;
            case 3:
                System.out.println("Введите ID чека");
                System.out.println(checkService.findById(scn.nextLong()));
                break;
            case 4:
                Check updatedCheck = new Check();
                System.out.println("Введите ID чека");
                updatedCheck.setId(scn.nextLong());
                System.out.println("Введите сумму");
                updatedCheck.setTotalSum(scn.nextDouble());
                checkService.update(updatedCheck);
                break;
            case 5:
                System.out.println("Введите ID для удаления");
                checkService.delete(scn.nextLong());
                break;
        }
    }

    @Override
    public void getUserControl() {
        System.out.println("Выберите действие");
        System.out.println("1.Добавить работника");
        System.out.println("2.Вывести весь список работников");
        System.out.println("3.Вывести данные работника по ID");
        System.out.println("4.Редактировать данные работника по его ID");
        System.out.println("5.Удалить работника по ID");

        switch (scn.nextInt()){
            case 1:
                Users user = new Users();
                System.out.println("Введите имя работника");
                scn.nextLine();
                user.setName(scn.nextLine());
                System.out.println("Введите логин");
                user.setLogin(scn.next());
                System.out.println("Введите пароль");
                user.setPassword(scn.next());
                System.out.println("Введите ID магазина");
                user.setShop(shopService.findById(scn.nextLong()).getId());
                usersService.save(user);
                break;
            case 2:
                System.out.println(usersService.findAll());
                break;
            case 3:
                System.out.println("Введите ID работника");
                System.out.println(usersService.findById(scn.nextLong()));
                break;
            case 4:
                Users updatedUser = new Users();
                System.out.println("Введите ID работника");
                updatedUser.setId(scn.nextLong());
                System.out.println("Введите имя работника");
                scn.nextLine();
                updatedUser.setName(scn.nextLine());
                System.out.println("Введите логин");
                updatedUser.setLogin(scn.next());
                System.out.println("Введите пароль");
                updatedUser.setPassword(scn.next());
                System.out.println("Введите ID магазина");
                updatedUser.setShop(scn.nextLong());
                System.out.println("Введите статус работника true/false");
                updatedUser.setActive(scn.nextBoolean());
                usersService.update(updatedUser);
                break;
            case 5:
                System.out.println("Введите ID для удаления");
                usersService.delete(scn.nextLong());
                break;

        }

    }
    @Override
    public void getCheckProductControl() {
        System.out.println("Выберите действие");
        System.out.println("1.Добавить чек/продукт");
        System.out.println("2.Вывести весь список чек/продукт");
        System.out.println("3.Вывести данные чек/продукт по ID");
        System.out.println("4.Редактировать данные чек/продукт по его ID");
        System.out.println("5.Удалить чек/продукт по ID");

        switch (scn.nextInt()){
            case 1:
                CheckProduct checkProduct = new CheckProduct();
                System.out.println("Введите ID чека");
                checkProduct.setCheck(scn.nextLong());
                System.out.println("Введите ID продукта");
                checkProduct.setProduct(scn.nextLong());
                System.out.println("Введите сумму");
                checkProduct.setCount(scn.nextDouble());
                checkProductService.save(checkProduct);
                break;
            case 2:
                System.out.println(checkProductService.findAll());
                break;
            case 3:
                System.out.println("Введите ID чек/продукт");
                System.out.println(checkProductService.findById(scn.nextLong()));
                break;
            case 4:
                CheckProduct updatedCheckProduct = new CheckProduct();
                System.out.println("Введите ID чек/продукт");
                updatedCheckProduct.setId(scn.nextLong());
                System.out.println("Введите ID чека");
                updatedCheckProduct.setCheck(scn.nextLong());
                System.out.println("Введите ID продукта");
                updatedCheckProduct.setProduct(scn.nextLong());
                System.out.println("Введите сумму");
                updatedCheckProduct.setCount(scn.nextInt());
                checkProductService.update(updatedCheckProduct);
                break;
            case 5:
                System.out.println("Введите ID для удаления");
                checkProductService.delete(scn.nextLong());
                break;
        }
        }

    @Override
    public void getShopCategoriesControl() {
        System.out.println("Выберите действие");
        System.out.println("1.Добавить магазин/категория");
        System.out.println("2.Вывести весь список магазин/категория");
        System.out.println("3.Вывести данные магазин/категория по ID");
        System.out.println("4.Редактировать данные магазин/категория по его ID");
        System.out.println("5.Удалить магазин/категория по ID");

        switch (scn.nextInt()) {
            case 1:
                ShopCategories shopCategories = new ShopCategories();
                System.out.println("Введите ID магазина");
                shopCategories.setShop(scn.nextLong());
                System.out.println("Введите ID категории");
                shopCategories.setCategories(scn.nextLong());
                shopCategoriesService.save(shopCategories);
                break;
            case 2:
                System.out.println(shopCategoriesService.findAll());
                break;
            case 3:
                System.out.println("Введите ID магазин/категория");
                System.out.println(shopService.findById(scn.nextLong()));
                break;
            case 4:
                ShopCategories updatedShopCategories = new ShopCategories();
                System.out.println("Введите ID магазин/категория");
                updatedShopCategories.setId(scn.nextLong());
                System.out.println("Введите ID магазина");
                updatedShopCategories.setShop(scn.nextLong());
                System.out.println("Введите ID категории");
                updatedShopCategories.setCategories(scn.nextLong());
                System.out.println("Введите статус магазин/категория true/false");
                updatedShopCategories.setActive(scn.nextBoolean());
                shopCategoriesService.update(updatedShopCategories);
                break;
            case 5:
                System.out.println("Введите ID для удаления");
                shopCategoriesService.delete(scn.nextLong());
                break;
        }
    }
}
