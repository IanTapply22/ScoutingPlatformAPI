package me.iantapply.scoutingPlatform.controllers;

import me.iantapply.scoutingPlatform.dto.ScoutingDataDTO;
import me.iantapply.scoutingPlatform.services.FirebaseService;
import me.iantapply.scoutingPlatform.utilities.ConfigurationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/scoutingPlatform")
public class PrivateAPIController {

    private final ConfigurationUtils configurationUtils;
    public PrivateAPIController(ConfigurationUtils configurationUtils) {
        this.configurationUtils = configurationUtils;
    }


    /**
     * POST scouting data call(s)
     */

    // Create data
    @PostMapping()
    public ResponseEntity<String> createScoutingData(@RequestBody ScoutingDataDTO scoutingDataDTO) throws ExecutionException, InterruptedException {

        configurationUtils.checkForDebug("POST", "/scoutingPlatform");
        return ResponseEntity.ok(FirebaseService.saveScoutingData(scoutingDataDTO));
    }


    /**
     * PATCH scouting data call(s)
     */

    // Update data
    @PatchMapping()
    public ResponseEntity<String> updateScoutingData(@RequestBody ScoutingDataDTO scoutingDataDTO) throws ExecutionException, InterruptedException {

        configurationUtils.checkForDebug("PATCH", "/scoutingPlatform");
        return ResponseEntity.ok(FirebaseService.saveScoutingData(scoutingDataDTO));
    }


    /**
     * DELETE scouting data call(s)
     */

    // Delete data for team on a specific date
    @DeleteMapping(params = { "teamID", "date" })
    public ResponseEntity<String> deleteScoutingDataTeamAndDate(@RequestParam Integer teamID, @RequestParam String date) {

        configurationUtils.checkForDebug("DELETE", "/scoutingPlatform?teamID=" + teamID + "&date=" + date);
        return ResponseEntity.ok(FirebaseService.deleteScoutingData(teamID, date));
    }

    // Delete data for a specific match for a team on a specific day
    @DeleteMapping(params = { "teamID", "date", "match" })
    public ResponseEntity<String> deleteScoutingDataTeamDateAndMatch(@RequestParam Integer teamID, @RequestParam String date, @RequestParam Integer match) {

        configurationUtils.checkForDebug("DELETE", "/scoutingPlatform?teamID=" + teamID + "&date=" + date + "&match=" + match);
        return ResponseEntity.ok(FirebaseService.deleteScoutingData(teamID, date, match));
    }

    // Delete all data for every team (will add more auth for this)
    @DeleteMapping()
    public ResponseEntity<String> deleteAllScoutingData() {

        configurationUtils.checkForDebug("DELETE", "/scoutingPlatform");
        return ResponseEntity.ok(FirebaseService.deleteAllScoutingData());
    }

    // Delete all data that was made on a specific day
    @DeleteMapping(params = { "date" })
    public ResponseEntity<String> deleteAllScoutingDataForDate(@RequestParam String date) {

        configurationUtils.checkForDebug("DELETE", "/scoutingPlatform?date=" + date);
        return ResponseEntity.ok(FirebaseService.deleteAllScoutingData(date));
    }

    // Delete all scouting data for a team
    @DeleteMapping(params = { "teamID" })
    public ResponseEntity<String> deleteAllScoutingDataForTeam(@RequestParam Integer teamID) {

        configurationUtils.checkForDebug("DELETE", "/scoutingPlatform?teamID=" + teamID);
        return ResponseEntity.ok(FirebaseService.deleteAllScoutingData(teamID));
    }
}
