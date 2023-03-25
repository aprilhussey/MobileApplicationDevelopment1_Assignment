package com.example.primaryschoolapplication;

public class FixedLogins
{
    public static void CreateFixedLogins()
    {
        LoginSystem LGS = new LoginSystem();

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

        LGS.saveUser(u1);
        LGS.saveUser(u2);
        LGS.saveUser(u3);
        LGS.saveUser(u4);
        LGS.saveUser(u5);
        LGS.saveUser(u6);
        LGS.saveUser(u7);
        LGS.saveUser(u8);
        LGS.saveUser(u9);
        LGS.saveUser(u10);
    }
}
