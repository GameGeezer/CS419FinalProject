package dungeoncrawler.school.will.dungeoncrawler.framework.util;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import dungeoncrawler.school.will.dungeoncrawler.framework.util.dataTypes.DatatypeUtil;

/**
 * @author William Gervasio
 */
public class BufferUtil {

    public static FloatBuffer createFloatBuffer(int size) {

        return ByteBuffer.allocateDirect(size * DatatypeUtil.FLOAT_SIZE_BYTES).order(ByteOrder.nativeOrder()).asFloatBuffer();
    }

    public static IntBuffer createIntBuffer(int size) {

        return ByteBuffer.allocateDirect(size * DatatypeUtil.INTEGER_SIZE_BYTES).order(ByteOrder.nativeOrder()).asIntBuffer();
    }
}