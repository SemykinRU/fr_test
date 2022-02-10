package ru.semykin.fr.test.util;

public interface ApplicationConstant {

    String URL_ADMIN = "admin";

    String URL_PUBLIC = "public";

    String URL_POLLS = "/polls";

    String URL_QUESTIONS = URL_POLLS + "/{pollId}" + "/questions";

    String URL_ANSWER = URL_POLLS + URL_QUESTIONS + "/answers";
}
