package be.switchfully.gameoflife.backend.gol;

import java.util.ArrayList;
import java.util.List;

public class NextWorldGenerator {

    private List<List<Boolean>> currentWorld;
    private List<List<Boolean>> nextWorld;

    NextWorldGenerator(List<List<Boolean>> currentWorld) {
        this.currentWorld = currentWorld;
        nextWorld = new ArrayList<>();
        for (int row=0; row<currentWorld.size(); row++) {
            List<Boolean> rowListInTheBoard = new ArrayList<>();
            for (int column=0; column<currentWorld.get(0).size(); column++) {
                Boolean gridElement = currentWorld.get(row).get(column);
                rowListInTheBoard.add(gridElement);
            }
            nextWorld.add(rowListInTheBoard);
        }
    }

    public List<List<Boolean>> getNextWorld() {
        for (int row = 0; row < currentWorld.size(); row++) {
            for (int column = 0; column < currentWorld.get(0).size(); column++) {
                applyTheRulesForTheNextWorld(new Coordinate(row, column));
            }
        }
        return nextWorld;
    }


    private void applyTheRulesForTheNextWorld(Coordinate coordinate) {
        if (currentCoordinateIsTrue(coordinate)) {
            if (coordinateHas0Or1OrMoreThan4TrueNeighbours(coordinate)) {
                setCoordinateOnFalse(coordinate);
            }
        } else {
            if (getAmountOfTrueNeighbours(coordinate) == 3) {
                setCoordinateOnTrue(coordinate);
            }
        }
    }

    private void setCoordinateOnTrue(Coordinate coordinate) {
        nextWorld.get(coordinate.getRow()).set(coordinate.getColumn(),true);
    }

    private void setCoordinateOnFalse(Coordinate coordinate) {
        nextWorld.get(coordinate.getRow()).set(coordinate.getColumn(),false);
    }

    private Boolean coordinateHas0Or1OrMoreThan4TrueNeighbours(Coordinate coordinate) {
        return getAmountOfTrueNeighbours(coordinate) == 0
                || getAmountOfTrueNeighbours(coordinate) == 1
                || getAmountOfTrueNeighbours(coordinate) >= 4;
    }

    private Boolean currentCoordinateIsTrue(Coordinate coordinate) {
        return currentWorld.get(coordinate.getRow()).get(coordinate.getColumn());
    }

    private int getAmountOfTrueNeighbours(Coordinate coordinate) {
        int numberOfTrueNeighbours = 0;
        if (eastNeighbourIsTrue(coordinate)) { numberOfTrueNeighbours++; }
        if (southEastNeighbourIsTrue(coordinate)) { numberOfTrueNeighbours++; }
        if (southNeighbourIsTrue(coordinate)) { numberOfTrueNeighbours++; }
        if (southWestNeighbourIsTrue(coordinate)) { numberOfTrueNeighbours++; }
        if (westNeighbourIsTrue(coordinate)) { numberOfTrueNeighbours++; }
        if (northWestNeighbourIsTrue(coordinate)) { numberOfTrueNeighbours++; }
        if (northNeighbourIsTrue(coordinate)) { numberOfTrueNeighbours++; }
        if (northEastNeighbourIsTrue(coordinate)) { numberOfTrueNeighbours++; }
        return numberOfTrueNeighbours;
    }

    private Boolean eastNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getColumn()+1 >= currentWorld.get(0).size()) {
            return false;
        }
        return currentWorld.get(coordinate.getRow()).get(coordinate.getColumn()+1);
    }

    private Boolean southEastNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getRow()+1 >= currentWorld.size()
                || coordinate.getColumn()+1 >= currentWorld.get(0).size()) {
            return false;
        }
        return currentWorld.get(coordinate.getRow()+1).get(coordinate.getColumn()+1);
    }

    private Boolean southNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getRow()+1 >= currentWorld.size()) {
            return false;
        }
        return currentWorld.get(coordinate.getRow()+1).get(coordinate.getColumn());
    }

    private Boolean southWestNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getRow()+1 >= currentWorld.get(0).size()
                || coordinate.getColumn()-1 < 0) {
            return false;
        }
        return currentWorld.get(coordinate.getRow()+1).get(coordinate.getColumn()-1);
    }

    private Boolean westNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getColumn()-1 < 0) {
            return false;
        }
        return currentWorld.get(coordinate.getRow()).get(coordinate.getColumn()-1);
    }

    private Boolean northWestNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getRow()-1 < 0
                || coordinate.getColumn()-1 < 0) {
            return false;
        }
        return currentWorld.get(coordinate.getRow()-1).get(coordinate.getColumn()-1);
    }

    private Boolean northNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getRow()-1 < 0) {
            return false;
        }
        return currentWorld.get(coordinate.getRow()-1).get(coordinate.getColumn());


    }

    private Boolean northEastNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getRow()-1 < 0
                || coordinate.getColumn()+1 >= currentWorld.get(0).size()) {
            return false;
        }
        return currentWorld.get(coordinate.getRow()-1).get(coordinate.getColumn()+1);
    }

}
