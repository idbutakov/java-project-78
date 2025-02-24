### Hexlet tests and linter status:
[![Actions Status](https://github.com/idbutakov/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/idbutakov/java-project-78/actions)

[![Java CI](https://github.com/idbutakov/java-project-78/actions/workflows/main.yaml/badge.svg)](https://github.com/idbutakov/java-project-78/actions/workflows/main.yaml)

<a href="https://codeclimate.com/github/idbutakov/java-project-78/test_coverage"><img src="https://api.codeclimate.com/v1/badges/588b9e8dcd5e489f056c/test_coverage" /></a>

# Hexlet Code Validator

Это библиотека для валидации данных на Java, которая позволяет создавать схемы проверки для различных типов данных: строк, чисел и структур типа Map. С помощью данной библиотеки можно централизованно описывать и применять правила валидации, что помогает повысить надёжность и читаемость кода в приложениях.

## Описание

Библиотека реализует подход валидации через схемы. Основные возможности:
- **Строки**: проверка на обязательное значение, минимальную длину и наличие подстроки.
- **Числа**: проверка на обязательное значение, положительность и соответствие заданному диапазону.
- **Map**: проверка наличия обязательного количества элементов и соответствие значений схемам валидации.

## Зачем это нужно

Валидация входных данных является критически важной задачей в разработке приложений, особенно при работе с пользовательским вводом, API и конфигурационными файлами. Использование схем валидации позволяет:
- Централизованно описывать правила проверки данных.
- Обеспечивать согласованную и повторно используемую логику валидации.
- Сократить количество ошибок, связанных с неверным форматом или недопустимыми значениями.

## Использование

Пример создания валидатора и проверки данных:

```java
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Validator validator = new Validator();

        // Пример валидации строки
        StringSchema stringSchema = validator.string();
        stringSchema.required().minLength(5).contains("hello");
        System.out.println(stringSchema.isValid("hello world")); // true
        System.out.println(stringSchema.isValid("world"));       // false

        // Пример валидации числа
        NumberSchema numberSchema = validator.number();
        numberSchema.required().positive().range(1, 10);
        System.out.println(numberSchema.isValid(5));  // true
        System.out.println(numberSchema.isValid(-1)); // false

        // Пример валидации Map
        MapSchema mapSchema = validator.map();
        mapSchema.required().sizeof(2);
        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        System.out.println(mapSchema.isValid(data));  // true
    }
}