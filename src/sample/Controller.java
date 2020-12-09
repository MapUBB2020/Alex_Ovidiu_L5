package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {

    private Fragebogen quiz;
    public FileRepository filerepo;

    public Controller() throws IOException {
        this.filerepo = new FileRepository();
        generateQuiz();
    }

    public List<Frage> getAllFragen() {
        return filerepo.getAll();
    }

    public void generateQuiz() {
        Random rand = new Random();
        List<Frage> questions = filerepo.getAll();
        List<Frage> quizQuestions = new ArrayList<Frage>();
        int counter = 1;
        while (counter <= 26) {
            int questionNumber = rand.nextInt(questions.size());
            Frage question = questions.get(questionNumber);
            question.setID(counter);
            quizQuestions.add(question);
            questions.remove(question);
            counter++;
        }
        this.quiz = new Fragebogen(1, quizQuestions, 0, 0);
    }

    public boolean areAnswersOkay(List<String> gegebenAntworten, Frage f) {
        if (f.getKorrekteAntworten().size() != gegebenAntworten.size()) {
            return false;
        }
        List<String> ok = f.getKorrekteAntworten();
        return ok.containsAll(gegebenAntworten);
    }

    public Frage getNextQuestion() {
        return quiz.getCurrentQuestion();
    }

    public void setCorrectQuestion() {
        quiz.setAnzahlRichtige(quiz.getAnzahlRichtige() + 1);
    }

    public void setFalseQuestion() {
        quiz.setAnzahlFalsche(quiz.getAnzahlFalsche() + 1);
    }

    public int getRightAnswers() {
        return quiz.getAnzahlRichtige();
    }

    public int getWrongAnswers() {
        return quiz.getAnzahlFalsche();
    }
}
