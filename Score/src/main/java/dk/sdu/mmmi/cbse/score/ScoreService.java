package dk.sdu.mmmi.cbse.score;


import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    private Long totalScore = 0L;

    public Long addToTotalScore(Long point) {
        totalScore += point;
        return totalScore;
    }

    public Long getTotalScore() {
        return totalScore;
    }
}
