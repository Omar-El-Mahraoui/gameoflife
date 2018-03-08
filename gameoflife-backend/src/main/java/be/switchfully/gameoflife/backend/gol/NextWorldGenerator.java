package be.switchfully.gameoflife.backend.gol;

import java.util.ArrayList;
import java.util.List;

public class NextWorldGenerator {

    private List<List<Boolean>> currentWorldCopy;

    NextWorldGenerator(List<List<Boolean>> currentWorld) {
        currentWorldCopy = new ArrayList<>();
        for (List<Boolean> row : currentWorld) {
            currentWorldCopy.add(row);
        }
    }

    public List<List<Boolean>> getNextWorld() {
        for (int row = 0; row < currentWorldCopy.size(); row++) {
            for (int column = 0; column < currentWorldCopy.get(0).size(); column++) {
                decideActionForCoordinate(new Coordinate(row, column));
            }
        }
        return currentWorldCopy;
    }


    private void decideActionForCoordinate(Coordinate coordinate) {
        if (currentWorldCopy.get(coordinate.getRow()).get(coordinate.getColumn())) {
            if (countAmountOfTrueNeighbours(coordinate) == 0
                    || countAmountOfTrueNeighbours(coordinate) == 1
                    || countAmountOfTrueNeighbours(coordinate) >= 4) {
                currentWorldCopy.get(coordinate.getRow()).set(coordinate.getColumn(),false);
            } else if (countAmountOfTrueNeighbours(coordinate) == 2
                    || countAmountOfTrueNeighbours(coordinate) == 3) {
                currentWorldCopy.get(coordinate.getRow()).set(coordinate.getColumn(),true);
            }
        } else {
            if (countAmountOfTrueNeighbours(coordinate) == 3) {
                currentWorldCopy.get(coordinate.getRow()).set(coordinate.getColumn(),false);
            }
        }
    }

    private int countAmountOfTrueNeighbours(Coordinate coordinate) {
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

    private boolean eastNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getColumn()+1 >= currentWorldCopy.get(0).size()) {
            return false;
        }
        return currentWorldCopy.get(coordinate.getRow()).get(coordinate.getColumn()+1);
    }

    private boolean southEastNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getRow()+1 >= currentWorldCopy.size()
                || coordinate.getColumn()+1 >= currentWorldCopy.get(0).size()) {
            return false;
        }
        return currentWorldCopy.get(coordinate.getRow()+1).get(coordinate.getColumn()+1);
    }

    private boolean southNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getRow()+1 >= currentWorldCopy.size()) {
            return false;
        }
        return currentWorldCopy.get(coordinate.getRow()+1).get(coordinate.getColumn());
    }

    private boolean southWestNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getRow()+1 >= currentWorldCopy.get(0).size()
                || coordinate.getColumn()-1 < 0) {
            return false;
        }
        return currentWorldCopy.get(coordinate.getRow()+1).get(coordinate.getColumn()-1);
    }

    private boolean westNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getColumn()-1 < 0) {
            return false;
        }
        return currentWorldCopy.get(coordinate.getRow()).get(coordinate.getColumn()-1);
    }

    private boolean northWestNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getRow()-1 < 0
                || coordinate.getColumn()-1 < 0) {
            return false;
        }
        return currentWorldCopy.get(coordinate.getRow()-1).get(coordinate.getColumn()-1);
    }

    private boolean northNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getRow()-1 < 0) {
            return false;
        }
        return currentWorldCopy.get(coordinate.getRow()-1).get(coordinate.getColumn());


    }

    private boolean northEastNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getRow()-1 < 0
                || coordinate.getColumn()-1 < 0) {
            return false;
        }
        return currentWorldCopy.get(coordinate.getRow()-1).get(coordinate.getColumn()-1);
    }
}
