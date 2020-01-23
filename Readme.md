# Java Training Example Project

## Overview

Project for providing and examples for Java Trainings.

Приложение для предоставления расписания занятий на тренингах, контроля посещаемости и выставления оценок.

## Сущности
Ниже перечисленный сущности в предметной области проекта и их поля.

### Student (Студент)
Пользователь проходящий обучение на курсе. Может быть записан на различные курсы.

Поля:
- Email
- ФИО
- Пол
- Дата рождения
- Информация о себе

Связи:
- появляются в рамках курса

### Teacher (Преподаватель)
Пользователь, который ответственнен за курс и/или за конкретное занятие.
Если Преподаватель ответственный за курс, то он может редактировать курс и занятия.
Если Преподаватель ответственный за конкретное занятие, то он может редактировать это занятие.

Поля:
- Email
- ФИО
- Пол
- Дата рождения
- Информация о себе

Связи:
- появляются в рамках курса

### Mentor (Ментор)
Пользователь, ответственный за конкретного студента на конкретном курсе.

Поля:
- Email
- ФИО
- Пол
- Дата рождения
- Информация о себе

Связи:
- появляются в рамках курса

### Course (Курс)
Курс по какой-либо теме.

Поля:
- Тема курса
- Описание
- Дата начала
- Дата окончания
- Ответственный преподователь

Связи:
- Ответственный преподователь ("Teacher")
- Список студентов ("Student")
- Ментор у каждого студента в рамках курса ("Mentor" to "Student")
- Итоговая оценка студенту за курс

### Lesson (Занятие)
Конкретное занятие.

Поля:
- Тема занятия
- Дата и время начала
- Длительность
- Преподователь

Связи:
- Преподователь ("Teacher")
- Оценка от ментора студенту за занятие ("Mentor", "Student")
- Итоговая оценка студенту за занятие ("Student")

## User Stories

В первую очередь начнем с работы "Студента" с системой.
Сложная аутентификация  и работа с токенами пока вне скоупа, предпологается что пользователь будет передавать свой id как header.

### JTEP-1 Как "Студент" я хочу зарегистрироваться в системе, и, если такого пользователя не найдено, регистрируюсь

Request:

`POST /java-training-app/student/sign-up`
```json
{
  "email" : "vasya@email.com",
  "password" : "qwerty",
  "fio" : "Пупкин Василий Иванович",
  "gender" : "male", 
  "birthDate" : "19.01.1995",
  "info" : "Молодой инженер" 
}
```

Response:
`201 CREATED`
```json
{
  "id" : 1
}
```

### JTEP-2 Как "Студент", будучи зарегистрированным пользователем, я хочу войти в систему, и, если такой пользователь существует и пароль совпадает, войти в систему

Request:

`POST /java-training-app/student/sign-in`
```json
{
  "email" : "vasya@email.com",
  "password" : "qwerty"
}
```

Response:
`200 OK`
```json
{
  "id" : 1
}
```

### JTEP-3 Как "Студент" я хочу получить список доступных курсов, и в результате получаю его   

Request:

`GET /java-training-app/course/list`

Response:
`200 OK`
```json
[
  {
    "id" : 1, 
    "title" : "GP Java Training Winter 2019-2020",
    "description" : "Курс по обучению старту проектов на языке Java",
    "startDate" : "04.02.2020", 
    "endDate" : "28.02.2020",
    "teacherName" : "Литвинов Владимир Дмитриевич" 
  }
]
```

### JTEP-4 Как "Студент" я хочу записаться на доступный курс, и в результате записываюсь

Пока что заявка на запись будет приниматься автоматически, но в дальнейшем она должна будет вначале быть согласовано преподавателем.   

Request:

`GET /java-training-app/course/${courseId}/register`

`Headers: userId=1` 


Response:
`200 OK`