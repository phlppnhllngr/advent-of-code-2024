package phlppnhllngr.adventofcode2024;

public class Resources {

    static String readString(String name) {
        try {
            byte[] bytes = Resources.class.getResourceAsStream("/" + name).readAllBytes();
            return new String(bytes);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
