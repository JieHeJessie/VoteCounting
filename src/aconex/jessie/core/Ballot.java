package aconex.jessie.core;

import aconex.jessie.core.Vote;

import java.util.ArrayList;
import java.util.List;

public class Ballot {

    public Ballot(){
        Votes = new ArrayList<>();
        Valid = true;
    }

    public boolean Valid;
    public boolean Exhausted;
    public String InputString;
    public List<Vote> Votes;
}
