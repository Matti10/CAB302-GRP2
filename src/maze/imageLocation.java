package maze;

public class imageLocation {
    String path;
    String name;
    Coordinate location;

    imageLocation(String path, String name, Coordinate location)
    {
        this.path = path;
        this.name = name;
        this.location = location;
    }

    imageLocation getImageLocation()
    {
        return this;
    }
}
