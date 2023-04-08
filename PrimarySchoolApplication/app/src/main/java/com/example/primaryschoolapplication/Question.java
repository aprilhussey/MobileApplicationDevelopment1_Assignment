package com.example.primaryschoolapplication;

public class Question
{
    // Declare variables
    private String questionText;
    private String[] answerOptions;
    private int correctAnswerIndex;

    public Question(String questionText, String[] answerOptions, int correctAnswerIndex)
    {
        // Initialise variables
        this.questionText = questionText;
        this.answerOptions = answerOptions;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    // Getter functions
    public String getQuestionText()
    {
        return questionText;
    }

    public String[] getAnswerOptions()
    {
        return answerOptions;
    }

    public int getCorrectAnswerIndex()
    {
        return correctAnswerIndex;
    }
}
