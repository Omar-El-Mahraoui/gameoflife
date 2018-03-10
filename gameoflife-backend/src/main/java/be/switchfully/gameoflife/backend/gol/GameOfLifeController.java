package be.switchfully.gameoflife.backend.gol;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = GameOfLifeController.WORLD_BASE_URL)
public class GameOfLifeController {

    static final String WORLD_BASE_URL = "/api/gol";
    private static Logger logger = Logger.getLogger(GameOfLifeController.class);
    private World currentWorld;
    private World nextWorld;

    @PostMapping(value = "/phase")
    public List<List<Boolean>> nextGeneration(@RequestBody List<List<Boolean>> currentWorld){
        logger.info(currentWorld);
        this.currentWorld = new World(currentWorld);
        this.nextWorld = World.copy(this.currentWorld);
        setNextWorld();
        return nextWorld.getCells();
    }

    private void setNextWorld() {
        for (int row = 0; row < currentWorld.getNumberOfRows(); row++) {
            for (int column = 0; column < currentWorld.getNumberOfColumns(); column++) {
                applyTheRulesForGeneratingTheNextWorld(new Coordinate(row, column));
            }
        }
    }

    private void applyTheRulesForGeneratingTheNextWorld(Coordinate coordinate) {
        if (currentWorld.currentCoordinateIsTrue(coordinate)) {
            if (currentWorld.coordinateHas0Or1OrMoreThan4TrueNeighbours(coordinate)) {
                nextWorld.setCoordinateOnFalse(coordinate);
            }
        } else {
            if (currentWorld.coordinateHas3TrueNeighbours(coordinate)) {
                nextWorld.setCoordinateOnTrue(coordinate);
            }
        }
    }

}
