package aconex.jessie.core;

import aconex.jessie.consts.ErrorType;
import aconex.jessie.core.Vote;

import java.util.List;

public class Ballot {
    public boolean Valid;
    public boolean Exhausted;
    public String VoteInput;
    public List<Vote> Vote;
    public ErrorType error;
}
