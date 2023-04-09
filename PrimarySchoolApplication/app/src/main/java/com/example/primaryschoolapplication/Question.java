package com.example.primaryschoolapplication;

import android.graphics.drawable.Drawable;
import android.media.Image;

public class Question
{
    // Declare variables
    private int questionImage = -1; // Default value to indicate no image
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

    public Question(int questionImage, String questionText, String[] answerOptions, int correctAnswerIndex)
    {
        // Initialise variables
        this.questionImage = questionImage;
        this.questionText = questionText;
        this.answerOptions = answerOptions;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    // Getter functions
    public int getQuestionImage()
    {
        return questionImage;
    }

    public boolean hasImage()
    {
        return questionImage != -1;
    }

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
