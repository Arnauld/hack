package hack.cyberspace;

import org.junit.Test;

public class GridTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_null_cells() {
        new Grid(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_null_rows() {
        new Grid(new Cell[][]{null});
    }

    @Test
    public void should_accept_non_squared_cells() {
        new Grid(new Cell[][]{
                {new Cell(), new Cell()},
                {new Cell()},
                {new Cell(), new Cell(), new Cell()},
                {new Cell()}
        });
    }

    @Test(expected = SegFaultException.class)
    public void should_throw_exception_when_accessed_in_an_out_of_range_manned() {
        Grid grid = new Grid(new Cell[][]{
                {new Cell(), new Cell()},
                {new Cell(), new Cell()}});
        grid.cellAt(Address.address(2, 1));
    }

    @Test(expected = SegFaultException.class)
    public void should_throw_exception_when_accessed_with_negative_x_address() {
        Grid grid = new Grid(new Cell[][]{
                {new Cell(), new Cell()},
                {new Cell(), new Cell()}});
        grid.cellAt(Address.address(-1, 1));
    }

    @Test(expected = SegFaultException.class)
    public void should_throw_exception_when_accessed_with_negative_y_address() {
        Grid grid = new Grid(new Cell[][]{
                {new Cell(), new Cell()},
                {new Cell(), new Cell()}});
        grid.cellAt(Address.address(1, -1));
    }

}