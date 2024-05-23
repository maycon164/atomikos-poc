package com.example.demo.validator;

import com.example.demo.model.Worker;
import org.springframework.stereotype.Component;

@Component
public class MagicValidator {

    public void validateWorker(Worker worker){

        if (worker.getEmail().contains("@gmail.com")) {
            throw new RuntimeException("Algo deu errado");
        }

    }
}
