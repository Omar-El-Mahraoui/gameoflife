package be.switchfully.gameoflife.backend.gol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class World {

    private List<List<Boolean>> cells;

    public World(List<List<Boolean>> cells) {
        this.cells = cells;
    }

    public List<List<Boolean>> getCells() {
        return Collections.unmodifiableList(cells);
    }

    public int getNumberOfRows() {
        return cells.size();
    }

    public int getNumberOfColumns() {
        return cells.get(0).size();
    }

    public static World copy(World world) {
        List<List<Boolean>> worldCopy = new ArrayList<>();
        for (int row = 0; row < world.getNumberOfRows(); row++) {
            List<Boolean> rowListInWorldCopy = new ArrayList<>();
            for (int column = 0; column < world.getNumberOfColumns(); column++) {
                Boolean itemInWorld = world.getCells().get(row).get(column);
                rowListInWorldCopy.add(itemInWorld);
            }
            worldCopy.add(rowListInWorldCopy);
        }
        return new World(worldCopy);
    }

    public void setCoordinateOnTrue(Coordinate coordinate) {
        cells.get(coordinate.getRow()).set(coordinate.getColumn(), true);
    }

    public void setCoordinateOnFalse(Coordinate coordinate) {
        cells.get(coordinate.getRow()).set(coordinate.getColumn(), false);
    }

    public Boolean coordinateHas0Or1OrMoreThan4TrueNeighbours(Coordinate coordinate) {
        return getAmountOfTrueNeighbours(coordinate) == 0
                || getAmountOfTrueNeighbours(coordinate) == 1
                || getAmountOfTrueNeighbours(coordinate) >= 4;
    }

    public boolean coordinateHas3TrueNeighbours(Coordinate coordinate) {
        return getAmountOfTrueNeighbours(coordinate) == 3;
    }

    public Boolean currentCoordinateIsTrue(Coordinate coordinate) {
        return cells.get(coordinate.getRow()).get(coordinate.getColumn());
    }

    private int getAmountOfTrueNeighbours(Coordinate coordinate) {
        int numberOfTrueNeighbours = 0;
        if (eastNeighbourIsTrue(coordinate)) {
            numberOfTrueNeighbours++;
        }
        if (southEastNeighbourIsTrue(coordinate)) {
            numberOfTrueNeighbours++;
        }
        if (southNeighbourIsTrue(coordinate)) {
            numberOfTrueNeighbours++;
        }
        if (southWestNeighbourIsTrue(coordinate)) {
            numberOfTrueNeighbours++;
        }
        if (westNeighbourIsTrue(coordinate)) {
            numberOfTrueNeighbours++;
        }
        if (northWestNeighbourIsTrue(coordinate)) {
            numberOfTrueNeighbours++;
        }
        if (northNeighbourIsTrue(coordinate)) {
            numberOfTrueNeighbours++;
        }
        if (northEastNeighbourIsTrue(coordinate)) {
            numberOfTrueNeighbours++;
        }
        return numberOfTrueNeighbours;
    }

    private Boolean eastNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getColumn() + 1 >= cells.get(0).size()) {
            return false;
        }
        return cells.get(coordinate.getRow()).get(coordinate.getColumn() + 1);
    }

    private Boolean southEastNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getRow() + 1 >= cells.size()
                || coordinate.getColumn() + 1 >= cells.get(0).size()) {
            return false;
        }
        return cells.get(coordinate.getRow() + 1).get(coordinate.getColumn() + 1);
    }

    private Boolean southNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getRow() + 1 >= cells.size()) {
            return false;
        }
        return cells.get(coordinate.getRow() + 1).get(coordinate.getColumn());
    }

    private Boolean southWestNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getRow() + 1 >= cells.get(0).size()
                || coordinate.getColumn() - 1 < 0) {
            return false;
        }
        return cells.get(coordinate.getRow() + 1).get(coordinate.getColumn() - 1);
    }

    private Boolean westNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getColumn() - 1 < 0) {
            return false;
        }
        return cells.get(coordinate.getRow()).get(coordinate.getColumn() - 1);
    }

    private Boolean northWestNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getRow() - 1 < 0
                || coordinate.getColumn() - 1 < 0) {
            return false;
        }
        return cells.get(coordinate.getRow() - 1).get(coordinate.getColumn() - 1);
    }

    private Boolean northNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getRow() - 1 < 0) {
            return false;
        }
        return cells.get(coordinate.getRow() - 1).get(coordinate.getColumn());
    }

    private Boolean northEastNeighbourIsTrue(Coordinate coordinate) {
        if (coordinate.getRow() - 1 < 0
                || coordinate.getColumn() + 1 >= cells.get(0).size()) {
            return false;
        }
        return cells.get(coordinate.getRow() - 1).get(coordinate.getColumn() + 1);
    }
}
