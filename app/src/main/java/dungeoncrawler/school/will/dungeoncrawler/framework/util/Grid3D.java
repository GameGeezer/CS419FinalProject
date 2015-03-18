package dungeoncrawler.school.will.dungeoncrawler.framework.util;

/**
 * @author William Gervasio
 */
public class Grid3D<E> {

    public final E[][][] data;
    public final int length, height, depth;

    public Grid3D(final int length, final int height, final int depth) {

        this.length = length;
        this.height = height;
        this.depth = depth;

        data = (E[][][]) new Object[length][height][depth];
    }
}
