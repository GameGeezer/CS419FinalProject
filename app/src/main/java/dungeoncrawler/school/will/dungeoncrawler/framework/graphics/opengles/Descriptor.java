package dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles;

/**
 * Descriptors are used to describe the data used to define generic attribute pointers.
 *
 * @author William Gervasio
 */
public final class Descriptor {

    public final int size;
    public final int type;
    public final boolean normalized;
    public final int stride;
    public final int pointer;

    /**
     * @param size       The number of elements per vertex
     * @param type       The data type. "GL_FLOAT" for example
     * @param normalized Whether the data should be normalized or not
     * @param stride     The size in bytes needed to store all the vertices
     * @param pointer    The offset in bytes of the first index
     */
    public Descriptor(final int size, final int type, final boolean normalized, final int stride, final int pointer) {

        this.size = size;
        this.type = type;
        this.normalized = normalized;
        this.stride = stride;
        this.pointer = pointer;
    }
}