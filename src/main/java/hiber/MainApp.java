package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User john = new User("John", "Black", "user1@mail.ru");
        User mary = new User("Mary", "Red", "user2@mail.ru");
        User bella = new User("Bella", "Green", "user3@mail.ru");
        User george = new User("George", "Brown", "user4@mail.ru");

        Car blackCar = new Car("BlackCar", 65);
        Car redCar = new Car("RedCar", 4);
        Car greenCar = new Car("GreenCar", 13);
        Car brownCar = new Car("BrownCar", 46);

        john.setCar(blackCar);
        userService.add(john);
        mary.setCar(redCar);
        userService.add(mary);
        bella.setCar(greenCar);
        userService.add(bella);
        george.setCar(brownCar);
        userService.add(george);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        System.out.println(userService.getUserByCar("BlackCar", 65));


        context.close();
    }
}
