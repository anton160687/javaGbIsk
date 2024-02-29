package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserDataApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные в формате: Фамилия Имя Отчество ДатаРождения НомерТелефона Пол");

        String input = scanner.nextLine();
        String[] data = input.split(" ");

        if (data.length != 6) {
            System.out.println("Ошибка: неверное количество данных");
            return;
        }

        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];
        String birthDate = data[3];
        String phoneNumber = data[4];
        String gender = data[5];

        try {
            validateData(lastName, firstName, middleName, birthDate, phoneNumber, gender);
            saveUserData(lastName, firstName, middleName, birthDate, phoneNumber, gender);
            System.out.println("Данные успешно сохранены");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }

    private static void validateData(String lastName, String firstName, String middleName, String birthDate,
                                     String phoneNumber, String gender) {
        if (lastName.isEmpty() || firstName.isEmpty() || middleName.isEmpty() || birthDate.isEmpty() ||
                phoneNumber.isEmpty() || gender.isEmpty()) {
            throw new IllegalArgumentException("Не все данные были введены");
        }

        // Проверка формата даты рождения
        if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new IllegalArgumentException("Неверный формат даты рождения");
        }

        // Проверка формата номера телефона
        if (!phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Неверный формат номера телефона");
        }

        // Проверка значения пола
        if (!gender.equals("f") && !gender.equals("m")) {
            throw new IllegalArgumentException("Неверное значение пола");
        }
    }

    private static void saveUserData(String lastName, String firstName, String middleName, String birthDate,
                                     String phoneNumber, String gender) throws IOException {
        String fileName = lastName + ".txt";
        FileWriter writer = new FileWriter(fileName, true);
        writer.write(lastName + " " + firstName + " " + middleName + " " + birthDate + " " + phoneNumber + " " + gender + "\n");
        writer.close();
    }
}
