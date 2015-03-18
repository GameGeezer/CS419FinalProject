package dungeoncrawler.school.will.dungeoncrawler.framework.util.fileIO;

import java.io.*;

/**
 * @author William Gervasio
 */
public final class FileUtil {

    /**
     * Parses a file for the text
     *
     * @param fileLocation
     * @return The text as a String
     */
    public static String readText(final String fileLocation) throws IOException {

        final StringBuilder shaderString = new StringBuilder();

        try {

            final BufferedReader reader = new BufferedReader(new FileReader(fileLocation));

	        for ( String line = reader.readLine (); line != null; ) {
		        shaderString.append ( line ).append ( "\n" );
		        line = reader.readLine ();
	        }

            reader.close();
        } catch (final FileNotFoundException e) {

            throw new IOException("File: " + fileLocation + " was not found");
        } catch (final IOException e) {

            throw new IOException("File: " + fileLocation + " could not be read");
        }

        return shaderString.toString();
    }
}
