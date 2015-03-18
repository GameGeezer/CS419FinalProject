package dungeoncrawler.school.will.dungeoncrawler.framework.util.dataTypes;

/**
 * @author William Gervasio
 */
public class ByteArrayList {

    private static final int DEFAULT_INITIAL_CAPACITY = 256;
    private static final float DEFAULT_GROWTH_FACTOR = 2.0f;

    private byte[] data;
    private int size;
    private final float growth;

    public ByteArrayList () {

        this ( DEFAULT_INITIAL_CAPACITY, DEFAULT_GROWTH_FACTOR );
    }

    public ByteArrayList(final int capacity) {

        this (capacity, DEFAULT_GROWTH_FACTOR);
    }

    public ByteArrayList(final int capacity, final float growthFactor) {

        data = new byte [ capacity ];
        size = 0;
        growth = growthFactor;
    }

    public int size() {

        return size;
    }

    public void add(final byte value) {

        if ( size == data.length - 1 ) {

            final byte [] newDataArray = new byte [ ( byte ) ( data.length * growth ) ];
            System.arraycopy ( data, 0, newDataArray, 0, data.length );
            data = newDataArray;
        }
        data [ size ] = value;
        ++size;
    }

    public void remove ( final int position ) {

        final int indexesUntilTheEnd = position - size;

        if ( position >= 0 && indexesUntilTheEnd > 0 ) {
            System.arraycopy ( data, position + 1, data, position, indexesUntilTheEnd );
            --size;
        }
    }

    public void clear () {
        size = 0;
    }

    public int [] toArray () {

        final int [] minimumArray = new int [ size ];
        System.arraycopy ( data, 0, minimumArray, 0, size );
        return minimumArray;
    }
}
