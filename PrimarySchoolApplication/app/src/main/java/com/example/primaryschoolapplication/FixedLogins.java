package com.example.primaryschoolapplication;

public class FixedLogins
{
    public static void CreateFixedLogins()
    {
        LoginSystem loginSystem = new LoginSystem();

        User u1 = new User ("pBarker", "Pepsi4EVER", "Philippa", "Barker");
        User u2 = new User ("yHolmes", "1mBatb0y", "Yasin", "Holmes");
        User u3 = new User ("eDavison", "T1nk3rb311", "Ella", "Davison");
        User u4 = new User ("rMorton", "R0saM1tch3ll", "Rosa", "Morton");
        User u5 = new User ("gReed", "Jasper3", "Gerald", "Reed");
        User u6 = new User ("cHunt", "i<3Rosa", "Ciaran", "Hunt");
        User u7 = new User ("pMitchel", "Barbie007", "Polly", "Mitchell");
        User u8 = new User ("rDuncan", "h3ll0Rocco", "Rocco", "Duncan");
        User u9 = new User ("gSingh", "boysF3RDA", "Glenn", "Singh");
        User u10 = new User ("cCastro", "cats4LIFE", "Caitlin", "Castro");

        loginSystem.saveUser(u1);
        loginSystem.saveUser(u2);
        loginSystem.saveUser(u3);
        loginSystem.saveUser(u4);
        loginSystem.saveUser(u5);
        loginSystem.saveUser(u6);
        loginSystem.saveUser(u7);
        loginSystem.saveUser(u8);
        loginSystem.saveUser(u9);
        loginSystem.saveUser(u10);
    }
}
