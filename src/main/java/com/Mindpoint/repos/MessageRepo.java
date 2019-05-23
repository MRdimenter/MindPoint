package com.Mindpoint.repos;

import com.Mindpoint.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Базовый репозиторий. Позволяет получить полный список полей
 * либо найти их по идентификатору.
 */

public interface MessageRepo extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag); //нахождение сообщений по тегу
}
