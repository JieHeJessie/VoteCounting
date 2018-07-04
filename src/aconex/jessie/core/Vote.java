package aconex.jessie.core;

import aconex.jessie.consts.ErrorType;

public class Vote {

    public Vote(int preference, String input){
        this.Preference = preference;
        this.Input = input;
    }

    public int Preference;
    public String Input;
    public Candidate Candidate;
    public boolean Valid;
    public ErrorType Error;
}
