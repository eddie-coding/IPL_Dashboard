package com.eddie.ipldashboard.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.eddie.ipldashboard.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

  private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

  @Override
  public Match process(final MatchInput matchInput) throws Exception {

    Match match = new Match();
    match.setId(Long.parseLong(matchInput.getID()));
    match.setCity(matchInput.getCity());
    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate d1 = LocalDate.parse(matchInput.getDate(), df);
    match.setDate(d1);
    match.setPlayerOfMatch(matchInput.getPlayer_of_Match());
    match.setVenue(matchInput.getVenue());

    String firstInnningsTeam, secondInningsTeam;

    if("bat".equals(matchInput.getTossDecision())){
        firstInnningsTeam = matchInput.getTossWinner();
        secondInningsTeam = matchInput.getTossWinner().equals(matchInput.getTeam1()) ? matchInput.getTeam2() : matchInput.getTeam1();
    }
    else{
        secondInningsTeam = matchInput.getTossWinner();
        firstInnningsTeam = matchInput.getTossWinner().equals(matchInput.getTeam1()) ? matchInput.getTeam2() : matchInput.getTeam1();
    }

    match.setTeam1(firstInnningsTeam);
    match.setTeam2(secondInningsTeam);
    match.setTossWinner(matchInput.getTossWinner());
    match.setTossDecision(matchInput.getTossDecision());
    match.setMatchWinner(matchInput.getWinningTeam());
    match.setResult(matchInput.getWonBy());
    match.setResultMargin(matchInput.getMargin());
    match.setUmpire1(matchInput.getUmpire1());
    match.setUmpire2(matchInput.getUmpire2());

    return match;

    }
}