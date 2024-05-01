
import dk.sdu.mmmi.cbse.common.services.IScoreService;
import dk.sdu.mmmi.cbse.scoresystem.ScoreSystem;

module ScoreSystem {
    requires Common;
    provides IScoreService with ScoreSystem;

}
