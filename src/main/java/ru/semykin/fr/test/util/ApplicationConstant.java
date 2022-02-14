package ru.semykin.fr.test.util;

public interface ApplicationConstant {

    String URL_ADMIN = "/admin";

    String URL_PUBLIC = "/public";

    String URL_POLLS = "/polls";

    String URL_POLLS_USER_ID = URL_POLLS + "/{userId}";

    String URL_POLLS_POLL_ID = URL_POLLS + "/{pollId}";

    String URL_QUESTIONS = "/questions";

    String URL_QUESTIONS_ID = URL_QUESTIONS + "{qId}";

    String URL_ANSWERS = "/answers";

    String URL_ANSWERS_ID = URL_ANSWERS + "{aId}";

    String API_ALL_ACTIVE_USER_POLL = "Получение списка активных опросов пользователя, " +
            "если пользователь уже завершил опрос, то сюда он(опрос) не попадет.";

    String API_ALL_ACTIVE_POLLS = "Получение всех активных опросов на текущий момент.";

    String API_SAVE_USER_RESPONSE = "Сохранение ответов пользователя на опрос.";

    String URL_POLLS_RESULT = "/polls/result";

    String URL_POLLS_RESULT_USER_ID = URL_POLLS_RESULT + "/{pollId}";

    String API_FIND_USER_RESPONSE = "Получение детализации ответов юзера по определенному опросу.";

    String API_FIND_ALL_USER_RESPONSE = "Получение всех ответов пользователя по всем пройденным опросам.";

    String API_USER_ID = "Идентификатор пользователя";

    String API_POLL_ID = "Идентификатор опроса.";

    String API_USER_RESPONSE = "Ответ от пользователя.";

    String API_FIND_ALL_POLLS = "Поиск всех опросов.";

    String API_FIND_POLL_BY_ID = "Поиск опроса по pollId.";

    String API_SAVE_POLL = "Добавление нового опроса.";

    String API_POLL_DTO = "Структура опроса.";

    String API_PUT_POLL = "Редактирование опроса.";

    String API_DEL_POLL = "Удаление опроса по pollId.";

    String API_FIND_ALL_QUESTIONS_BY_POLL_ID = "Поиск всех вопросов в опросе по pollId.";

    String API_FIND_QUESTION_BY_ID = "Поиск вопросов по qId.";

    String API_QUESTION_ID = "Идентификатор вопроса.";

    String API_SAVE_NEW_QUESTION = "Добавление нового вопроса в опрос(pollId)";

    String API_QUESTION_DTO = "Структура вопроса.";

    String API_PUT_QUESTION = "Редактирование вопроса.";

    String API_DEL_QUESTION = "Удаление вопроса по qId";

    String API_FIND_ALL_ANSWERS = "Получение всех ответов в вопросе qId";

    String API_FIND_ANSWER = "Получение ответа по aId.";

    String API_ANSWER_ID = "Идентификатор ответа.";

    String API_SAVE_ANSWER = "Добавление нового ответа в вопрос qId.";

    String API_ANSWER_DTO = "Структура ответа.";

    String API_PUT_ANSWER = "Редактирование ответа.";

    String API_DEL_ANSWER = "Удаление ответа по aId.";
}
