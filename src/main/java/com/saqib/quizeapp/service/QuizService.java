package com.saqib.quizeapp.service;

import com.saqib.quizeapp.model.QuestionWrapper;
import com.saqib.quizeapp.dao.QuestionDao;
import com.saqib.quizeapp.dao.QuizDao;
import com.saqib.quizeapp.model.Question;
import com.saqib.quizeapp.model.Quiz;
import com.saqib.quizeapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

//    @Autowired
//    private Question question;

    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory ( category, numQ );
        Quiz quiz = new Quiz ();
        quiz.setTitle ( title );
        quiz.setQuestions ( questions );
        quizDao.save ( quiz );
        return new ResponseEntity<> ( "Success", HttpStatus.OK );
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById ( id );
        List<Question> questionsFromDB = quiz.get ().getQuestions ();
        List<QuestionWrapper> questionForUser = new ArrayList<> ();
        for (Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper ( q.getId (), q.getTitle (), q.getOption1 (), q.getOption2 (), q.getOption3 (), q.getOption4 () );
            questionForUser.add ( qw );
        }
        return new ResponseEntity<> ( questionForUser, HttpStatus.OK );
    }

//    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
//        Quiz quiz = quizDao.findById ( id ).get ();
//        List<Question> questions = quiz.getQuestions ();
//        int right = 0;
//        int i = 0;
//        for (Response response : responses) {
//            if (response.getResponse ().equals ( questions.get ( i ).getCorrectAnswer () ))
//                right++;
//
//            i++;
//        }
//        return new ResponseEntity<> ( right, HttpStatus.OK );
//    }


public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
    Quiz quiz = quizDao.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
    List<Question> questions = quiz.getQuestions();

    int right = 0;

    for (int i = 0; i < responses.size() && i < questions.size(); i++) {
        String userResponse = responses.get(i).getResponse();
        String correctAnswer = questions.get(i).getCorrectAnswer();

        // Check for null values before calling .equals()
        if (userResponse != null && userResponse.equals(correctAnswer)) {
            right++;
        }
    }

    return new ResponseEntity<>(right, HttpStatus.OK);
}

}

