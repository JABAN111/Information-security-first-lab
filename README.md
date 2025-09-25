Работа 1: Разработка защищенного REST API с интеграцией в CI/CD \
Стек: java + spring \
Сборщик: maven \
Для данной лабораторной было разработано безопасное backend-приложение с автоматизированной проверка кода на уязвимости 

# Разработанные endpoint:
1. Регистрация
Создает новый аккаунт в веб сервисе и возвращает токен \
POST /auth/registration \
Пример тела 
```json
{
    "email": "jaba1@jaba.jaba",
    "password": "password"
}
```
Пример ответа
```json
  "token": "hbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcm5h"
```
2. Авторизация \
Возвращает токен для авторизации POST /auth/registration \
Пример тела 
```json
{
    "email": "jaba1@jaba.jaba",
    "password": "password"
}
```

Пример ответа \
```json
  "token": "hbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcm5h"
```
3. GET /api/v1/music/data \
Возвращает всю музыку на сервисе \
Пример ответа 
```json
[
    {
        "id": 1,
        "title": "Jaba",
        "description": "description of song jaba",
        "author": "jaba jaba"
    },
    {
        "id": 2,
        "title": "Jaba",
        "description": "description of song jaba",
        "author": "jaba jaba"
    },
]
```
4. POST /api/v1/music \
Добавляет новую музыку на сервис \
Пример тела: 
```json
{
  "title": "Jaba",
  "description": "description of song jaba",
  "author": "jaba jaba"
}
```
5. GET /api/v1/music/{ID} \
Возвращает музыку по ID \
Пример ответа: 
```json
{
    "id": 1,
    "title": "Jaba",
    "description": "description of song jaba",
    "author": "jaba jaba"
}
```
---
Меры защиты: 
1.	Аутентификация 
При успешной авторизации/регистрации для пользователя генерируется JWT токен, при обращении к сервису просматривается этот токен и проверяется токен на подлинность.  
2.	Защита от SQLi 
Для взаимодействия с базой данных используется Hibernate в качестве ORM, который позволяет избежать потенциальной sql инъекции 
3.	 Защита от XSS 
Реализована с помощью экранирования пользовательских данных за счет утилитарной функции StringUtils.escapeHtml4() 
4.	Защита паролей 
Все пароли хранятся в зашифрованном виде алгоритмом BCryptPasswordEncoder 
---
## Отчёты SAST/SCA
Spotbugs
---
<img width="434" height="700" alt="Снимок экрана 2025-09-26 в 01 37 03" src="https://github.com/user-attachments/assets/c9feb8ec-a04e-4595-af46-d913f44fe995" />

Snyk
---
<img width="3282" height="7091" alt="image" src="https://github.com/user-attachments/assets/74a996e4-fddc-4380-bf0d-f254e560c728" />
<img width="482" height="279" alt="image" src="https://github.com/user-attachments/assets/38bc2e38-4f8f-4ca0-87f9-272d7b7f8ab9" />

Во время разработки статическим анализатором кода spotbugs были обнаружены следующие проблемы:
SE_NO_SERIALVERSIONUID – уязвимость, указывающее на отсутствие поля с serialVersionUID в сериализуемом классе. Потенциально может привести к тому, что мы не сможем восстановить класс из-за пересоздания serialVersionUID.
Также за счет библиотеки SNYK была найдена уязвимость CVE-2025-48924, которая была вызвана зависимостью apache.common.lang3. Обновление версии решило проблему.
